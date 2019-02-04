package gov.cancer.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.commons.lang3.SystemUtils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;

public class BrowserManager {

  static WebDriver driver;

  /**
   * @param config
   * @param driver
   * @return String driverPath
   */
  private static String getDriverPath(Configuration config, String driver) {
    // Get the base path for the OS
    String driverPath = config.getDriverBasePath();

    // Get the driver name itself
    driverPath += "/" + config.getDriverName(driver);

    if (SystemUtils.IS_OS_WINDOWS) {
      driverPath += ".exe";
    }

    return driverPath;
  }

  /**
   * Create a new web driver for given browser and set that browser's options
   *
   * @param browserName name of the browser
   * @param url         URL to open
   * @return WebDriver driver
   */
  public static WebDriver startBrowser(String browserName, Configuration config, String url) {

    if (browserName.equalsIgnoreCase("Chrome")) {
      System.out.println("");

      String driverFullPath = getDriverPath(config, "ChromeDriver");
      System.setProperty("webdriver.chrome.driver", driverFullPath);
      System.out.println("Chrome Driver Path: " + driverFullPath);

      driver = new ChromeDriver();

      driver.manage().window().maximize();
      driver.get(url);
    }

    else if (browserName.equalsIgnoreCase("Firefox")) {
      String driverFullPath = getDriverPath(config, "FirefoxDriver");

      System.setProperty("webdriver.gecko.driver", driverFullPath);
      System.out.println("Firefox Driver Path: " + driverFullPath);
      driver = new FirefoxDriver();
      driver.manage().window().maximize();
      driver.get(url);
    }

    /* Headless Chrome */
    else if (browserName.equalsIgnoreCase("ChromeHeadless")) {
      System.out.println("chrome headless");

      String driverFullPath = getDriverPath(config, "ChromeDriver");
      System.setProperty("webdriver.chrome.driver", driverFullPath);
      System.out.println("Chrome Driver Path: " + driverFullPath);
      ChromeOptions options = new ChromeOptions();
      options.addArguments("headless");
      options.addArguments("window-size=1200x600"); // Testing large screens: Breakpoint 1024px
      driver = new ChromeDriver(options);
      driver.manage().window().maximize();
      driver.get(url);
    }

    /* Headless Firefox */
    else if (browserName.equalsIgnoreCase("FirefoxHeadless")) {
      System.out.println("Firefox headless");
      FirefoxBinary firefoxBinary = new FirefoxBinary();
      firefoxBinary.addCommandLineOptions("--headless");
      String driverFullPath = getDriverPath(config, "FirefoxDriver");
      System.setProperty("webdriver.gecko.driver", driverFullPath);
      System.out.println("Firefox Driver Path: " + driverFullPath);
      FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setBinary(firefoxBinary);
      driver = new FirefoxDriver(firefoxOptions);

      driver.manage().window().maximize();
      driver.get(url);
    }

    else if (browserName.equalsIgnoreCase("iPhone6")) {

      System.setProperty("webdriver.chrome.driver", getDriverPath(config, "ChromeDriver"));
      Map<String, Object> mobileEmulation = new HashMap<>();
      mobileEmulation.put("deviceName", "iPhone 6");
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
      driver = new ChromeDriver(chromeOptions);
      System.out.println("Chrome driver instance created");
      System.out.println("==============================");
      driver.get(url);
    }

    else if (browserName.equalsIgnoreCase("iPad")) {

      System.setProperty("webdriver.chrome.driver", getDriverPath(config, "ChromeDriver"));
      Map<String, Object> mobileEmulation = new HashMap<>();
      mobileEmulation.put("deviceName", "iPad");
      Map<String, Object> chromeOptions = new HashMap<>();
      chromeOptions.put("mobileEmulation", mobileEmulation);
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
      driver = new ChromeDriver(capabilities);
      System.out.println("Chrome driver instance created");
      System.out.println("==============================");
      driver.get(url);

    }

    // Allow up to a one second delay for elements to become available.
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    return driver;
  }

  /**
   * Create a proxy web driver for a given browser and URL.<br/>
   * Modified from
   * https://github.com/lightbody/browsermob-proxy#using-with-selenium
   *
   * @param browserName name of the browser
   * @param url         URL to open
   * @return WebDriver driver TODO: create headless Chrome driver TODO: reuse
   *         startBrowser where possible
   */
  public static WebDriver startProxyBrowser(String browserName, Configuration config, String url, BrowserMobProxy bmp) {

    ChromeOptions chromeOptions = new ChromeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    // Get the Selenium proxy object and configure it as a desired capability
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(bmp);
    DesiredCapabilities capabilities = new DesiredCapabilities();

    if (browserName.equalsIgnoreCase("Chrome")) {
      System.out.println("Chrome browser");
      String driverFullPath = getDriverPath(config, "ChromeDriver");
      System.setProperty("webdriver.chrome.driver", driverFullPath);
      System.out.println("Chrome Driver Path: " + driverFullPath);

      chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new ChromeDriver(chromeOptions);
      driver.manage().window().maximize();
      driver.get(url); // open proxy page
    } else if (browserName.equalsIgnoreCase("Firefox")) {
      System.out.println("Firefox browser");
      String driverFullPath = getDriverPath(config, "FirefoxDriver");
      System.setProperty("webdriver.gecko.driver", driverFullPath);
      System.out.println("Firefox Driver Path: " + driverFullPath);

      firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new FirefoxDriver(firefoxOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else if (browserName.toLowerCase().contains("headless")) {
      System.out.println("Gecko headless");
      // Firefox/Geckdo driver are the same
      String driverFullPath = getDriverPath(config, "FirefoxDriver");
      System.setProperty("webdriver.gecko.driver", driverFullPath);
      System.out.println("Gecko Driver Path: " + driverFullPath);

      FirefoxBinary firefoxBinary = new FirefoxBinary();
      firefoxBinary.addCommandLineOptions("--headless");
      firefoxOptions.setBinary(firefoxBinary);
      firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new FirefoxDriver(firefoxOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else if (browserName.equalsIgnoreCase("ChromeHeadless")) {
      System.out.println("Chrome headless");
      String driverFullPath = getDriverPath(config, "ChromeDriver");
      System.setProperty("webdriver.chrome.driver", driverFullPath);
      System.out.println("Chrome Driver Path: " + driverFullPath);

      // TODO: handle NoSuchElementException
      // chromeOptions.addArguments("headless");
      // chromeOptions.addArguments("window-size=1200x600"); // Testing large screens:
      // Breakpoint 1024px
      chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
      driver = new ChromeDriver(chromeOptions);
      driver.manage().window().maximize();
      driver.get(url);
    } else {
      throw new IllegalArgumentException("Invalid browser type; check configuration settings.");
    }

    return driver;
  }

}
