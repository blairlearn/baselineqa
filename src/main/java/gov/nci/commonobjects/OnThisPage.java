package gov.nci.commonobjects;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import gov.nci.Utilities.ScrollUtil;
import gov.nci.framework.PageObjectBase;

public class OnThisPage extends PageObjectBase {

	private WebDriver driver;

	public OnThisPage(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify the "On This Page" element exists on page and is visible
	 * 
	 * @return
	 */
	public boolean IsVisible() {
		WebElement otpElement = getOnThisPage();

		if (otpElement != null) {
			return otpElement.isDisplayed();
		}
		return false;
	}

	/**
	 * Get the "On This Page" container element.
	 * @return WebElement
	 */
	private WebElement getOnThisPage() {
		WebElement onThisPage = driver.findElement(By.cssSelector("nav.on-this-page"));
		return onThisPage;
	}

	/**
	 * Get the "On This Page" link elements.
	 * @return WebElement
	 */
	private List<WebElement> getOnThisPageLinks() {
		List<WebElement> otpLinks = getOnThisPage().findElements(By.cssSelector("li a"));
		return otpLinks;
	}

	/**
	 * Get a "On This Page" link at a given index.
	 * 
	 * @param index
	 * @return
	 */
	private WebElement getOnThisPageLink(int index) {
		try {
			List<WebElement> otpLinks = getOnThisPageLinks();
			return otpLinks.get(index);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid 'On This Page' index.");
			return null;
		}
	}

	/**
	 * Get "On This Page" link text at a given index.
	 * 
	 * @param index
	 * @return
	 */
	public String getOnThisPageLinkText(int index) {
		return getOnThisPageLink(index).getText();
	}

	/**
	 * Get "On This Page" href attribute value at a given index.
	 * 
	 * @param index
	 * @return
	 */
	public String getOnThisPageHref(int index) {
		String href = getOnThisPageLink(index).getAttribute("href");
		String anchor = href.substring(href.indexOf("#"));
		return anchor;
		//return getOnThisPageLink(index).getAttribute("href");
	}
	
	/**
	 * Click on a "On This Page" link at a given index.
	 * 
	 * @param index
	 */
	public void clickOnThisPageLink(int index) {		
		WebElement onthisPage = getOnThisPageLink(index);
		ScrollUtil.scrollIntoview(driver, onthisPage);
		onthisPage.click();
	}
	
	
}
