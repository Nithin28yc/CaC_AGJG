def call(Map params) {
    	   stage("CT :  ${params.repo} - Continous testing"){
			   def SECRET_NAME = 'secret-' + params.provisionid
            container("python"){
				sh 'apk update'
				sh 'apk add git'             
				sh 'git config --global http.sslVerify "false" '
				sh 'apk add --update --no-cache py3-pip'
				sh 'pip3 install --upgrade pip'
				sh 'apk add build-base'
				sh 'apk add libffi-dev'
				sh 'pip3 install selenium==4.0.0'
				sleep 30
				withCredentials([file(credentialsId: "${SECRET_NAME}", variable: 'FILE')]) {
					container('kubectl') {
    				try{
        			    withEnv(["ELB=${sh (script: """kubectl get svc ${params.deployment_name} -n ${params.namespace} --kubeconfig ${FILE} -o jsonpath="{ .status.loadBalancer.ingress[*]['ip', 'hostname']}" """, returnStdout: true).trim()}"]) {
                                 sh ("kubectl get pods -n ${params.namespace} --kubeconfig ${FILE}")
                                 sh ("kubectl get svc -n ${params.namespace} --kubeconfig ${FILE}")
            
                    			echo "${ELB}"
                			    
                    			container("python"){
                                sh '''
                                     ls -lart
                                     chmod +x ./vars/ct/Tests/ct_sampleapp.py
                				     python ./vars/ct/Tests/ct_sampleapp.py
        				        '''
        			        }
        			    }
        			 }
        			catch(Exception error){
        			    sh "echo error"
        			}
			    
			        
			    }
				}
			    
    }
}
}

return this
