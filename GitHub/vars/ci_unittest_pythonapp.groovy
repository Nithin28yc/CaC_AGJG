def call(Map params) {
    stage('CI : Load requirements') {
        container("python") {
          sh 'pip install -r source/requirements.txt'
          }
         }
        stage("CI : ${params.repo} - Unit Test"){
            def TEST_FILENAME = params.filename
            def NAMESPACE = params.namespace
            try{
                withEnv(["NAMESPACE=${NAMESPACE}"]) {
                  echo "NAMESPACE - ${NAMESPACE}"
                  container("python"){
                        sh "ls -ll"
                        sh ("cp source/app.py vars/ci/unit_test");
                        sh ("python -m unittest -v vars/ci/unit_test/${TEST_FILENAME}");
                        println('pythonTestMethod exit');
                        // sh "chmod +x ./vars/ci/unit_test/${TEST_FILENAME}"
                        // sh "python ./vars/ci/unit_test/${TEST_FILENAME}"
            }
                }
                    
            }
            catch(Exception error){
            sh "echo error"
            }

        }

}
return this
