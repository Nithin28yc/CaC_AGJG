package compliance.PageObjMethods;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.model.Log;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.filter.SendRequestFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import Generic.TestBase.restAssured_testBase;
import java.io.FileWriter;
import java.io.IOException;



public class DataInTransit_Encrypt {
	public static Response response;
	public static String jsonAsString;
	
	SoftAssert sa = new SoftAssert();
	
//	public void transitEncrypt(String username, String password) {
//		RestAssured.baseURI="https://caccontrolcheck.continuoustestplatform.com/";
//		RequestSpecification request = RestAssured.given();
//		Header acceptHeader = new Header("Accept","application/json");
//		Header contentTypeHeader = new Header("Content-Type","application/json");
//		List<Header> headers = new ArrayList<>();
//		headers.add(acceptHeader);
//		headers.add(contentTypeHeader);
//		
//		Headers allHeaders = new Headers(headers);
//			
//		String payload = "{\r\n" + 
//				 " \"username\": r\",\r\n" + 
//				 " \"password\": FL_\"\r\n" +
//				"}";
//		
//
//		request.header("Content_Type","application/json");
//		Response responseFromGenerateToken = request.body(payload).post("gplapi/login");
//		request.body(payload);
//		Response response = request.body(payload).headers(allHeaders).post("gplapi/login").then().log().body().extract().response();
//		Response ffh = request.post("gplapi/login");
//		System.out.println(response.getStatusCode());


//		Response responseFromGenerateToken = request.formParam("username", "yLd=").formParam("password", "F").post("gplapi/login");
//		responseFromGenerateToken.prettyPrint();
//		System.out.println(responseFromGenerateToken.getStatusLine());
//		System.out.println(responseFromGenerateToken.getStatusCode());
		
//		String jsonString = responseFromGenerateToken.getBody().asString();
//		String token = JsonPath.from(jsonString).get("access_token");
		
		//logger.log(LogStatus.INFO, "Sending Post request for getting token");
//		System.out.println("Sending Post request for getting token");
//		Response generateToken = request.formParam("username", "ethanadmin").formParam("password", "bGg")
//				.post("/gplapi/login");
//		generateToken.prettyPrint();
//		System.out.println(generateToken.getStatusLine());
//		System.out.println(generateToken.getStatusCode());
//		String jsonString = generateToken.getBody().asString();
//		String token = JsonPath.from(jsonString).get("access_token");
//	}


	public void transitEncrypt(String username,String password) throws IOException
	{

		//.config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()))
		RestAssured.useRelaxedHTTPSValidation();
		Response resp=RestAssured.
		given()
		.formParam("username", username)
		.formParam("password", password)
		.post("https://eactesting.continuoustestplatform.com/gqlapi/graphql");

    	//System.out.println(resp.jsonPath().prettify());
//		String token=resp.jsonPath().get("access_token");
	
		
//		Response response=RestAssured.
//				 given().config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()))
//				.auth()
//				.oauth2(token)
//				.get(jsonpath1);
		String msg = resp.getStatusLine();
		System.out.println("Code"+resp.getStatusCode());
		System.out.println("Code"+resp.getBody().asString());
		sa.assertEquals(resp.getStatusCode(), 401);
		if(msg.contains("40")) {
			System.out.println("Proper encrypted values need to be passed for fetching the token");
		}
				
	
		FileWriter fw = new FileWriter("./apiData/response.json");
		fw.write(msg);
		fw.write(resp.getBody().asString());
		fw.flush();
//		return response;
		
		
	}
	
}
