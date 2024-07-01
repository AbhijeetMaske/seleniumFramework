# **Selenium Framework**

Welcome to the ultimate Selenium Framework, designed to simplify and enhance your web automation and testing projects. This framework provides a comprehensive suite of utilities for interacting with web elements, handling browser navigation, performing complex actions, and verifying element properties. By leveraging this framework, you can write cleaner, more maintainable, and reusable code, streamlining your Selenium automation process.

---

<img  align= center width=50px height=50px src="https://media4.giphy.com/media/3hoLIVAJYkz6T0Ichp/giphy.gif?cid=6c09b952m4j3poopinf91rquev6qy4e8avu0bflq1e0vh4gp&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s"> <a id="table-of-contents">Table of Contents</a></br>


 - [Introduction](#introduction)
 - [Getting Started](#getting-started)
 - [Project Structure](#project-structure)
 - [Dependencies](#dependencies)
 - [Configuration](#configuration)
 - [Usage](#usage)
	- [Element Actions](#element-actions)
	- [Element Interactions](#element-interactions)
	- [Element Utilities](#element-utilities)
	- [Navigation and Frames](#navigation-and-frames)
	- [Special Actions](#special-actions)
	- [Verification Actions](#verification-actions)
	- [Combined Interaction and Verification](#combined-interaction-and-verification)
 - [Contributing](#contributing)
 - [License](#license)
 
---
## Introduction
The Selenium Framework is crafted to address the common challenges faced during web automation. Whether you are a beginner or an experienced tester, this framework is built to make your life easier by providing easy-to-use methods for element interaction, navigation, and verification. Our goal is to make this the best Selenium framework you'll ever use.

# Getting Started

### Prerequisites
To use this Selenium Framework, you need to have the following installed on your system:

- Java Development Kit (JDK) 8 or higher: Ensure that your JDK is properly installed and configured.
- Maven: Maven is used for managing project dependencies and building the project.
- IDE: An Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or VS Code will make development easier.
	
## Installation

1. Clone the repository:
	```sh
	git clone https://github.com/yourusername/selenium-framework.git
	```
2.Navigate to the project directory:
	```sh
	cd selenium-framework
	```
 
3.Install dependencies using Maven:
	```sh
	mvn clean install
	```	
## Project Structure
The project follows a modular structure to keep the code organized and maintainable:

```html
selenium-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── seleniumFramework/
│   │   │           └── common/
│   │   │               ├── BaseClass
│   │   │               └── Config
│   │   │           └── pageObject/
│   │   │               └── pegeObject
│   │   │           └── utilities/
│   │   │               ├── AlertUtils
│   │   │               ├── ApiUtils
│   │   │               ├── BrowserUtils
│   │   │               ├── CustomAssertions
│   │   │               ├── DatabaseUtils
│   │   │               ├── DataProviderUtils
│   │   │               ├── DateTimeUtils
│   │   │               ├── EmailUtils
│   │   │               ├── ExcelUtils
│   │   │               ├── ExtentReportListener
│   │   │               ├── FileUtils
│   │   │               ├── LoggerUtils
│   │   │               ├── ReadConfig
│   │   │               ├── RetryAnalyzer
│   │   │               ├── ScreenshotUtils
│   │   │               └── UtilMethods
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── seleniumframework/
│   │   │           └── testcases/
│   │   │               └── tests/
│   └── resources/
│   └── suites/
│   │   ├── test.xml/
├── pom.xml
└── README.md 
```

 - src/main/java: Contains the main Java code, including utilities and core functionalities.
 - src/test/java: Contains test cases and test-related code.
 - resources: Contains resource files such as configuration files and test data.
	
## Dependencies
The framework utilizes the following dependencies, managed through pom.xml:

- Selenium Java: For Selenium WebDriver functionalities.
- WebDriverManager: For managing WebDriver binaries.
- TestNG: For structuring and running tests.
- Log4j: For logging framework activities.
- Maven: For project management and build automation.


## Configuration
### WebDriver Configuration
The BaseClass initializes the WebDriver instance. Customize your WebDriver settings in the BaseClass as per your project requirements. You can configure different browsers, set timeouts, and manage other WebDriver properties.

### Logging Configuration
Logging is configured using Log4j. Customize the log4j.properties file to set the logging levels and appenders. Proper logging will help you debug and maintain your tests effectively.

## Usage
The framework offers a rich set of utilities categorized based on their functionalities:

### Element Actions
Perform basic actions on web elements:

- click: Clicks on the web element.
- clickUsingJS: Clicks on the web element using JavaScript.
- sendKeys: Sends keys to the web element.
- sendKeysUsingJS: Sends keys to the web element using JavaScript.
- clear: Clears the text in the web element.
- doubleClick: Double clicks on the web element.
- rightClick: Right clicks on the web element.

### Element Interactions
Interact with element attributes and text:

- getAttribute: Retrieves the attribute value of the web element.
- describeElement: Describes the element for debugging purposes.
- verifyText: Verifies the text of the web element.
- verifyAttributeValue: Verifies the attribute value of the web element.
- verifyAttributeContains: Verifies if the attribute contains a specific value.
- getElementByText: Retrieves the element by its text.

### Element Utilities
Helper methods for element interactions:

- highlightElement: Highlights the web element for visual verification.
- scrollIntoView: Scrolls the web element into view.
- mouseHover: Hovers the mouse over the web element.
- selectOptionAndClick: Selects an option and clicks.
- isDisplayedAndClick: Checks if the element is displayed and then clicks.
- isEnabledAndClick: Checks if the element is enabled and then clicks.

### Navigation and Frames
Handle browser windows and frames:

- switchWindow: Switches to a different browser window.
- closeWindow: Closes the current browser window.
- switchToFrame: Switches to a specified frame.
- switchToDefaultContent: Switches back to the default content from a frame.

### Methods for browser navigation:

- navigateBack: Navigates back in the browser history.
- refresh: Refreshes the current page.

### Special Actions
Special interaction methods:

- keyDown: Performs key down action on a specified element.

### Web Table Interactions
Method for interacting with web tables:

- WebTables: Handles web table interactions.

### Verification Actions
Methods to verify elements and page content:

- verifyPageContainsText: Verifies if the page contains specified text.

### Combined Interaction and Verification
Methods that combine interaction and verification:

- searchClickByJS: Searches for an element by text and clicks it using JavaScript.
- findElement: Finds an element on the page.
- checkBoxSelectAndClick: Selects a checkbox if it is not already selected and then clicks it.
- checkBoxDisplayAndClick: Checks if a checkbox is displayed, then clicks it.
- checkBoxEnabledAndClick: Checks if a checkbox is enabled, then clicks it.

## Contributing
We welcome contributions to improve this framework! To contribute:

1. Fork the repository.
2. Create a new feature branch.
3. Make your changes and commit them.
4. Push to your branch.
5. Create a pull request.
Ensure your code follows the existing style and includes relevant tests. We appreciate your efforts to improve this framework!

## License
This project is licensed under the MIT License. See the LICENSE file for details.
	
	
