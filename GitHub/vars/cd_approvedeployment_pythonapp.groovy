def call(Map params) {
  stage("CD : ${params.repo} - Approve deployment"){
        repo = params.repo
         container('curl'){
            String BUILD_NO = "${env.BUILD_NUMBER}"
            def now = new Date()
            String DATE = "**"+now.format("dd/MM/yyyy  HH:mm")+"**"
            String ENV = "***${params.namespace}***"
            String JOB_N = "[**Link to the view pipeline**](https://multiclouddemo.gvsmb.com/jenkinscore/job/${env.JOB_NAME}/)"
            String Approve_link = "[**Link to the Approve deployment**](https://multiclouddemo.gvsmb.com/jenkinscore/job/${env.JOB_NAME}/${env.BUILD_NUMBER}/input/)"
            jsonfile = readJSON file: './vars/configurations/approve_build.json'
            jsonfile['sections'][0]['facts'][0]['value'] = BUILD_NO
            jsonfile['sections'][0]['facts'][1]['value'] = DATE
            jsonfile['sections'][0]['facts'][2]['value'] = ENV
            jsonfile['sections'][0]['facts'][3]['value'] = JOB_N
            jsonfile['sections'][0]['facts'][4]['value'] = Approve_link
            writeJSON file: './edited_build.json', json: jsonfile
            sh "cat ./edited_build.json"

            sh "ls -ll"
            
            sh """ curl -X POST -H 'Content-Type: application/json' -d @./edited_build.json  https://outlook.office.com/webhook/3c5021a8-c28d-497e-ba25-e0560ca16ca6@e0793d39-0939-496d-b129-198edd916feb/IncomingWebhook/98eba2f2d2a24c678bd366dcdbad3170/a945acbd-b036-45aa-9fff-117b4c98f44b"""
         }
         
         def userInput = timeout(time: 3600, unit: 'SECONDS') { input(
                            id: 'userInput', message: "Approve deployment of ${repo}",
                            parameters: [
                                    string(defaultValue: 'Yes',
                                            description: 'Proceed with Deployment? (yes/no) - ',
                                            name: 'Proceed')
                            ]) }
            echo "Agreed to deploy - ${userInput}"
                            

    }
    

}
