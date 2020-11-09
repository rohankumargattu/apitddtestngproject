package tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestNGCRUDViaDataProviderClassOnlyParameterizedMethod
{
	//Declare Global objects
	RequestSpecification req;
	Response res;
	JSONObject jo;
	int sc;
	
	@BeforeMethod
	public void reusableCode()
	{
		//Register endpoint of Restful service under testing
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		//Define a default HTTP Request
		req=RestAssured.given();
	}
	
	@Test(priority=1,dataProviderClass=TestNGCRUDViaDataProviderOnlyParameterizedMethod.class,dataProvider="testdata")
	public void createAPost(int x,int y,String z,String w)
	{
		jo=new JSONObject();
		jo.put("userId",x);
		jo.put("id",y);
		jo.put("title",z);
		jo.put("body",w);
		req.header("Content-Type","application/json");
		req.body(jo.toString());
		//Define a default HTTP Request
		res=req.request(Method.POST,"/posts");
		sc=res.getStatusCode();
		if(sc==201)
		{
			Reporter.log("Post created succesfully");
			Assert.assertTrue(true);
		}
		else
		{
			Reporter.log("Post not created");
			//Assert.assertTrue(false);
			Assert.fail();
		}
	}
}
