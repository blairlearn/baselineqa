package gov.nci.commonobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class LanguageBar {
	WebDriver driver;
	ExtentTest logger;

	public static final String ESPANOL_PAGE_URL = "espanol";

	/*************** Page WebElements **********************/
	@FindBy(how = How.XPATH, using = "//div[@id='LangList1']")
	WebElement languageBar;
	@FindBy(how = How.LINK_TEXT, using = "Español")
	WebElement lnk_espanol;
	@FindBy(how = How.XPATH, using = "//div[@id='LangList1']/ul/li/a")
	WebElement lnk_languageToggle;

	// Initializing the Page Objects
	public LanguageBar(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	public WebElement getLanguageBar() {
		return languageBar;
	}

	public WebElement getLanguageToggle() {
		return lnk_languageToggle;
	}

	public void clickLanguageToggle() {
		lnk_languageToggle.click();
	}

}
