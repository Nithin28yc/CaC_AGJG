def call(Map params) {
stage("CI : ${params.repo} - MongoDB Instance Creation"){
              def NAMESPACE = params.namespace
              def MONGO_DEPLOYMENT = params.mongo_deployment
              def CREDENTIAL_ID = params.credentialid
              def GCR_HUB_ACCOUNT = params.gcraccount
              def DNS = params.dns
              def SECRET_NAME = 'secret-' + params.provisionid
              sh "sed -i 's/dns/${DNS}/g' source/mongo.yaml"
              withCredentials([file(credentialsId: "${SECRET_NAME}", variable: 'FILE')]) {
                  container('kubectl'){
                      try{
                          sh ("kubectl get ns ${NAMESPACE} --kubeconfig ${FILE}")
                          if(true){
                            try{
                              sh ("kubectl get secrets/gitlab-auth -n ${NAMESPACE} --kubeconfig ${FILE}")
                              if(true){
                                  echo "secret gitlab-auth present"
                              }
                             else{
                               withCredentials([[$class: 'UsernamePasswordMultiBinding',
                                  credentialsId: "${CREDENTIAL_ID}",
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {
                                      sh "kubectl create secret docker-registry gitlab-auth --docker-server=${GCR_HUB_ACCOUNT} --docker-username=${USERNAME} --docker-password=${PASSWORD} -n ${NAMESPACE} --kubeconfig ${FILE}"
                                      }
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
                          sh ("kubectl get deployment/mongopy -n ${NAMESPACE} --kubeconfig ${FILE}")
                          if (true){
                              sh ("kubectl set image deployment/${MONGO_DEPLOYMENT} ${MONGO_DEPLOYMENT}=${GCR_HUB_ACCOUNT}/${REPO}/mongo:3.4 -n ${NAMESPACE} --kubeconfig ${FILE}")
                              sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                              sh ("kubectl get svc mongopy -n ${NAMESPACE} --kubeconfig ${FILE}")
                          }
                          else{
                            sh("kubectl apply -f source/mongo.yaml -n ${NAMESPACE} --kubeconfig ${FILE}")
                            sleep 20
                            sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                            sh ("kubectl get svc mongopy -n ${NAMESPACE} --kubeconfig ${FILE}")
                          } 
                      }
                      catch(e){
                          sh "ls -ll"
                          echo "Deploying Mongo Instance in ${NAMESPACE}"
                          sh("kubectl apply -f source/mongo.yaml -n ${NAMESPACE} --kubeconfig ${FILE}")
                          sleep 20
                          sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                          sh ("kubectl get svc mongopy -n ${NAMESPACE} --kubeconfig ${FILE}")
                      }
                  }
              }
                    catch(e){
                        sh ("kubectl create ns ${NAMESPACE} --kubeconfig ${FILE}")
                        withCredentials([[$class: 'UsernamePasswordMultiBinding',
                            credentialsId: "${CREDENTIAL_ID}",
                            usernameVariable: 'USERNAME',
                            passwordVariable: 'PASSWORD']]) {
                                sh "kubectl create secret docker-registry gitlab-auth --docker-server=${GCR_HUB_ACCOUNT} --docker-username=${USERNAME} --docker-password=${PASSWORD} -n ${NAMESPACE} --kubeconfig ${FILE}"
                            }
                        sh "ls -ll"
                        sleep 3
                        sh("kubectl apply -f source/mongo.yaml -n ${NAMESPACE} --kubeconfig ${FILE}")
                        echo "Deploying MONGO Instance"
                        sleep 20 //seconds
                        sh ("kubectl get pods -n ${NAMESPACE} --kubeconfig ${FILE}")
                        sh ("kubectl get svc mongopy -n ${NAMESPACE} --kubeconfig ${FILE}")
                        
                        }
                }
              }
          }
  }
  return this