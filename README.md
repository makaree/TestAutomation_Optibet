## Introduction
This test project contains testing framework to optibet.lv/en login page and running the test cases from IsoftBet Category. The Test URL is https://www.optibet.lv/en.

## Testing Tools
**Eclipse** is used for the development of this test project. It is an integrated development environment (IDE) used in computer programming. The coding is written in Object Oriented Programming Java.

**Maven**: This project is created as a Maven project. Maven Project is selected because of following features:
- It resolves Maven dependencies from the Eclipse workspace without installing to local Maven repository (requires dependency project be in same workspace).
- It automatic downloads the required dependencies and sources from the remote Maven repositories based on Maven's pom.xml. The dependencies used in this project are:

**Selenium Webdriver**: This tool is used for automating web-based application testing to verify that it performs expectedly. Selenium WebDriver allows you to choose a programming language of your choice to create test scripts. Java programming is used for automating here.

**TestNG**: TestNG is a testing framework for the Java programming language inspired by JUnit and NUnit. The design goal of TestNG is to cover a wider range of test categories: unit, functional, end-to-end, integration, etc., with more powerful and easy-to-use functionalities.

**Apache Maven plugin**: It is a plugin execution framework for a specific project to build and execute. Furthermore it helps in generating the results in HTML format.

**WebDrivers**: Different Web Drivers are included in the testing project to test the compatibility of the automation. The used drivers are: Opera, Selenium, and FireFox browers.

**Docker**: Docker is a tool designed to make it easier to create, deploy, and run applications by using containers. Containers allow a developer to package up an application with all of the parts it needs, such as libraries and other dependencies, and deploy it as one package.

## Running the test cases
Here are the list of steps required for the test execution. Below are the pre-requisites:

- In case java is not installed, Install Java (https://www.oracle.com/java/technologies/javase-downloads.html) if not installed. Then in command prompt run 
***java -version*** 
command to make sure java is installed.

- In case docker is not installed, Install Docker (https://www.docker.com/products/docker-desktop) if not installed. Then in command prompt run 
***docker -v*** 
command to make sure docker is installed.


**Method 1 with MAVEN (CMD)**:
1. In case maven is not installed, download maven (https://maven.apache.org/download.cgi File: apache-maven-3.6.3-bin.zip). Then in command prompt run 
***mvn -version*** 
command to make sure maven is installed.

2. Download the repository from the GIT.

3. Inside the downloaded folder TestAutomation_Optibet, open command prompt in this location  and run command in command prompt/bash
***mvn clean test*** 
It might take some time few minutes to install all the jar file and dependencies after this project is being build and test is executed. 

4. After the build is run go inside TestAutomation_Optibet/target/surefire-reports and open the file **emailable-report.html**. It contains the details about the test results.

**Method 2 with Eclipse:**
1. Download the repository from the GIT.

2. Open the folder TestAutomation_Optibet in Eclipse. Build the project. It might take some time few minutes to install all the jar file and dependencies when this project is being build.

3. Right click on AllSuitesTest.xml >> Run as >> TestNG Suite. (If this option is not present Goto Help >> Install new Software >> Click ADD>> Name: TestNG, Location: Add stable release link from (https://github.com/cbeust/testng-eclipse/)>>Select All and finish installation of the TestNG plugin). After the build is run go inside TestAutomation_Optibet/test-output and open the file **emailable-report.html**. It contains the details about the test results.

***Since there are huge number of tests to execute in multiple browsers so it take almost half and hour or more to execute.***

***ValidUsernameAndInvalidPassword test case is disabled to run because it blocks the account after multiple runs.***


## Features
Some of the features that are included in the testing framework are as follows:
1. Docker-compose file automatically configures the browsers firefox, opera and chrome and runs the test there.
2. Tests run in parallel (where user does not needs to be logged-in) and in series (where user needs to be logged-in)
3. Some of the tests are data driven meaning it runs the same test with multiple data through dataprovider.
4. Maven plugins scripts are written inside Project which could build, run the test cases and generate an HTML report file after running the test cases.

## Contact
If you got any questions feel free to contact me: manz.karki@gmail.com