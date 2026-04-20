# trainingProject

Selenium Java automation framework built using:

- Maven
- TestNG
- Page Object Model (POM)
- Jenkins CI pipeline
- Cross-browser testing (Chrome, Edge)
- Selenium Grid (local/remote execution)

## Run tests locally

mvn clean test -Dsurefire.suiteXmlFiles=testng/smoke.xml

## Run tests in Jenkins

- Uses Jenkinsfile in repo
- Parameterised suite selection (smoke/regression)

## Test Suites

- testng/smoke.xml
- testng/regression.xml
- testng/edge-smoke.xml