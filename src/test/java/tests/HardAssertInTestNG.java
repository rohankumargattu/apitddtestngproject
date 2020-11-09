package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class HardAssertInTestNG
{
	@Test(priority=1)
	public void method1()
	{
		System.out.println("Hie");
		Reporter.log("Test Passed");
		Assert.assertTrue(true);
		System.out.println("Bye");
	}
	
	@Test(priority=2)
	public void method2()
	{
		System.out.println("Good Morning");
		Reporter.log("Test Failed");
		Assert.assertTrue(false);
		//Further code cannot run
		System.out.println("Good Night");
	}
}
