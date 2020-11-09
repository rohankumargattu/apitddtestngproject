package tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class OptionalAnnotation
{
	@Test(priority=1)
	@Parameters({"input1","input2"})
	public void hi(@Optional("Gaurav") String x,@Optional("cricket team") String y)
	{
		String z=x+"-->"+y;
		System.out.println(z);
	}
}


