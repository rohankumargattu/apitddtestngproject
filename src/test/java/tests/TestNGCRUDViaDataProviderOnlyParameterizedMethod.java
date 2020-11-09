package tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestNGCRUDViaDataProviderOnlyParameterizedMethod
{
	//Declare Global objects
	RequestSpecification req;
	Response res;
	JSONObject jo;
	int sc;
	
	@DataProvider(name="testdata")
	public Object[][] dataMethod()
	{
		//Rows-No.of times the test has to be repeated
		//Columns-No.of parameters in test data
		Object[][] dparray=new Object[][] 
		{
			{11,101,"Priyanka","Eyyyy Optum Nadhee arthamaindha"},
			{11,102,"Geeta","Eyyyy Dubai Nadhe arthamaindha"},
			{11,103,"Shwetha","Paatttttt ani kodtha"},
			{11,104,"Riyaz","Why all are doing labalaba"},
			{11,105,"Ravi","Maa manager dimaak kharab chesthunadu"},
			{11,106,"Vinod","click on save for pom.xml"}
		};
		
		/*Object[][] data=new Object[6][4];
		//1st row
		data[0][0]=11;
		data[0][1]=101;
		data[0][2]="Priyanka";
		data[0][3]="Eyyyy Optum Nadhee arthamaindha";
		
		//2nd row
		data[1][0]=11;
		data[1][1]=102;
		data[1][2]="Geeta";
		data[1][3]="Eyyyy Dubai Nadhe arthamaindha";
		
		//3rd row
		data[2][0]=11;
		data[2][1]=103;
		data[2][2]="Shwetha";
		data[2][3]="Paatttttt ani kodtha";
		
		//4th row
		data[3][0]=11;
		data[3][1]=104;
		data[3][2]="Riyaz";
		data[3][3]="Why all are doing labalaba";
		
		//5th row
		data[4][0]=11;
		data[4][1]=105;
		data[4][2]="Ravi";
		data[4][3]="Maa manager dimaak kharab chesthunadu";
		
		//6th row
		data[5][0]=11;
		data[5][1]=106;
		data[5][2]="Vinod";
		data[5][3]="click on save for pom.xml";*/
		
		//Return Array
		return(dparray);
	}
	
	@BeforeMethod
	public void reusableCode()
	{
		//Register endpoint of Restful service under testing
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		//Define a default HTTP Request
		req=RestAssured.given();
	}
	
	@Test(priority=1,dataProvider="testdata")
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
