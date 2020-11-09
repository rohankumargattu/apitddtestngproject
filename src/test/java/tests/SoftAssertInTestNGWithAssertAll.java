package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertInTestNGWithAssertAll
{
	@Test(priority=1)
	public void method1()
	{
		System.out.println("Hie");
		Reporter.log("Test Passed");
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(true);
		System.out.println("Bye");
	}
	
	@Test(priority=2)
	public void method2()
	{
		System.out.println("Good Morning");
		Reporter.log("Test Failed");
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(false);
		//Further code can run, Here even though the method is declared as fail,
		//SoftAssert can consider it as passed and continue further execution
		System.out.println("Good Night");
		//At the end of method, Pass are considered as pass and fail are considered as fail
		sa.assertAll();
	}
}
