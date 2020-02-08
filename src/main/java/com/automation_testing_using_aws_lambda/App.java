package com.automation_testing_using_aws_lambda;

import org.testng.TestNG;

public class App 
{
    public static void main( String[] args )
    {
    	
    	try {
       
        // Calling test suite 
    	Class[] classes = new Class[] { TestSuite.class};
		TestNG testNG = new TestNG();
		testNG.setTestClasses(classes);
		testNG.run();
		
    	}catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
