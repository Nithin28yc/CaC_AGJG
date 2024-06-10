def call(Map params) {
    stage("CI : ${params.repo} - Push image") {
    def GCR_HUB_ACCOUNT = 'https://' + params.gcraccount 
    def CREDENTIALS_ID = params.credentialid
    def CONT_REGISTRY = params.cont_registry
    echo "CONT_REGISTRY -- ${CONT_REGISTRY}"
        container('kaniko') {
            sh("pwd")
            sh("cd .")
            //sleep 200
            sh ("/kaniko/executor --context `pwd` --insecure-pull --cleanup --skip-tls-verify-pull --insecure --skip-tls-verify --destination ${CONT_REGISTRY}")
        }

	}

	}


return this
