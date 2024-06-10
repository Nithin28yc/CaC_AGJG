def allowedPattern() {
        sh '''
        git secrets --add --allowed --literal 'ex@mplepassword'
		git secrets --add --allowed --literal '^$'
		git secrets --add --allowed --literal 'sooper secret'
		git secrets --add --allowed --literal 'process.env.MYSQL_ENV_MYSQL_ROOT_PASSWORD'
		'''
}
def nonAllowedPattern() {
        sh '''
        git secrets --add '[^a-zA-Z\\s+]password\\s*(=|:)\\s*.+',
		git secrets --add 'credential\\s*(=|:)\\s*.+'
		git secrets --add '^key\\s*(=|:)\\s*.+'
		git secrets --add 'userid\\s*(=|:)\\s*.+'
		git secrets --add 'access\\s*(=|:)\\s*.+'
		git secrets --add 'secret\\s*(=|:)\\s*.+'
		git secrets --add 'https?:[a-zA-Z0-9!@#$&()\\-`.+,/\"\\S]+:[a-zA-Z0-9!@#$&()\\-`.+,/\"\\S]+@[a-zA-Z0-9\\-`.+,\\S]+'
		'''
		
}
def call(Map stageParams){

 stage("CI : ${stageParams.repo} - GIT Secret Scann") {
	    container('git-secrets') {
    allowedPattern()
    nonAllowedPattern()
	sh 'git secrets --scan'
		}
	}
}

