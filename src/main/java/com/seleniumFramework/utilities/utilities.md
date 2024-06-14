# **Selenium Framework Utils**

The Selenium Framework Utilities is a comprehensive set of utility classes meticulously crafted to streamline and enhance the Selenium WebDriver-based automation testing process. Each utility serves a specific purpose, simplifying common tasks encountered during test automation development. Let's delve into each utility class to understand its role and how it contributes to the overall efficiency and effectiveness of Selenium test automation.

## 1. CustomAssertions.java
This utility class provides custom assertion methods, offering enhanced readability and informative error messages when assertions fail. It empowers testers to write expressive and precise assertions, facilitating quicker identification and resolution of test failures.
Example usage:
```java
CustomAssertions.assertEquals(actualValue, expectedValue, "Verify value equality");
```

## 2. DatabaseUtils.java
The DatabaseUtils class facilitates interaction with databases by enabling connection establishment, query execution, and connection closure. It abstracts away the complexities of JDBC code, promoting cleaner and more maintainable database-related operations in test automation scripts.
Example usage:
```java
DatabaseUtils.connectToDatabase(url, user, password);
ResultSet resultSet = DatabaseUtils.executeQuery(query);
DatabaseUtils.closeConnection();
```

## 3. DataProviderUtils.java
This utility class simplifies data provisioning for TestNG tests by reading data from Excel files and supplying it via TestNG's data provider mechanism. It enhances test data management and encourages separation of test data from test logic, fostering modular and scalable test suites.
Example usage:
```java
@DataProvider(name = "excelDataProvider")
public Object excelDataProvider() throws IOException {
    return DataProviderUtils.getExcelData(filePath, sheetName);
}
```

## 4. DateTimeUtils.java
DateTimeUtils offers a suite of methods for handling date and time operations, such as obtaining the current date/time, formatting, parsing, and calculating date/time differences. It promotes consistent and reliable date/time manipulation across test scenarios.
Example usage:
```java
@String currentDate = DateTimeUtils.getCurrentDate();
}
```

## 5. EmailUtils.java

This utility simplifies email sending tasks, supporting plain text, HTML, and email with attachments via SMTP. It enhances test reporting capabilities by enabling automated email notifications for test execution results.
Example usage:
```java
EmailUtils.sendPlainTextEmail(to, subject, body);
```

## 6. ExcelUtils.java
ExcelUtils provides functionalities for reading and writing data to Excel files, fostering seamless integration of test data stored in Excel spreadsheets into automated test scripts.
Example usage:
```java
ExcelUtils excelUtility = new ExcelUtils(filePath);
List<List<String>> data = excelUtility.readExcelData("Sheet1");
```

## 7. FileUtils.java
FileUtils simplifies file handling operations such as reading, writing, and deleting files. It enhances test script flexibility by enabling dynamic file operations during test execution.
Example usage:
```java
FileUtils.readFile(filePath);
```
## 8. LoggerUtils.java
LoggerUtils is a utility class for logging application behavior and troubleshooting using Log4j. It provides a structured approach to logging test execution events, aiding in debugging and analysis.Utility class for logging application behavior and troubleshooting using Log4j.
Example usage:
```java
LoggerUtils.info("Info message");
```

## 9.ReadConfig.java
This utility reads configurations from a properties file, such as base URL and browser type. It facilitates centralized configuration management, promoting consistency and maintainability across test environments.
Example usage:
```java
ReadConfig readConfig = new ReadConfig();
String baseUrl = readConfig.getBaseUrl();
```
## 10.RetryAnalyzer.java
RetryAnalyzer implements TestNG's IRetryAnalyzer interface for retrying failed tests, enhancing test robustness and stability in flaky test environments.
Example usage:
```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void testMethod() {
    // Test logic
}
```

## 11.ScreenshotUtils.java
This utility class captures screenshots of web pages, facilitating visual inspection of test failures and aiding in bug diagnosis.
Example usage:
```java
ScreenshotUtils.captureScreenShot(driver, "homepage");
}
```

## 12.UtilMethods.java
UtilMethods provides utility methods for capturing screenshots using WebDriver's TakesScreenshot interface, enhancing test reporting capabilities.
Example usage:
```java
UtilMethods.captureScreenShot(driver, "loginPage");
}
```

## 13.WebElementInteractionUtils.java
WebElementInteractionUtils is a utility class for interacting with web elements in Selenium WebDriver. It provides methods for common actions such as clicking, typing text, and scrolling, simplifying element interaction in test scripts.
Example usage:
```java
WebElementInteractionUtils.click(element);
}
```

