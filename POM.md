# Project Object Model (POM) Documentation

This document provides a detailed overview of the pom.xml file for the Selenium Framework project. The pom.xml file is a crucial part of a Maven project, defining the project dependencies, plugins, and various build configurations. This guide aims to help you understand the purpose and use of each dependency and plugin included in the pom.xml.

## Project Coordinates

 - Group ID: `SeleniumFramework`
 - Artifact ID: `SeleniumFramework`
 - Version: `1.0.0-SNAPSHOT`
 
## Dependencies

### Selenium Java 
```xml
<dependency>
	<groupId>org.seleniumhq.selenium</groupId>
	<artifactId>selenium-java</artifactId>
	<version>4.21.0</version>
</dependency>
```
**Description:** Selenium Java bindings provide the necessary libraries to interact with web browsers for automated testing. Selenium supports multiple browsers and allows writing tests in various programming languages, including Java.

### TestNG
```xml
<dependency>
	<groupId>org.testng</groupId>
	<artifactId>testng</artifactId>
	<version>7.8.0</version>
	<scope>test</scope>
</dependency>
```
**Description:** TestNG is a testing framework inspired by JUnit and NUnit. It supports parallel test execution, data-driven testing, and configuration flexibility, making it ideal for Selenium test automation.

### ExtentReports

```xml
<dependency>
	<groupId>com.aventstack</groupId>
	<artifactId>extentreports</artifactId>
	<version>5.1.1</version>
</dependency>
```
**Description:** ExtentReports is a customizable HTML reporting library for Java. It provides detailed and visually appealing reports for automated tests, including screenshots, logs, and test steps.

### Apache POI

```xml
<dependency>
	<groupId>org.apache.poi</groupId>
	<artifactId>poi</artifactId>
	<version>5.2.5</version>
</dependency>
<dependency>
	<groupId>org.apache.poi</groupId>
	<artifactId>poi-ooxml</artifactId>
	<version>5.2.5</version>
</dependency>
```
**Description:** Apache POI provides libraries for reading and writing Microsoft Office documents. The poi artifact is for general Excel file manipulation, while poi-ooxml supports OOXML (XLSX) files.

### Log4j

```xml
<dependency>
	<groupId>org.apache.logging.log4j</groupId>
	<artifactId>log4j-core</artifactId>
	<version>2.23.1</version>
</dependency>
<dependency>
	<groupId>org.apache.logging.log4j</groupId>
	<artifactId>log4j-api</artifactId>
	<version>2.23.1</version>
</dependency>
```
**Description:** Log4j is a logging framework for Java. It provides logging at various levels (e.g., INFO, DEBUG, ERROR) and supports advanced configurations, making it easier to monitor and debug test executions.

### WebDriver Manager

```xml
<dependency>
	<groupId>io.github.bonigarcia</groupId>
	<artifactId>webdrivermanager</artifactId>
	<version>5.4.1</version>
</dependency>
```
**Description:** WebDriver Manager automates the management of WebDriver binaries for Selenium. It downloads and configures the appropriate WebDriver binary for your system, simplifying browser setup.

### RestAssured

```xml
<dependency>
	<groupId>io.rest-assured</groupId>
	<artifactId>rest-assured</artifactId>
	<version>4.4.0</version>
</dependency>
<dependency>
	<groupId>io.rest-assured</groupId>
	<artifactId>json-path</artifactId>
	<version>4.4.0</version>
</dependency>
<dependency>
	<groupId>io.rest-assured</groupId>
	<artifactId>xml-path</artifactId>
	<version>4.4.0</version>
</dependency>
```
**Description:** RestAssured is a Java library for testing RESTful web services. It simplifies making HTTP requests and validating responses. The json-path and xml-path dependencies extend RestAssured's capabilities for handling JSON and XML responses.

### Javax Mail

```xml
<dependency>
	<groupId>com.sun.mail</groupId>
	<artifactId>javax.mail</artifactId>
	<version>1.6.2</version>
</dependency>
```
**Description:** Javax Mail provides a set of APIs for sending and receiving emails. It's useful for sending email notifications about test results, such as failed test cases or test completion reports.

##Build Plugins

### Maven Surefire Plugin

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<version>3.0.0-M5</version>
	<configuration>
		<suiteXmlFiles>
			<suiteXmlFile>suites/Sample.xml</suiteXmlFile>
		</suiteXmlFiles>
		<!-- Parallel execution configuration -->
		<parallel>methods</parallel>
		<threadCount>4</threadCount>
	</configuration>
</plugin>
```
**Description:** The Maven Surefire Plugin is used for running unit tests in a Maven project. It provides options for parallel test execution and integration with TestNG. The configuration specifies the test suite to run and enables parallel execution with a defined thread count.


#Instructions for Use

### Setting Up the Project

#### 1. Ensure Java and Maven are Installed:
 - Make sure you have Java Development Kit (JDK) and Apache Maven installed on your machine.
 
#### 2. Clone the Repository:

 - Clone the project repository to your local machine using Git or download the source code as a ZIP file.

#### 3.Navigate to the Project Directory:

 - Open a terminal or command prompt and navigate to the root directory of the project.

#### 4. Build the Project:

 - Run the following command to build the project and download all dependencies:

```bash
mvn clean install
```

### Running Tests
#### 1. Run TestNG Tests:

 - Use the Maven Surefire Plugin to run the TestNG test suite specified in the pom.xml:

```bash
mvn test
```

#### 2. Parallel Execution:

 - The configuration in pom.xml enables parallel execution of test methods with a thread count of 4, speeding up test execution.

### Customizing Configuration

#### 1. Update Dependencies:

To update any dependency version, modify the corresponding `<version>` tag in the `pom.xml` file and run `mvn clean install` to apply the changes.

#### 2. Modify Test Suite:

To change the TestNG suite file, update the `<suiteXmlFile>` path in the Surefire Plugin configuration.

### Utilizing Dependencies
 
#### 1. Selenium Java:
 - Use Selenium WebDriver for browser automation in your test scripts.

#### 2. TestNG:
 - Organize and run your tests using TestNG's annotations, test suites, and data providers.

#### 3. ExtentReports:
 - Generate detailed and visually appealing test reports with ExtentReports.

#### 4. Apache POI:
 - Read from and write to Excel files for managing test data.

#### 5. Log4j:
 - Implement logging in your test scripts for better debugging and monitoring.

#### 6. WebDriver Manager:
 - Automate the setup of WebDriver binaries, ensuring compatibility with browser versions.

#### 7. RestAssured:
 - Perform API testing by sending HTTP requests and validating responses.

#### 8. Javax Mail:
 - Send email notifications about test execution results.

---

This pom.xml file is a powerful configuration that supports comprehensive test automation capabilities. By following the instructions and understanding the use of each dependency, you can efficiently set up, run, and maintain your Selenium-based test automation framework.

--- 
