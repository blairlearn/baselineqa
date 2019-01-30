package gov.nci.commonobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Pseudo page object representing any page in the system. The Banner class
 * is used solely for verifying attributes of a page's banner.
 * <p>
 * The page to be tested is assumed to have been loaded prior to instantiation.
 */
public class Banner {

	@FindBy(how = How.CSS, using = ".nci-logo-pages img")
	WebElement banner;

	/**
	 * Constructor
	 * 
	 * @param driver WebDriver instance representing the browser.
	 */
	public Banner(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Reports whether the banner is displayed.
	 * 
	 * @return True if the banner is displayed, false otherwise.
	 */
	public boolean isDisplayed() {
		return banner.isDisplayed();
	}

	/**
	 * Reports the text of the banner's alt= attribute.
	 * 
	 * @return String containing the alt text. Null if the attribute is missing.
	 */
	public String getAltText() {
		return banner.getAttribute("alt");
	}
}
