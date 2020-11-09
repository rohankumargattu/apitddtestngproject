package tests;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNGCRUDWithAllTestProperties 
{
	@Test(priority=1)
	public void method1()
	{
		Reporter.log("priority attribute utilization");
		Assert.assertTrue(true);
	}
	
	@Test(priority=2,enabled=false)
	public void method2()
	{
		Reporter.log("to skip method from execution, we can even use suite file with <exclude> tag");
		Assert.assertTrue(true);
	}
	
	@Test(priority=3,description="This attribute tells you about the method")
	public void method3()
	{
		Reporter.log("description attribute describes the finctionality of your method");
		Assert.assertTrue(true);
	}
	
	@Test(priority=4,timeOut=3000)
	public void method4() throws Exception
	{
		//Even the method is passed, if it takes more than the timeOut time TestNG can show as fail
		Thread.sleep(4000);
		System.out.println("Hi");
	}
	
	@Test(priority=5,invocationCount=5)
	public void method5()
	{
		System.out.println("By Invocation count");
	}
	
	@Test(priority=6,invocationCount=5,invocationTimeOut=5000)
	public void method6() throws Exception
	{
		//Even the method is passed, if it takes more than invocationTimeOut time 
		//TestNG can show as fail
		Thread.sleep(6000);
		System.out.println("By Invocation count with invocationTimeOut");
	}
	
	@Test(priority=7,expectedExceptions= {ArithmeticException.class})
	public void method7() 
	{
		//When known exception is raised, TestNG can pass the method
		int x=0;
		int y=1;
		int z=y/x;
		System.out.println("This Exception is already known");
	}
	
	@Test(priority=8,dependsOnMethods= {"method6"},alwaysRun=true)
	public void method8() 
	{
		System.out.println("method8 depends on method6 but still executed as it is alwaysRun=true");
	}
	
	@Test(priority=9,groups= {"smoketest"})
	public void method9() 
	{
		System.out.println("Connect to service");
	}
	
	@Test(priority=10,groups= {"smoketest","realtest"})
	public void method10() 
	{
		System.out.println("Send HTTP request");
	}
	
	@Test(priority=11,groups= {"realtest"})
	public void method11() 
	{
		System.out.println("get response");
	}
	
	@Test(priority=12,groups= {"regressiontest"})
	public void method12() 
	{
		System.out.println("regression group");
	}
	
	@Test(priority=13,dependsOnGroups= {"smoketest","realtest"})
	public void method13() 
	{
		System.out.println("Analyze response");
	}
}
