# trainingProject

Selenium Java automation framework built to demonstrate modern test automation practices, including cross-browser execution, CI integration, scalable design, and Behaviour-Driven Development (BDD) using Cucumber.

---

## Tech Stack

* Java (JDK 21)
* Selenium WebDriver
* TestNG
* Maven
* Jenkins (CI/CD)
* Selenium Grid
* Cucumber (BDD)
* Extent Reports

---

## Project Structure

trainingproject/
│
├── src/
│   ├── main/java/pages        # Page Object classes
│   ├── main/java/utils        # Utilities (ConfigReader, ScreenshotUtil, etc.)
│   └── test/java/tests        # Test classes
│   └── test/java/base         # BaseTest setup
│   └── test/java/stepdefs     # Cucumber step definitions
│   └── test/java/runners      # Cucumber TestNG runners
│
├── src/test/resources
│   ├── config.properties      # Environment configuration
│   └── features               # Cucumber feature files
│
├── testng/                    # TestNG suite files
│   ├── smoke.xml
│   ├── regression.xml
│   └── edge-smoke.xml
│
├── reports/                   # Extent Reports output
├── screenshots/               # Failure screenshots
│
├── Jenkinsfile                # CI pipeline definition
├── pom.xml                    # Maven configuration
└── README.md

---

## Running Tests

### Run Smoke Suite

```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng/smoke.xml
```

### Run Regression Suite

```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng/regression.xml
```

### Run Edge Suite

```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng/edge-smoke.xml
```

> Note: When using PowerShell, wrap parameters in quotes:

```powershell
mvn clean test "-Dsurefire.suiteXmlFiles=testng/smoke.xml"
```

---

## Cross-Browser Execution

Browser is controlled via TestNG:

```xml
<parameter name="browser" value="chrome"/>
```

Supported browsers:

* Chrome
* Edge

---

## Configuration

All runtime configuration is controlled via:

src/test/resources/config.properties

Example:

```properties
browser=chrome
headless=false
runMode=local
gridUrl=http://localhost:4444
loginUrl=https://the-internet.herokuapp.com/login
timeout=10
```

### Key Features

* **headless=true/false** → control browser visibility
* **runMode=local/grid** → switch execution mode
* **gridUrl** → Selenium Grid endpoint

---

## Selenium Grid

Framework supports execution on Selenium Grid.

To enable:

```properties
runMode=grid
```

To run locally:

```properties
runMode=local
```

---

## Cucumber (BDD) Integration

The framework supports Behaviour-Driven Development (BDD) using Cucumber.

### Key Features

* Feature files written in Gherkin syntax
* Step definitions implemented in Java
* Integration with TestNG runner
* Reusable step definitions aligned with Page Object Model
* Supports execution via Maven and Jenkins

### Example Feature

```gherkin
Feature: Login functionality

  Scenario: Successful login
    Given user is on login page
    When user enters valid credentials
    Then user should be logged in successfully
```

### Execution

Cucumber scenarios are executed via TestNG runner classes and can be included in CI pipelines.

---

## Test Coverage

Current test scenarios include:

* Login functionality (valid + invalid credentials)
* Drag and drop interaction (jQuery UI)
* eBay search and navigation test
* Cucumber BDD scenarios for login functionality

---

## Framework Features

* Page Object Model (clean separation of UI logic)
* Thread-safe WebDriver using ThreadLocal
* Config-driven execution
* Cross-browser support
* Headless execution support
* Selenium Grid compatibility
* CI/CD integration with Jenkins
* Extent reporting with screenshots
* Cucumber BDD integration

---

## Jenkins Integration

* Pipeline defined in `Jenkinsfile`
* Parameterised suite selection (smoke / regression)
* Executes via Maven:

```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng/${SUITE}.xml
```

* Publishes:

  * TestNG results
  * Extent Reports
  * Screenshots

---

## Improvements Implemented

* Removed hardcoded WebDriver paths
* Externalised configuration
* Upgraded to Java 21
* Added SLF4J logging support
* Refactored screenshot utility using modern Java NIO
* Reduced build warnings
* Integrated Cucumber for BDD-style testing

---

## Future Enhancements

* Retry mechanism for flaky tests
* Enhanced reporting (custom dashboards)
* Docker-based Selenium Grid
* Parallel execution optimisation

--- 
https://github.com/cokeef01-svg/trainingproject.git
## Author

Conor O’Keeffe
Senior Test Engineer
