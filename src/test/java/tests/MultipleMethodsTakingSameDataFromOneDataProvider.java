package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultipleMethodsTakingSameDataFromOneDataProvider 
{
	@DataProvider(name="testdata")
	public Object[][] dataSource()
	{
		Object[][] data=new Object[][]
		{
			{12,45},
			{65,35},
			{68,15},
			{95,26}
		};
		return(data);
	}
	
	@Test(priority=1,dataProvider="testdata")
	public void addition(int x,int y)
	{
		int z=x+y;
		System.out.println("Addition result is "+z);
	}
	
	@Test(priority=2,dataProvider="testdata")
	public void subtraction(int x,int y)
	{
		int z=x-y;
		System.out.println("Subtraction result is "+z);
	}
	
	@Test(priority=3,dataProvider="testdata")
	public void multiplication(int x,int y)
	{
		int z=x*y;
		System.out.println("Multiplication result is "+z);
	}
	
	@Test(priority=4,dataProvider="testdata")
	public void division(int x,int y)
	{
		int z=x/y;
		System.out.println("Division result is "+z);
	}
}
