def call(Map params) {
    stage("CI : ${params.repo} - Build image"){
        
    def CONT_REGISTRY = params.cont_registry
    def NAMESPACE = params.namespace
    echo "${CONT_REGISTRY}"
    echo "Env Variable - ${NAMESPACE}"
        container('kaniko'){  
            sh("pwd")   
            sh("cd .") 
            sh (" /kaniko/executor --context `pwd` --tarPath image.tar --no-push --insecure-pull --cleanup --skip-tls-verify-pull --destination ${CONT_REGISTRY}")
        }
    }    
}

return this