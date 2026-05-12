This is a hybrid automation testing framework built using:

Selenium WebDriver
TestNG
Maven
Java
Page Object / Test design structure
GitHub Actions (CI/CD integration)

It supports cross-browser execution, headless mode, reporting, and CI execution on GitHub.

 Features
 Selenium WebDriver automation
 TestNG test framework
 Maven build management
 Cross-browser support (Chrome & Firefox)
 Headless execution for CI/CD (GitHub Actions)
 WebDriverManager for automatic driver setup
 Explicit & implicit waits
 Alert handling, file upload, dropdown, hover, dynamic elements
 Screenshot support (listener integration ready)
 Extent Reports integration (HTML reporting)
 GitHub Actions CI pipeline
 Email report sending via Gmail SMTP
Tech Stack
Java 21
Selenium 4
TestNG
Maven
WebDriverManager
GitHub Actions
 Project Structure
MockHack/
│
├── src/test/java
│   ├── com.krct
│   │   ├── BaseTest.java
│   │   ├── BtestAlert.java
│   │   ├── FileUploadTest.java
│   │   ├── ChoverTest.java
│   │   ├── DynamicPage.java
│   │   └── LoginPage.java
│
├── src/test/resources
│   ├── testng.xml
│   ├── image.jpg
│
├── utils
│   ├── ConfigReader.java
│   ├── ScreenshotUtil.java
│   ├── ExtentReportManager.java
│
├── listeners
│   ├── ListenerClass.java
│
├── reports/
├── pom.xml
└── README.md
 How to Run Tests Locally
Run all tests:
mvn test
Run in headless mode:
mvn test -Dheadless=true
Run specific browser:
mvn test -Dbrowser=chrome
 GitHub Actions (CI/CD)

This project supports automatic execution on GitHub.

Workflow:
Checkout code
Setup Java 21
Install Chrome
Run Maven tests in headless mode
Generate test report
Send email notification
 Email Report Configuration

Uses Gmail SMTP with GitHub Secrets:

Key	Description
ACTION_USERNAME	Gmail ID
ACTION_PASSWORD	App Password
 Test Scenarios Covered
JS Alerts Handling
File Upload Automation
Hover Actions
Checkbox & Dropdown Validation
Dynamic Controls
Page Navigation Checks
 Reports

After execution, reports are generated at:

target/surefire-reports/emailable-report.html
 Base Framework Design
BaseTest handles:
WebDriver initialization
Browser selection
Headless configuration
Wait setup
Driver teardown
Test classes contain only test logic
 Key Learning

This framework demonstrates:

Real-world automation architecture
CI/CD pipeline integration
Scalable Selenium design
TestNG execution flow
Author
Mohana Rengan

 Note

This framework is designed for learning + interview + real project simulation purposes.
