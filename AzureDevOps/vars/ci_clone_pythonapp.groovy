def call(Map params) {
    stage("CI : ${params.repo} - Clone "){
    echo "will be cloning git repo"
    def URL = params.url
    def BRANCH = params.branch
    def CREDS = params.creds
    git url : "${URL}", branch : "${BRANCH}", credentialsId : "${CREDS}" 
    }
}
