package compliance.PageObjMethods;


import org.junit.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Generic.TestBase.ExcelOp;
import Generic.TestBase.restAssured_testBase;
import Generic.TestBase.testBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import jdk.jfr.internal.Logger;

import static io.restassured.RestAssured.*;

import java.io.FileWriter;
import java.io.IOException;

public class EncryptionOfData {
	public static Response response;
	public static String jsonAsString;


	//	@Test
	//	public void AuthResponse() throws IOException {
	//		
	//	ExcelOp.loadExcel("Testdata");
	////	logger.log(LogStatus.INFO, "Sending the get request for the data without passing token");
	//	System.out.println("Sending the get request for the data without passing token");
	//	
	//	RestAssured.baseURI="https://qeanalyticssit6.3.continuoustestplatform.com";
	//	RequestSpecification request = RestAssured.given();
	//
	//	int rowCount = ExcelOp.getRowCount("API_Endpoints_ProjectID");
	//	int rowCount1 = ExcelOp.getRowCount("API_Endpoints_Entity");
	//	System.out.println("No.of rows in projectid is "+rowCount);
	//	System.out.println("No. of rows in entity is " +rowCount1);
	//	
	//	for(int row=1; row<=rowCount; row++) {
	//		restAssured_testBase.projectid = ExcelOp.ReadExcelData("API_Endpoints_ProjectID", row, "projectid");
	//		String projectid = restAssured_testBase.projectid;
	//		for(int row1=1; row1<=rowCount1; row1++) {
	//			restAssured_testBase.entity = ExcelOp.ReadExcelData("API_Endpoints_Entity", row1, "entity");
	//			String entity = restAssured_testBase.entity;
	//			
	//			Response resp = request.get("/si/api?clientid=client1&projectid="+ projectid +"&entity="+ entity +"&from=2021-06-09");
	//
	//		    System.out.println(projectid +" , " + entity);
	//			System.out.println(resp.prettyPrint());
	//			System.out.println(resp.getStatusLine());
	//			Assert.assertEquals(resp.getStatusCode(), 401);
	//			
	//		}
	//	}
	//
	//}

	@Test
	public void response() throws IOException {

		//		String[] projectid = {"GitlabJava", "SIMongoTesting", "PythonAnalytics"};
		//		String[] entity= {"build", "codebranch", "codecommit", "deployment", "environment"};

		FileWriter file = new FileWriter("./apiData/data.json");

		//		for (String project:projectid) {
		//			for (String entityname:entity) {

		RestAssured.baseURI="https://dockerinsights8.0st.continuoustestplatform.com/";
		RequestSpecification request = RestAssured.given();

		//				logger.log(LogStatus.INFO, "Sending Post request for getting token");
		System.out.println("Sending Post request for getting token");
		Response generateToken = request.formParam("username", "admin").formParam("password", "admin@123")
				.post("/si/login?clientid=client1");
		generateToken.prettyPrint();

		String jsonString = generateToken.getBody().asString();
		String token = JsonPath.from(jsonString).get("access_token");

		//				logger.log(LogStatus.INFO, "Passing the token for authorization");
		System.out.println("Passing the token for authorization");
		request.header("Authorization","Bearer "+ token).header("Content-Type", "application/json");

		//				logger.log(LogStatus.INFO, "Sending the get request for the data");
		System.out.println("Sending the get request for the data");
		//				Response resp = request.get("/si/api?clientid=analytics&projectid="+ project +"&entity="+ entityname +"&from=2021-08-13");
		Response resp = request.get("/si/api?clientid=client1&entity=codecommit&projectid=SIMongoTesting&from=2022-06-23");

//		System.out.println(resp.prettyPrint());
		System.out.println(resp.getStatusLine());
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("Data is Encrypted");
		//				System.out.println("The entity name is "+entityname);

		//				file.write("The project id is "+project);
		//				file.write("\n");
		//				file.write("The entity name is "+entityname);
		//				file.write("\n");
		file.write(resp.prettyPrint());
		file.write("\n");
		file.write(resp.getStatusLine());
		//				file.write("\n");
		//			}
		//		}
		file.close();
	}


}
