def call(Map params) {
	stage("CS : ${params.repo} - Application Webscan") {
        def K8S_DEPLOYMENT_NAME = params.deployment_name
        def NAMESPACE = params.namespace
        def SECRET_NAME = 'secret-' + params.provisionid
        echo "K8S_DEPLOYMENT_NAME - ${K8S_DEPLOYMENT_NAME} , NAMESPACE - ${NAMESPACE}"
                sleep 30
                withCredentials([file(credentialsId: "${SECRET_NAME}", variable: 'FILE')]) {
                    container('kubectl') {
                     sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                     sh ("kubectl get svc -n ${NAMESPACE} --kubeconfig ${FILE}")
                     try{
				     LB= sh (script: """kubectl get svc ${K8S_DEPLOYMENT_NAME} -n ${NAMESPACE} --kubeconfig ${FILE} -o jsonpath="{ .status.loadBalancer.ingress[*]['ip', 'hostname']}" """, returnStdout: true).trim()
				     echo "${LB}"
                     sleep 40
				  	         container('zap') {
                        try{
                          sh 'pwd'
                          sh 'mkdir /zap/wrk'
                          sh ("/zap/zap-baseline.py -t 'http://${LB}' -g gen.conf -I -r webscan_${BUILD_ID}.html")
                        }catch(Exception e){
                          error("Build failed because of exceptions found in Owasp Scan")
                        }
                      sh 'cp /zap/wrk/webscan_${BUILD_ID}.html $(pwd)'
                      sh 'ls -lrt'
                      }
                    }catch(Exception e){
                        sh 'echo "error"'
                     }
                     sh 'echo "Check"'
                    }
                }
           }
    }

return this
