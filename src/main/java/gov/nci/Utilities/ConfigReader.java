package gov.nci.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.lang3.SystemUtils;

/*To read the Property file*/
public class ConfigReader {
	Properties properties;
	String hostName;

	/**
	 * Constructor.
	 *
	 * @param environment String containing the environment name. Valid values are:
	 *                    qa dt blue red pink stage For production, pass the empty
	 *                    string.
	 *
	 */
	public ConfigReader(String environment) {
		try {
			SetHostName(environment);
			File file = new File("./configuration/config.properties");
			FileInputStream fis = new FileInputStream(file);
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}


	/**
	 * Map of environment names to host names.
	 */
	private static final Map<String, String> environmentHostMap = new TreeMap<String, String>(
			String.CASE_INSENSITIVE_ORDER) {
		private static final long serialVersionUID = 1L;
		{
			put("",      "www");
			put("prod",  "www");
			put("qa",    "www-qa");
			put("dt",    "www-dt-qa");
			put("blue",  "www-blue-dev");
			put("red",   "www-red-dev");
			put("pink",  "www-pink-dev");
			put("stage", "www-stage");
		}
	};

	private void SetHostName(String environment) {
		if (environment == null)
			environment = "";
		hostName = environmentHostMap.get(environment.trim()) + ".cancer.gov";
	}

	public String GetHostName() {
		return hostName;
	}


	/**
	 * Retrieves the identified URL from the configuration file and
	 * returns a version modified to reflect the current environment.
	 *
	 * @param pageURL Identifier for a specific page URL.
	 */
	public String getPageURL(String pageURL) {

		String configUrl = properties.getProperty(pageURL);
		try {
			URL oldUrl = new URL(configUrl);
			URL modifiedURl = new URL(oldUrl.getProtocol(), hostName, oldUrl.getFile());
			return modifiedURl.toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException( String.format("Config entry '%s' does not contain a valid URL. Found: '%s'.", pageURL, configUrl) );
		}
	}

	/**
	 * Whey didn't you just go home? That's your home!
	 * Are you too good for your home? Answer me!
	 * @return homePage URL (String)
	 */
	public String goHome() {
		return getPageURL("HomePage");
	}

	public String getDriverPath(String driverPath) {
		return properties.getProperty(driverPath);
	}

	/**
	 * Gets the base driver path from the configuration properties file
	 *
	 * @return The base path for the drivers
	 */
	public String getDriverBasePath() {
		if (SystemUtils.IS_OS_WINDOWS) {
			return properties.getProperty("DriverPath_Win");
		} else if (SystemUtils.IS_OS_MAC_OSX) {
			return properties.getProperty("DriverPath_Mac");
		} else if (SystemUtils.IS_OS_LINUX) {
			return properties.getProperty("DriverPath_Linux");
		} else {
			throw new RuntimeException("Could not get base driver path, unknown OS");
		}
	}

	/**
	 * Gets the driver name (geckodriver) based on the selected driver
	 * (FirefoxDriver)
	 *
	 * @param driver
	 * @return
	 */
	public String getDriverName(String driver) {
		return properties.getProperty("DriverName_" + driver);
	}

	public String getExtentReportPath() {
		return properties.getProperty("ExtentReports");
	}

	public String getScreenShotsPath() {
		return properties.getProperty("ScreenShots");
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

}
