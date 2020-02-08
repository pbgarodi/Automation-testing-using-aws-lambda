package com.automation_testing_using_aws_lambda;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.testng.TestNG;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class LambdaHandler implements RequestHandler<String, String> {

	public String handleRequest(String input, Context context) {
    	
	  	try {
	  	  
	    	// Code to download serveless chrome from given s3 bucket     	
	        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
	        String bucketName = "automation-testing";
	        S3Object fullObject = s3Client.getObject(new GetObjectRequest(bucketName, "headless_chrome.zip"));
	        S3ObjectInputStream inputStream = fullObject.getObjectContent();
	        FileUtils.copyInputStreamToFile(inputStream, new File("/tmp/" + "headless_chrome.zip"));
	        
	        // Extract .zip file in /tmp directory of lambda
	        File archive = new File("/tmp/headless_chrome.zip");
	        File destination = new File("/tmp/");
	        Archiver archiver = ArchiverFactory.createArchiver("zip");
	        archiver.extract(archive, destination);
	       
	        // Calling test suite 
	    	Class[] classes = new Class[] { TestSuite.class};
			TestNG testNG = new TestNG();
			testNG.setTestClasses(classes);
			testNG.run();
			
			// Upload a summary report on s3 bucket
			String s3Path = "automation_testing_report"+"/test_summary_report.json";
			String reportPath = "test-output/testng-results.xml";
			PutObjectRequest putRequest = new PutObjectRequest(bucketName,s3Path, new File(reportPath));
			s3Client.putObject(putRequest);
			
			System.out.println("Report uploaded to s3");
			
	    	}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		return null;
	}

}
