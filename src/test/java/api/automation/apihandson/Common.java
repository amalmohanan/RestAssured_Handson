package api.automation.apihandson;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

public class Common {
	
	public static Response getResponse(String method,String apiEndpoint,String username,String body) {
		baseURI="https://petstore.swagger.io/v2";
		Response res=null;
		
		switch (method) {
		case "GET":
			res=given().when().get(apiEndpoint+"/"+username);
			break;
		case "POST":
			res=given().header("Content-Type", "application/json").body(body).when().post(apiEndpoint);
			break;
		case "PUT":
			res=given().header("Content-Type", "application/json").body(body).when().put(apiEndpoint+"/"+username);
			break;
		case "DELETE":
			res=given().when().delete(apiEndpoint+"/"+username);
			break;
		default:
			break;
		}
		return res;
	}

}
