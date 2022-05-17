package api.automation.apihandson;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;


import io.restassured.response.Response;


import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class ApiTest {
	
	@Test//(dependsOnMethods="postMethodWithEscapeChar")
	public void getMethod() {
		
		Response res=Common.getResponse("GET", "/user","Amal","");
		Assert.assertEquals(200, res.getStatusCode());
		System.out.println(res.asString());
		
	}
	
	@Test
	public void postMethodWithEscapeChar() {
		
		String body="{\"id\":101,\"username\":\"Amal\",\"firstName\":\"amal\",\"lastName\":\"m\",\"email\":\"a@b.com\",\"password\":\"tgvsx\",\"phone\":\"5454545\",\"userStatus\":0}";
		Response res=Common.getResponse("POST", "/user","",body);
		Assert.assertEquals(200, res.getStatusCode());
		Assert.assertEquals(200, res.jsonPath().getInt("code"));
		
	}
	@Test
	public void postMethodWithJsonFile() throws IOException {

		FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"\\Files\\body.json"));
		String body=IOUtils.toString(file);
		Response res=Common.getResponse("POST", "/user","",body);
		Assert.assertEquals(200, res.getStatusCode());
		Assert.assertEquals(200, res.jsonPath().getInt("code"));
		
	}
	
	@Test(dependsOnMethods="getMethod")
	public void putMethodWithEscapeChar() {
		
		String body="{\"id\":101,\"username\":\"NewAmal\",\"firstName\":\"amal\",\"lastName\":\"m\",\"email\":\"amal@m.com\",\"password\":\"tgvsx\",\"phone\":\"5454545\",\"userStatus\":0}";
		Response res=Common.getResponse("PUT", "/user","Amal",body);
		Assert.assertEquals(200, res.getStatusCode());

	}
	
	@Test
	public void putMethodWithJsonObject() throws IOException {
		
		JSONObject obj=new JSONObject();
		obj.put("id", 122);
		obj.put("username", "NewAppus");
		obj.put("firstName", "appu");
		obj.put("lastName", "m");
		obj.put("email", "Newapp@b.com");
		obj.put("password", "cscs");
		obj.put("phone", "9658456");
		obj.put("userStatus", 0);
		String body=obj.toJSONString();
		Response res=Common.getResponse("PUT", "/user","appu",body);
		Assert.assertEquals(200, res.getStatusCode());
		
	}
	
	@Test(dependsOnMethods="putMethodWithJsonObject")
	public void deleteMethod() {
		
		Response res=Common.getResponse("DELETE", "/user","NewAmal","");
		Assert.assertEquals(200, res.getStatusCode());
		
	}
	@Test
	public void getMethod404() {
		
		Response res=Common.getResponse("GET", "/user","xyz","");
		Assert.assertEquals(404, res.getStatusCode());
		
	}
	@Test
	public void deleteMethod404() {
		
		Response res=Common.getResponse("DELETE", "/user","yyyy","");
		Assert.assertEquals(404, res.getStatusCode());
		
	}
	
}
