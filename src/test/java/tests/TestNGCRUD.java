package tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestNGCRUD 
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
	
	@Test(priority=1)
	public void getAllPosts()
	{
		res=req.request(Method.GET,"/posts");
		sc=res.getStatusCode();
		if(sc==200)
		{
			Reporter.log("All posts resource working correctly");
			Assert.assertTrue(true);
		}
		else
		{
			Reporter.log("All posts resource NOT working correctly");
			Assert.assertTrue(false);
			//Assert.fail();
		}
	}
	
	@Test(priority=2)
	public void getSpecificPost()
	{
		res=req.request(Method.GET,"/posts/1");
		sc=res.getStatusCode();
		JsonPath jp=res.jsonPath();
		String id=jp.getString("id");
		if(sc==200 && id.equals("1"))
		{
			Reporter.log("Specific post resource working correctly");
			Assert.assertTrue(true);
		}
		else
		{
			Reporter.log("Specific post resource NOT working correctly");
			Assert.assertTrue(false);
			//Assert.fail();
		}
	}
	
	@Test(priority=3)
	public void createAPost()
	{
		jo=new JSONObject();
		jo.put("userId",11);
		jo.put("id",101);
		jo.put("title","TestNG framework");
		jo.put("body","first practice");
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
	
	@Test(priority=4)
	public void updateAPost()
	{
		jo=new JSONObject();
		jo.put("userId",11);
		jo.put("id",101);
		jo.put("title","TestNG framework");
		jo.put("body","second practice");
		req.header("Content-Type","application/json");
		req.body(jo.toString());
		res=req.request(Method.PUT,"/posts/101");
		sc=res.getStatusCode();
		if(sc==200)
		{
			Reporter.log("Post updated succesfully");
			Assert.assertTrue(true);
		}
		else
		{
			Reporter.log("Post not updated");
			//Assert.assertTrue(false);
			Assert.fail();
		}
	}
	
	@Test(priority=5)
	public void deleteAPost()
	{
		res=req.request(Method.DELETE,"/posts/101");
		sc=res.getStatusCode();
		if(sc==200)
		{
			Reporter.log("Post deleted succesfully");
			Assert.assertTrue(true);
		}
		else
		{
			Reporter.log("Post not deleted");
			Assert.assertTrue(false);
			Assert.fail();
		}
	}
}
