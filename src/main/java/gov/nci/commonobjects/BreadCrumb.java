package gov.nci.commonobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Pseudo page object representing any page in the system. The BreadCrumb class
 * is used solely for verifying attributes of a page's bread crumb trail.
 * <p>
 * The page to be tested is assumed to have been loaded prior to instantiation.
 */
public class BreadCrumb {

	@FindBy(how = How.CSS, using = ".breadcrumbs")
	WebElement breadCrumb;

	/**
	 * Constructor
	 * 
	 * @param driver WebDriver instance representing the browser.
	 */
	public BreadCrumb(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Retrieves the innerText from all breadcrumb elements, removing any leading/trailing
	 * whitespace on each node of the trail.
	 */
	public String getBreadCrumbText() {
		String Breadcrumb = breadCrumb.getText();
		return Breadcrumb;
	}

}
