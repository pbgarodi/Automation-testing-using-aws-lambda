# Automation testing using AWS Lambda

This is a Unit test case suite written in Java language and uses TestNG testing framework for writing unit test cases.

## Getting Started
- These instructions will get you a copy of the project up and running on AWS lambda for UI testing. 
- In this project we are testing linkedin login.

### Prerequisites
- Aws Account
- S3 bucket 
- AWS lambda function
- Serverless Chrome (Note: Given in resources directory)
- Maven

##### If Maven not install follow following steps
- Open terminal and execute following command.
- $ sudo apt update
- $ sudo apt install maven

### Input
- Create a bucket name as 'serverless-artifacts'.
- Once bucket created then copy all file from resources folder to newly 	 created S3 bucket.
- Create lambda function and Configure test event to trigger lambda 

### How to create deployment package
In order to create deployment package we required following tool
- To create .jar file run following command in directory where pom.xml file located.
- $ maven clean install
- This command will generate two jar files one with dependency and another one without dependency in **target** directory.
- Upload automation-testing-using-aws-lambda-0.0.1-jar-with-dependencies.jar in lambda function.

### Code explanation
In Order to run selenium testing suite in AWS lambda you need to set following option for Chrome browser.

```
ChromeOptions options = new ChromeOptions();
options.addArguments("headless");
options.addArguments("window-size=1366,768");
options.addArguments("--single-process");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--no-sandbox");
options.addArguments("--user-data-dir=/tmp/user-data");
options.addArguments("--data-path=/tmp/data-path");
options.addArguments("--homedir=/tmp");
options.addArguments("--disk-cache-dir=/tmp/cache-dir");
```

### How ro run 
You can test lambda function working or not by configuring test event
And Hit Test button 

### Output 
It will generate XML test report and store that report into s3 bucket for reference.


