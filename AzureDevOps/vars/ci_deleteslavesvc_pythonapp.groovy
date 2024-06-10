def call(Map params) {
	stage("CI : ${params.repo} - Delete Jenkinslave Service") {
    def label = params.label
    container('kubectl') {
    sh """
        kubectl delete svc '${label}'
    """
    }
    }
    }

return this
