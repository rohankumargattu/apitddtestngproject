package tests;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultipleMethodsTakingDifferentDataFromOneDataProvider 
{
	@DataProvider(name="testdata")
	public Object[][] dataSource(Method m) throws Exception
	{
		Object[][] data=null;
		if(m.getName().equalsIgnoreCase("addition"))
		{
			data=new Object[][]
			{
				{12,45},
				{65,35},
				{68,15},
				{95,26}
			};
			//Return array
			return(data);
		}
		
		else if(m.getName().equalsIgnoreCase("concatination"))
		{
			data=new Object[][]
			{
				{"Gattu","Rohan"},
				{"Rohan","Kumar"},
				{"Gattu","Kumar"},
				{"Gattu","RohanKumar"}
			};
			//Return array
			return(data);
		}
		else
		{
			return data;
		}
	}
	
	@Test(priority=1,dataProvider="testdata")
	public void addition(int x,int y)
	{
		int z=x+y;
		System.out.println("Addition result is "+z);
	}
	
	@Test(priority=2,dataProvider="testdata")
	public void concatination(String x,String y)
	{
		String z=x+" "+y;
		System.out.println("Concatination result is "+z);
	}
}
