package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

public class TestNGCRUDViaDataProviderExcelOnlyParameterizedMethod
{
	//Declare Global objects
	RequestSpecification req;
	Response res;
	JSONObject jo;
	int sc;
	
	@DataProvider(name="testdata")
	public Object[][] dataMethod() throws Exception
	{
		//Read data from excel
		File f=new File("TestNGCRUDViaDataProviderExcelOnlyParameterizedMethod.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("Sheet1");
		int nour=sh.getPhysicalNumberOfRows();
		int nouc=sh.getRow(0).getLastCellNum();
		Object[][] data=new Object[nour-1][nouc]; 
		//Data Driven from 2nd row(index=1)
		for(int i=1;i<nour;i++)
		{
			//Read data from excel
			DataFormatter df=new DataFormatter();
			data[i-1][0]=Integer.parseInt(df.formatCellValue(sh.getRow(i).getCell(0)));
			data[i-1][1]=Integer.parseInt(df.formatCellValue(sh.getRow(i).getCell(1)));
			data[i-1][2]=df.formatCellValue(sh.getRow(i).getCell(2));
			data[i-1][3]=df.formatCellValue(sh.getRow(i).getCell(3));		
		}
		fi.close();
		wb.close();
		return(data);
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
