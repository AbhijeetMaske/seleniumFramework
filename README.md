# **seleniumFramework**

This Selenium Framework is designed to facilitate web automation and testing. It provides a comprehensive set of utilities to interact with web elements, handle browser navigation, perform element actions, and verify element properties. This framework is built to improve code readability, reusability, and maintainability in your Selenium automation projects.

---

<img  align= center width=50px height=50px src="https://media4.giphy.com/media/3hoLIVAJYkz6T0Ichp/giphy.gif?cid=6c09b952m4j3poopinf91rquev6qy4e8avu0bflq1e0vh4gp&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s"> <a id="table-of-contents">Table of Contents</a>
</br>
 - [Getting Started](#Getting-Started)
 - [Project Structure](#Project-Structure)
 - [Dependencies](#Dependencies)
 - [Configuration](#Configuration)
 - [Usage](#Usage)
	- [Element Actions](#Element-Actions)
	- [Element Interactions](#Element-Interactions)
	- [Element Utilities](#Element-Utilities)
	- [Navigation and Frames](#Navigation-and-Frames)
	- [Special Actions](#Special-Actions)
	- [Verification Actions](#Verification-Actions)
	- [Combined Interaction and Verification](Combined-Interaction-and-Verification)
 - [Contributing](#Contributing)
 - [License](Licenses)
 	
---

#1-Getting Started

##Prerequisites
To use this Selenium Framework, you need to have the following installed on your system:

- Java Development Kit (JDK) 8 or higher
- Maven
- An IDE (like IntelliJ IDEA, Eclipse, or VS Code)
	
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
        </pre>
    </div>
</div>
```
	
## Dependencies
The framework uses the following dependencies:

- Selenium Java
- WebDriverManager
- TestNG (for testing)
- Log4j (for logging)
- Maven	

These dependencies are managed in the pom.xml file.

## Configuration
### WebDriver Configuration
The BaseClass initializes the WebDriver instance. Configure your WebDriver settings in the BaseClass to suit your needs.

### Logging Configuration
Logging is configured using Log4j. Customize the log4j.properties file to set the logging levels and appenders.

## Usage
### Element Actions
These methods perform basic actions on web elements:

- click
- clickUsingJS
- sendKeys
- sendKeysUsingJS
- clear
- doubleClick
- rightClick

### Element Interactions
Methods to interact with element attributes and text:

- getAttribute
- describeElement
- verifyText
- verifyAttributeValue
- verifyAttributeContains
- getElementByText

### Element Utilities
Helper methods for element interactions:

- highlightElement
- scrollIntoView
- mouseHover
- selectOptionAndClick
- isDisplayedAndClick
- isEnabledAndClick

### Navigation and Frames
Methods for handling browser windows and frames:

- switchWindow
- closeWindow
- switchToFrame
- switchToDefaultContent

### Methods for browser navigation:

- navigateBack
- refresh

### Special Actions
Special interaction methods:

- keyDown

### Web Table Interactions
Method for interacting with web tables:

- WebTables

### Verification Actions
Methods to verify elements and page content:

- verifyPageContainsText

### Combined Interaction and Verification
Methods that combine interaction and verification:

- searchClickByJS
- findElement
- checkBoxSelectAndClick
- checkBoxDisplayAndClick
- isCheckboxEnabledAndClick

## Contributing
We welcome contributions to improve this framework! To contribute:

1. Fork the repository.
2. Create a new feature branch.
3. Make your changes and commit them.
4. Push to your branch.
5. Create a pull request.
6. Please ensure your code adheres to the existing style and includes relevant tests.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
	
	
