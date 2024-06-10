import groovy.json.JsonSlurperClassic
def call(Map params) {
    stage("CD : Deploy ${params.repo} - ${params.namespace} Kubernetes Cluster"){
        def NAMESPACE = params.namespace
        def CLUSTER_NAME = params.cluster_name
        def K8S_DEPLOYMENT_NAME = params.deployment_name
        def CONT_REGISTRY = params.container_registry
        def DOCKER_IMAGE_NAME = params.docker_image_name
        def deployment_path = params.deploy_path
        def VERSION = params.version
        def ISTIO_NAMESPACE = params.istio_namespace
        def CREDENTIAL_ID = params.credentialid
        def GCR_HUB_ACCOUNT = 'https://' + params.gcraccount
        def DNS = params.dns
        def REPO = params.repo
        def SECRET_NAME = 'secret-' + params.provisionid
        echo "${CONT_REGISTRY}"
        
        sh "sed -i 's/dns/${DNS}/g' ${deployment_path}/sampleapp_deployment.yaml"
        sh "sed -i 's/repo/${REPO}/g' ${deployment_path}/sampleapp_deployment.yaml"
        sh "sed -i 's/imagename/${DOCKER_IMAGE_NAME}/g' ${deployment_path}/sampleapp_deployment.yaml"
        sh "sed -i 's/imagetag/${VERSION}/g' ${deployment_path}/sampleapp_deployment.yaml"
        sh "sed -i 's/USER_NAMESPACE/${NAMESPACE}/g' ${deployment_path}/sampleapp_deployment.yaml"
        withCredentials([file(credentialsId: "${SECRET_NAME}", variable: 'FILE')]) {
            container('kubectl'){
            try{
                sh ("kubectl get ns ${NAMESPACE} --kubeconfig ${FILE}")
                if(true){try{
                        sh ("kubectl get secrets/gitlab-auth -n ${NAMESPACE} --kubeconfig ${FILE}")
                        if(true){
                            echo "secret gitlab-auth present"
                        }
                    }
                    catch(e){
                        withCredentials([[$class: 'UsernamePasswordMultiBinding',
                            credentialsId: "${CREDENTIAL_ID}",
                            usernameVariable: 'USERNAME',
                            passwordVariable: 'PASSWORD']]) {
                                sh "kubectl create secret docker-registry gitlab-auth --docker-server=${GCR_HUB_ACCOUNT} --docker-username=${USERNAME} --docker-password=${PASSWORD} -n ${NAMESPACE} --kubeconfig ${FILE}"
                    }
                    }
                    
                    try{
                        sh ("kubectl get deployment/${K8S_DEPLOYMENT_NAME} -n ${NAMESPACE} --kubeconfig ${FILE}")
                        if (true){
                            String IMAGE = sh (script: "kubectl get --no-headers=true pods -l app=${K8S_DEPLOYMENT_NAME} -n ${NAMESPACE} --kubeconfig ${FILE} -o custom-columns=:spec.containers[].image", returnStdout: true).trim()
                            sh ("kubectl set image deployment/${K8S_DEPLOYMENT_NAME} ${K8S_DEPLOYMENT_NAME}=${CONT_REGISTRY} -n ${NAMESPACE} --kubeconfig ${FILE}")
                            POD_NAME = sh (script: "kubectl get --no-headers=true pods -l app=${K8S_DEPLOYMENT_NAME} -n ${NAMESPACE} --kubeconfig ${FILE} -o custom-columns=:metadata.name", returnStdout: true).trim()
                            TAG_PRESENT = "${IMAGE}".contains("${VERSION}")
                            
                            echo "POD_NAME - ${POD_NAME}"
                            echo "IMAGE - ${IMAGE}"
                            echo "TAG_PRESENT - ${TAG_PRESENT}"
                            
                            if (TAG_PRESENT){
                                sh ("kubectl delete pod ${POD_NAME} -n ${NAMESPACE} --kubeconfig ${FILE}")
                            }
                            else{
                                sh ("kubectl apply -f ${deployment_path} -n ${NAMESPACE} --kubeconfig ${FILE}")                                    
                            }
                            
                            sleep 30
                            sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                        }
                    }
                    catch(e){
                        sh "ls -ll"
                        sh ("kubectl apply -f ${deployment_path} -n ${NAMESPACE} --kubeconfig ${FILE}")
                        sleep 20
                        sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                    }
                }
            }
            catch(e){
                sh ("kubectl create ns ${NAMESPACE} --kubeconfig ${FILE}")
                def wd = pwd()
                echo "current working directory - ${wd}"
                sh "ls -ll"
                sleep 3
                withCredentials([[$class: 'UsernamePasswordMultiBinding',
                            credentialsId: "${CREDENTIAL_ID}",
                            usernameVariable: 'USERNAME',
                            passwordVariable: 'PASSWORD']]) {
                                sh "kubectl create secret docker-registry gitlab-auth --docker-server=${GCR_HUB_ACCOUNT} --docker-username=${USERNAME} --docker-password=${PASSWORD} -n ${NAMESPACE} --kubeconfig ${FILE}"
                            }
                sh ("kubectl apply -f ${deployment_path} -n ${NAMESPACE} --kubeconfig ${FILE}")
                echo "Deploying"
                sleep 30 //seconds
                sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                
                }
            }
        }
        
    }
}

return this
