# **Selenium Framework Utils**

The Selenium Framework Utils is a collection of utility classes designed to streamline and enhance the automation testing process with Selenium WebDriver. These utilities cover a wide range of functionalities, from handling web elements to interacting with files and managing test configurations. Below is a brief overview of each utility class included in the framework:

## 1. CustomAssertions.java
Provides custom assertion methods with detailed error messages to improve the clarity of test failures.
Example usage:
````java
CustomAssertions.assertEquals(actualValue, expectedValue, "Verify value equality");
```

## 2. DatabaseUtils.java
Enables connecting to a database, executing queries, and closing the connection.
Example usage:
```java
DatabaseUtils.connectToDatabase(url, user, password);
ResultSet resultSet = DatabaseUtils.executeQuery(query);
DatabaseUtils.closeConnection();
```
## 3. DataProviderUtils.java
Reads data from an Excel file and provides it to TestNG tests using data providers.
Example usage:
```java
@DataProvider(name = "excelDataProvider")
public Object excelDataProvider() throws IOException {
    return DataProviderUtils.getExcelData(filePath, sheetName);
}
```

## 4. DateTimeUtils.java
Offers methods for handling date and time operations, such as getting the current date/time, formatting, parsing, and calculating differences.
Example usage:
```java
@String currentDate = DateTimeUtils.getCurrentDate();
}
```
## 5. EmailUtils.java

Simplifies sending plain text, HTML, or email with attachments using SMTP.
Example usage:
```java
EmailUtils.sendPlainTextEmail(to, subject, body);
```

## 6. ExcelUtils.java
Provides functionality for reading and writing data to Excel files.
Example usage:
```java
ExcelUtils excelUtility = new ExcelUtils(filePath);
List<List<String>> data = excelUtility.readExcelData("Sheet1");
```

## 7. FileUtils.java
Offers methods for reading, writing, and deleting files.
Example usage:
```java
FileUtils.readFile(filePath);
```
## 8. LoggerUtils.java
Utility class for logging application behavior and troubleshooting using Log4j.
Example usage:
```java
LoggerUtils.info("Info message");
```

## 9.ReadConfig.java
Reads configurations from a properties file, such as base URL and browser type.
Example usage:
```java
ReadConfig readConfig = new ReadConfig();
String baseUrl = readConfig.getBaseUrl();
```
## 10.RetryAnalyzer.java
Implements TestNG's IRetryAnalyzer interface for retrying failed tests.
Example usage:
```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void testMethod() {
    // Test logic
}
```
## 11.ScreenshotUtils.java
Captures screenshots of web pages and saves them as image files.
Example usage:
```java
ScreenshotUtils.captureScreenShot(driver, "homepage");
}
```

## 12.UtilMethods.java
Utility methods for capturing screenshots using WebDriver's TakesScreenshot interface.
Example usage:
```java
UtilMethods.captureScreenShot(driver, "loginPage");
}
```
## 13.WebElementInteractionUtils.java
Utility class for interacting with web elements in Selenium WebDriver, such as clicking, typing text, and scrolling.
Example usage:
```java
WebElementInteractionUtils.click(element);

}
```

