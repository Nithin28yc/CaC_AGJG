def call(Map params) {
 stage("CT : ${params.repo} - Test from Demo repository"){
     def CREDENTIALS_ID = params.credentialid
                   container('maven'){
        		      sh 'apk update'
        		      sh 'apk add git'
                      sh  'git config --global http.sslVerify "false" '
					  withCredentials([[$class: 'UsernamePasswordMultiBinding',
						credentialsId: "${CREDENTIALS_ID}",
						usernameVariable: 'USERNAME',
						passwordVariable: 'PASSWORD']]) {
							sh "git submodule add -b master https://${USERNAME}:${PASSWORD}@gitlab.ethan.svc.cluster.local:8084/gitlab/root/demo.git"
						}
                     try    {
        					sh 'ls -lart'
        					sh '''
        					     cd demo
        					     git branch
        					     ls -ll
        					     mvn test -DsuiteXmlFile=Demo.xml
        					   '''
        					sh "ls -ll"
        					}
        					
        					catch(Exception e)
        					{
        					sh 'echo e'
        					}
        					finally
                            {
                                 sh 'echo e'  
                            } 
                }						
        
        		}

}
