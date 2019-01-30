# WCMS QA Testing Framework
Automated test framework using Se WebDriver, TestNG and Maven written in Java.

## Prerequisites:
- JDK 1.8.0_144 or later
- [Apache maven](http://maven.apache.org/download.cgi) tool 
- Eclipse Neon or later (optional)

## Command line execution:

To run the default test suite (all tests), execute the command 

    mvn test

### Specifying the environment

If no environment is specified, the default is QA.

To use a different environment, on the maven commmand line, specify
`-Denvironment=<environment_name>`

    Valid values are:
      blue
      red
      pink
      qa
      dt
      stage
      

### Specifying a test suite.

To execute a specific test suite, execute the command

    mvn test -Dsurefire.suiteXmlFiles=<testfile>

Where `<testfile>` is the test suite file and path.  Multiple test suites may be specified by separating them
with commas. e.g.

    mvn test -Dsurefire.suiteXmlFiles=resources\testng-CTS.xml,resources\testng-R4R.xml

### Specifying the browser

Tests default to running in Chrome Headless. To use a different browser, include the arguement -Dbrowser=<browser>

    mvn test -Dbrowser=Chrome

Valid browser names are:
* Chrome
* ChromeHeadless
* Firefox
* GeckoHeadless
* iPhone6
* iPad

**NOTE:** Names are CaSe seNstive.  (e.g. "Chrome" is not the same as "chrome.")


## Setup For Eclipse
1. Open Eclipse and workspace.
2. Install TestNG
   1. Launch the Eclipse IDE and from Help menu, click **Install New Software**.
   2. Click Add button.
   3. Enter name as **TestNG** and type http://beust.com/eclipse/ as location. Click OK.
   4. The TestNG option should display in the available software list. Click TestNG and click Next button.
   5. Accept the terms of the license agreement, then click Finish.
   6. Click Next again on the succeeding dialog box until it prompts you to Restart the Eclipse and click Yes.
3. Wait for installation to complete, then restart Eclipse.

## To run tests:
1. In Eclipse navigator, expand resources -> drivers.
2. Right-click **testng.xml**, select **Run as -> TestNG Suite**.

## To change tests or browsers: 
1. In Eclipse navigator, expand resources -> drivers.
2. Open **testng.xml** for editing. 
3. Add or uncomment any elements that should be tested.

## To change testing tier:
1. In Eclipse navigator, expand src/main/java/gov.nci.Utilities.
2. Open **ConfigReader.java** for editing.
3. Change the "/configuration/ConfigXXXX.property" string to match the desired tier.
4. Save and run tests.

## Installing Drivers/Plugins
*Note - the required drivers are currently pulled in with the Git repo and do not need to be installed. If any of these need to be reinstalled manually, download from the given link, extract, then copy the files to the referenced path in Eclipse.*
### Maven Install:
1. Go to URL: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-eclipse-plugin/2.10/
2. Click on maven-eclipse-plugin-2.10-source-release.zip
3. Extract files in the appropriate folder
### Selenium Webdriver:
1. http://selenium-release.storage.googleapis.com/index.html?path=3.5/
2. Click on selenium-java-3.5.0.zip
3. Extract files in the appropriate folder
### ChromeDriver:
1. https://chromedriver.storage.googleapis.com/index.html?path=2.34/
2. Click on chromedriver_win32.zip
3. Extract files in the appropriate folder

### Gecko Driver:
1. https://github.com/mozilla/geckodriver/releases/tag/v0.23.0
2. Download file for desired OS:
   1. **Windows:** geckodriver-v0.23.0-win64.zip
   2. **Mac OS:** geckodriver-v0.23.0-macos.tar.gz
   3. **Linux:** geckodriver-v0.23.0-linux64.tar.gz
3. Extract file and copy to the appropriate folder

### Apache Maven:
1. https://maven.apache.org/download.cgi
2. Click on apache-maven-3.5.2-bin.zip
3. Extract files in the appropriate folder
### BrowserMob Proxy:
1. Download source from https://github.com/lightbody/browsermob-proxy
2. Build and place jar in the appropriate folder
