# **Selenium Framework Utilities Documentation**

## Overview
The Selenium Framework Utilities package provides a comprehensive suite of utility classes designed to streamline and enhance the Selenium WebDriver-based automation testing process. Each utility class serves a specific purpose, simplifying common tasks encountered during test automation development. This documentation provides a detailed overview of each utility class, its role, methods, and example usage to aid testers in leveraging these utilities effectively.

---

<img  align= center width=50px height=50px src="https://media4.giphy.com/media/3hoLIVAJYkz6T0Ichp/giphy.gif?cid=6c09b952m4j3poopinf91rquev6qy4e8avu0bflq1e0vh4gp&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s"> <a id="table-of-contents">Table of Contents</a>
</br>
 - [AlertUtils](#AlertUtils)
 - [ApiUtils](#ApiUtils)
 - [BrowserUtils](#BrowserUtils)
 - [CustomAssertions](#CustomAssertions)
 - [DatabaseUtils](#DatabaseUtils)
 - [DataProviderUtils](#DataProviderUtils)
 - [DateTimeUtils](#DateTimeUtils)
 - [ElementInteractionUtils](#ElementInteractionUtils)
 - [EmailUtils](#EmailUtils)
 - [ExcelUtils](#ExcelUtils)
 - [ExtentReportListener](#ExtentReportListener)
 - [FileUtils](#FileUtils)
 - [LoggerUtils](#LoggerUtils)
 - [ReadConfig](#ReadConfig) 	
 - [RetryAnalyzer](#RetryAnalyzer) 	
 - [ScreenshotUtils](#ScreenshotUtils) 	
 - [UtilMethods](#UtilMethods) 		
---

## 1. AlertUtils
AlertUtils handles interactions with JavaScript alerts in Selenium WebDriver, offering methods to accept, dismiss, and retrieve alert text, and check for alert presence. It simplifies alert handling in test scripts.

Example Methods:
 - acceptAlert(): Accepts the alert if present.
 - dismissAlert(): Dismisses the alert if present.
 - getAlertText(): Retrieves the text from the alert.
 - isAlertPresent(): Checks if an alert is present.
 - acceptAlertIfPresent(): Accepts the alert if present.
 - dismissAlertIfPresent(): Dismisses the alert if present.
 - acceptPrompt(String text): Accepts the prompt and enters the specified text if present.

Example Usage:

```java
AlertUtils alertUtils = new AlertUtils(driver);
alertUtils.acceptAlert();
```

## 2.ApiUtils
ApiUtils is a utility class for making API requests using RestAssured. It simplifies the process of sending GET and POST requests and handling responses, making it easier to integrate API interactions within your test automation framework.

Example Methods
get(String url): Makes a GET request to the specified URL and returns the response.
post(String url, String body): Makes a POST request to the specified URL with the given body and returns the response.

Example Usage:

```java
Response response = ApiUtils.get("https://api.example.com/data");
String responseBody = response.getBody().asString();
```

## 3.BrowserUtils
BrowserUtils handles browser-related operations, including retrieving browser versions and configuring browser options for Chrome, Firefox, and Edge.

Example Methods
 - getChromeBrowserVersion(): Retrieves the version of the Chrome browser.
 - getEdgeBrowserVersion(): Retrieves the version of the Edge browser.
 - getFirefoxBrowserVersion(): Retrieves the version of the Firefox browser.
 - configureChromeOptions(): Configures and returns ChromeOptions.
 - configureFirefoxOptions(): Configures and returns FirefoxOptions.
 - configureEdgeOptions(): Configures and returns EdgeOptions.

Example Usage:

```java
System.out.println("Chrome Version: " + BrowserUtils.getChromeBrowserVersion());
```

## 4. CustomAssertions.java
CustomAssertions provides custom assertion methods, offering enhanced readability and informative error messages when assertions fail. It empowers testers to write expressive and precise assertions, facilitating quicker identification and resolution of test failures.

Example Methods:
assertEquals(Object actual, Object expected, String message): Asserts that two objects are equal, with a custom message.

Example usage:

```java
CustomAssertions.assertEquals(actualValue, expectedValue, "Verify value equality");
```

## 2. DatabaseUtils.java
DatabaseUtils facilitates interaction with databases by enabling connection establishment, query execution, and connection closure. It abstracts away the complexities of JDBC code, promoting cleaner and more maintainable database-related operations in test automation scripts.

Example Methods:
 - connectToDatabase(String url, String user, String password): Establishes a connection to the specified database.
 - executeQuery(String query): Executes the given SQL query and returns the ResultSet.
 - closeConnection(): Closes the current database connection.

Example usage:

```java
DatabaseUtils.connectToDatabase(url, user, password);
ResultSet resultSet = DatabaseUtils.executeQuery(query);
DatabaseUtils.closeConnection();
```

## 3. DataProviderUtils.java
DataProviderUtils simplifies data provisioning for TestNG tests by reading data from Excel files and supplying it via TestNG's data provider mechanism. It enhances test data management and encourages the separation of test data from test logic, fostering modular and scalable test suites.

Example Methods:
 - getExcelData(String filePath, String sheetName): Reads data from the specified Excel sheet and returns it as a 2D array.

Example usage:

```java
@DataProvider(name = "excelDataProvider")
public Object[][] excelDataProvider() throws IOException {
    return DataProviderUtils.getExcelData(filePath, sheetName);
}
```

## 4. DateTimeUtils.java
DateTimeUtils offers a suite of methods for handling date and time operations, such as obtaining the current date/time, formatting, parsing, and calculating date/time differences. It promotes consistent and reliable date/time manipulation across test scenarios.

Example Methods:
 - getCurrentDate(): Returns the current date as a formatted string.
 - formatDate(Date date, String format): Formats the given date according to the specified format.

Example usage:

```java
@String currentDate = DateTimeUtils.getCurrentDate();
}
```

## 5. EmailUtils.java
EmailUtils simplifies email sending tasks, supporting plain text, HTML, and email with attachments via SMTP. It enhances test reporting capabilities by enabling automated email notifications for test execution results.

Example Methods:
 - sendPlainTextEmail(String to, String subject, String body): Sends a plain text email to the specified recipient.
 - sendHtmlEmail(String to, String subject, String htmlBody): Sends an HTML email to the specified recipient.
 - sendEmailWithAttachment(String to, String subject, String body, String filePath): Sends an email with an attachment to the specified recipient.

Example usage:

```java
EmailUtils.sendPlainTextEmail(to, subject, body);
```

## 6. ExcelUtils.java
ExcelUtils provides functionalities for reading and writing data to Excel files, fostering seamless integration of test data stored in Excel spreadsheets into automated test scripts.

Example Methods:
 - readExcelData(String sheetName): Reads data from the specified Excel sheet and returns it as a List<List<String>>.
 - writeDataToExcel(String sheetName, List<List<String>> data): Writes the specified data to the given Excel sheet.

Example usage:

```java
ExcelUtils excelUtility = new ExcelUtils(filePath);
List<List<String>> data = excelUtility.readExcelData("Sheet1");
```

## 7. FileUtils.java
FileUtils simplifies file handling operations such as reading, writing, and deleting files. It enhances test script flexibility by enabling dynamic file operations during test execution.

Example Methods:
 - readFile(String filePath): Reads the content of the specified file and returns it as a string.
 - writeFile(String filePath, String content): Writes the given content to the specified file.
 - deleteFile(String filePath): Deletes the specified file.

Example usage:

```java
FileUtils.readFile(filePath);
```
## 8. LoggerUtils.java
LoggerUtils is a utility class for logging application behavior and troubleshooting using Log4j. It provides a structured approach to logging test execution events, aiding in debugging and analysis.

Example Methods:
 - info(String message): Logs an info-level message.
 - warn(String message): Logs a warning-level message.
 - error(String message, Throwable t): Logs an error-level message with an exception.

Example usage:

```java
LoggerUtils.info("Info message");
```

## 9.ReadConfig.java
ReadConfig reads configurations from a properties file, such as base URL and browser type. It facilitates centralized configuration management, promoting consistency and maintainability across test environments.

Example Methods:
 - getBaseUrl(): Returns the base URL from the configuration file.
 - getBrowserType(): Returns the browser type from the configuration file.

Example usage:

```java
ReadConfig readConfig = new ReadConfig();
String baseUrl = readConfig.getBaseUrl();
```
## 10.RetryAnalyzer.java
RetryAnalyzer implements TestNG's IRetryAnalyzer interface for retrying failed tests, enhancing test robustness and stability in flaky test environments.

Example Methods:
 - retry(ITestResult result): Determines whether the test method should be retried.

Example usage:

```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void testMethod() {
    // Test logic
}
```

## 11.ScreenshotUtils.java
ScreenshotUtils captures screenshots of web pages, facilitating visual inspection of test failures and aiding in bug diagnosis.

Example Methods:
- captureScreenShot(WebDriver driver, String fileName): Captures a screenshot of the current page and saves it with the specified file name.

Example usage:

```java
ScreenshotUtils.captureScreenShot(driver, "homepage");
}
```

## 12.UtilMethods.java
UtilMethods provides additional utility methods that complement the Selenium WebDriver API, enhancing overall test script capabilities.

Example Methods:
 - someUtilityMethod(): Example placeholder for utility methods.

Example usage:

```java
UtilMethods.someUtilityMethod();
}
```

## 13.ElementInteractionUtils.java
ElementInteractionUtils is a utility class for interacting with web elements in Selenium WebDriver. It provides methods for common actions such as clicking, typing text, and scrolling, simplifying element interaction in test scripts.

Example Methods:
 - click(WebElement element): Clicks on the specified web element.
 - typeText(WebElement element, String text): Types the given text into the specified web element.
 - scrollToElement(WebDriver driver, WebElement element): Scrolls the page to the specified web element.

Example usage:

```java
WebElementInteractionUtils.click(element);
}
```

---

The Selenium Framework Utilities package offers a robust set of tools that significantly enhance the capabilities and efficiency of Selenium WebDriver-based test automation. By leveraging these utilities, testers can streamline common tasks, improve test script readability, maintainability and ultimately deliver more reliable and comprehensive test automation solutions.

---

