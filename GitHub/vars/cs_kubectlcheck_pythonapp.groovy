def call(Map params) {
    stage ("CS : ${params.repo} - Kubectl-Check")
    		   {
                     def SECRET_NAME = 'secret-' + params.provisionid
                     def GITLAB_REPO_URL = params.gitlabrepourl
                     def NAMESPACE = params.namespace
                     withCredentials([file(credentialsId: "${SECRET_NAME}", variable: 'FILE')]) {
    			   container('kubectl')
    			   {
    		   //sh 'kubectl get node  >> nodes.txt'
    		    //sh 'kubectl auth can-i list nodes'
    		  
    		   sh 'kubectl get nodes --kubeconfig ${FILE} >> nodes.txt'
    		    sh label: '', script: '''
    		    if grep -q master nodes.txt; then
    			echo "master found" >> master.txt
    			else
    			echo not found
    		    fi
    		   echo cat master.txt
    		   '''
    			   }
    		   
    		  if (fileExists('master.txt'))
    		  {
    		
    			
    			echo " It is  non managed kubernetes service hence executing kube audit and kube bench stages"
    			stage("CS : ${params.repo} - Kube-Bench Scan") { 
        		    container('kubectl') {		
        		    sh '''
					v=`kubectl version --short --kubeconfig ${FILE} | grep -w 'Server Version'`
					v1=`echo $v | awk -F: '{print $2}'`
					v2=`echo $v1 | sed 's/v//g'`
					versionserver=`echo $v2 | cut -f1,2 -d'.'`
					echo kubernetes version is ${versionserver}
                                        echo ${GITLAB_REPO_URL}| awk -F/ '{print $3}' > master_url.txt
                                        export DNSNAME="$(cat master_url.txt)"
                                        echo DNS name is $DNSNAME
        		    kubectl run --rm -i kube-bench-master-python-${BUILD_ID} --kubeconfig ${FILE} --image=${DNSNAME}/root/docker_registry/aiindevops.azurecr.io/kube-bench:0.6 -n ${NAMESPACE} --restart=Never --overrides="{ \\"apiVersion\\": \\"v1\\", \\"spec\\": { \\"imagePullSecrets\\": [ { \\"name\\": \\"gitlab-auth\\" } ], \\"hostPID\\": true, \\"tolerations\\": [ { \\"key\\": \\"node-role.kubernetes.io/master\\", \\"operator\\": \\"Exists\\", \\"effect\\": \\"NoSchedule\\" } ] } }" -- master --version ${versionserver}
        		    kubectl run --rm -i kube-bench-node-python-${BUILD_ID} --kubeconfig ${FILE} --image=${DNSNAME}/root/docker_registry/aiindevops.azurecr.io/kube-bench:0.6 -n ${NAMESPACE} --restart=Never --overrides="{ \\"apiVersion\\": \\"v1\\", \\"spec\\": { \\"imagePullSecrets\\": [ { \\"name\\": \\"gitlab-auth\\" } ], \\"hostPID\\": true } }" -- node --version ${versionserver}
        		    '''
        			}       
    			}
    		  
                
                stage("CS : ${params.repo} - Kube-Audit Scan") {
    		          container('kubeaudit') {		
    		          sh 'kubeaudit -a allowpe'
    		        }
                }
    		}
    		else
    		{
    			echo " It is Managed Kubernetes service hence executing kubebench and kube-audit only on nodes "
    			stage("CS : ${params.repo} - Kube-Bench Scan") { 
    		    container('kubectl') {	
    			
    			sh '''
				v=`kubectl version --short --kubeconfig ${FILE} | grep -w 'Server Version'`
				v1=`echo $v | awk -F: '{print $2}'`
				v2=`echo $v1 | sed 's/v//g'`
				versionserver=`echo $v2 | cut -f1,2 -d'.'`
				echo kubernetes version is ${versionserver}
                                echo ${GITLAB_REPO_URL}| awk -F/ '{print $3}' > node_url.txt
                                export DNSNAME="$(cat node_url.txt)"
                                echo DNS name afer export is $DNSNAME
    			        kubectl run --rm -i kube-bench-node-python-${BUILD_ID} --kubeconfig ${FILE} --image=${DNSNAME}/root/docker_registry/aiindevops.azurecr.io/kube-bench:0.6 -n ${NAMESPACE} --restart=Never --overrides="{ \\"apiVersion\\": \\"v1\\", \\"spec\\": { \\"imagePullSecrets\\": [ { \\"name\\": \\"gitlab-auth\\" } ], \\"hostPID\\": true } }" -- node --version ${versionserver}
    			'''
    			}
    			}
    			  stage("CS : ${params.repo} - Kube-Audit Scan") {
    		          container('kubeaudit') {		
    		          sh 'kubeaudit -a allowpe'
    		        }
    		   }
    		   }
    		   }
        }
    }

return this
