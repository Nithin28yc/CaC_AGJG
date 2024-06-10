def call(Map params) {
        def CONT_REGISTRY = params.cont_registry
        def label = params.label
        stage ("CI : ${params.repo} - trivy Scan") {
	        echo '------Trivy Scan---------'
		        container('trivy'){
                        sleep 20
                        sh ("pwd")
		        sh ("trivy image --severity CRITICAL --ignore-unfixed --ignorefile trivyignore --exit-code 1 --input image.tar")
                }
       }
}
return this
