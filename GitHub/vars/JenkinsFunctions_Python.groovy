// Below methods are used to build and test the python code with pip
def buildMethod() {
    println('pythonBuildMethod enter');
    //sh('mvn clean install package');
	sh ('pip install -r requirements.txt');
    sh ('pip install jake')
    sh ('jake -w ddt')
    sh ''' 
    jake -w ddt > jake-dependency-report.txt
    jake -w ddt | grep Vector: | grep -Eo "[0-9]+([.][0-9])?" | tee cvss.txt > /dev/null    
    apk add --update bash
    chmod +x threshold_check.sh
    ./threshold_check.sh
    '''
    println('pythonBuildMethod exit');
}

def testMethod() {
    println('pythonTestMethod enter');
    sh ('cp app.py tests');
    //sh ('python -m unittest -v tests/test_endpoints.py');
    sh '''
    coverage run -m unittest tests/test_endpoints.py
    coverage report -m
    coverage xml
    '''
    println('pythonTestMethod exit');
}

def sonarMethod() {
	println('Sonar Method enter');
    def scannerHome = tool 'SonarQube';
	sh "${scannerHome}/bin/sonar-scanner -Dsonar.login=$USERNAME -Dsonar.password=$PASSWORD";
    println('Sonar Method exit');
	
}

return this // Its important to return after all the functions.

