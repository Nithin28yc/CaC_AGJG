def call(Map params) {
    stage("CI : ${params.repo} - SonarQube Analysis"){
				withCredentials([usernamePassword(credentialsId: 'sonar-creds', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
			   container('jq') 
				{
				 	sh '''
			export SONAR_PROJECT_KEY='hello-world-python'
			export SONAR_QG_NAME='python_QG'
			apt update
			apt install curl --force-yes -y
			echo $SONAR_PROJECT_NAME
			echo $SONAR_PROJECT_KEY
			echo $SONAR_QG_NAME
			echo "Creating sonar gateway"
			curl -u ${USERNAME}:${PASSWORD} -k -X POST "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/create?name=$SONAR_QG_NAME"
			curl -u ${USERNAME}:${PASSWORD} -k "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/show?name=$SONAR_QG_NAME" > qualitygate.json
		    QG_ID=$(cat qualitygate.json | jq -r ".id")
		    curl -u ${USERNAME}:${PASSWORD} -k -X POST "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/create_condition?gateId=$QG_ID&metric=coverage&op=LT&error=80"
			curl -u ${USERNAME}:${PASSWORD} -k -X POST "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/create_condition?gateId=$QG_ID&metric=duplicated_lines_density&op=GT&error=10"
			export SONAR_PROJECT_NAME='hello-world-python'
			curl -u ${USERNAME}:${PASSWORD} -k -X POST "http://sonar.ethan.svc.cluster.local:9001/sonar/api/projects/create?project=$SONAR_PROJECT_KEY&name=$SONAR_PROJECT_NAME"
			curl -u ${USERNAME}:${PASSWORD} -k "http://sonar.ethan.svc.cluster.local:9001/sonar/api/projects/search?" > project.json
			ls -lrta
			cat qualitygate.json
			cat project.json
			SONAR_PROJECT_ID=$(cat project.json | jq -r \'.components[] | select(.name=="'$SONAR_PROJECT_NAME'") | .id\')
			echo $QG_ID
			echo $SONAR_PROJECT_ID
			curl -u ${USERNAME}:${PASSWORD} -k -X POST "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/select?gateId=$QG_ID&projectKey=$SONAR_PROJECT_KEY"
			curl -u ${USERNAME}:${PASSWORD} -k "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/show?name=$SONAR_QG_NAME" > qualitygate.json
			echo "Updating sonar gateway"
			cat qualitygate.json | jq -r ".conditions[].id" > cgid.txt
            cgid1=$(head -n 1 cgid.txt)
            echo $cgid1
            curl -u ${USERNAME}:${PASSWORD} -k -X POST "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/update_condition?gateId=$QG_ID&id=$cgid1&metric=coverage&op=LT&error=0"
            cgid2=$(sed -n '2p' cgid.txt)
            echo $cgid2
            curl -u ${USERNAME}:${PASSWORD} -k -X POST "http://sonar.ethan.svc.cluster.local:9001/sonar/api/qualitygates/update_condition?gateId=$QG_ID&id=$cgid2&metric=duplicated_lines_density&op=GT&error=80"
			'''
			  }
				container('maven') {
				def function = load "${WORKSPACE}/vars/JenkinsFunctions_Python.groovy"
				sh '''
				apt-get update
				apt-get install -y nodejs
				cp ${WORKSPACE}/vars/ci/sonarqube/jenkins_sonar-project.properties ${WORKSPACE}/sonar-project.properties
				'''
		withSonarQubeEnv('SonarQube') {
        println('Sonar Method enter');
				function.sonarMethod()
        echo "Access the SonarQube URL from the Platform Dashboard tile"
				sh '''
				sleep 30
				export SONAR_PROJECT_NAME='jenkins-proj'
				curl -k -u ${USERNAME}:${PASSWORD} "${SONAR_HOST_URL}/api/measures/component?metricKeys=critical_violations,coverage,major_violations,blocker_violations&component=$SONAR_PROJECT_NAME" > sonar-result.json
				cat sonar-result.json
				'''
               }
		}
             }
    	}
}
return this
