package gov.cancer.tests;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import gov.cancer.framework.Configuration;
import gov.cancer.framework.ScreenShot;
import gov.cancer.framework.BrowserManager;

public abstract class TestObjectBase {

  // private static Logger log=
  // LogManager.getLogger(BaseClass.class.getName());
  protected static ExtentReports report;
  protected static ExtentTest logger;
  protected WebDriver driver;
  protected Configuration config;

  @BeforeTest(alwaysRun = true)
  @Parameters({ "environment" })
  public void beforeTest(String environment) {

    config = new Configuration(environment);

    String dateTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-SS").format(new Date());
    String extentReportPath = config.getExtentReportPath();
    String fileName = environment.toUpperCase() + "-" + dateTime + ".html";

    report = new ExtentReports(extentReportPath + fileName);
    report.addSystemInfo("Environment", environment);
  }

  // Setting up path and filename for the log file
  // Printing location of log file and environment variables
  // -------------------------------------------------------
  @BeforeClass(alwaysRun = true)
  @Parameters({ "environment", "browser" })
  public void beforeClass(String environment, String browser) {

    config = new Configuration(environment);
    driver = BrowserManager.startBrowser(browser, config, "about:blank");

    logger = report.startTest(this.getClass().getSimpleName());
    System.out.println("\n  Running test: " + this.getClass().getSimpleName());
  }

  // Print out the name of the method before each is run
  // ------------------------------------------------------
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod(Method method) {

    System.out.println(String.format("\tExecuting %s()", method.getName()));
  }

  // If a method fails, log the result and take a screenshot of the page
  // -------------------------------------------------------------------
  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult result) throws InterruptedException {
    if (result.getStatus() == ITestResult.FAILURE) {
      String screenshotPath = ScreenShot.captureScreenshot(driver, result.getName());
      String image = logger.addScreenCapture(screenshotPath);

      logger.log(LogStatus.FAIL, image + "Fail => " + result.getName());
    } else if (result.getStatus() == ITestResult.SKIP) {
      logger.log(LogStatus.SKIP, "Skipped => " + result.getName());
    } else {
      logger.log(LogStatus.PASS, "Passed => " + result.getName());
    }
    report.flush();
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    driver.quit();
    report.endTest(logger);
  }

  // Returns the URL for the host currently used for testing
  // -------------------------------------------------------
  public String AddHostname(String path) {
    String host;

    host = config.GetHostName();
    return "https://" + host + path;
  }
}
