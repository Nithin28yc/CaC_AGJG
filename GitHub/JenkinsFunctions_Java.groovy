// Below methods are used to build and test the java code with Maven
def sonarMethod() {
	println('Sonar Method enter');
    def scannerHome = tool 'SonarQube';
//   sh "${scannerHome}/bin/sonar-scanner";
     sh "${scannerHome}/bin/sonar-scanner -Dsonar.login=$USERNAME -Dsonar.password=$PASSWORD";
	println('Sonar Method exit');
	
}

return this // Its important to return after all the functions.
