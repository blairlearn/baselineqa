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

public class RelatedResources extends PageObjectBase {

	private WebDriver driver;

	// Constructor to initialize the page object
	public RelatedResources(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify the Related Resources list exists on page and is visible
	 * 
	 * @return
	 */
	public boolean IsVisible() {
		List<WebElement> elementExists = getRelatedResources();

		if (elementExists.size() > 0) {
			WebElement element = elementExists.get(0);
			return element.isDisplayed();
		}
		return false;
	}

	/**
	 * Get a list of "Related Resources" elements.
	 * 
	 * @return
	 */
	private List<WebElement> getRelatedResources() {
		List<WebElement> relatedResources = driver.findElements(By.cssSelector(".related-resources.list a"));
		return relatedResources;
	}

	/**
	 * Get a "Related Resources" link at a given index.
	 * 
	 * @param index
	 * @return
	 */
	private WebElement getRelatedResourcesItem(int index) {
		try {
			List<WebElement> relatedResources = getRelatedResources();
			return relatedResources.get(index);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid related resources index.");
			return null;
		}
	}

	/**
	 * Get "Related Resources" link text at a given index.
	 * 
	 * @param index
	 * @return
	 */
	public String getRelatedResourcesLinkText(int index) {
		return getRelatedResourcesItem(index).getText();
	}

	/**
	 * Click on a "Related Resources" link at a given index.
	 * 
	 * @param index
	 */
	public void clickRelatedResourcesLink(int index) {
		WebElement relatedResource = getRelatedResourcesItem(index);
		ScrollUtil.scrollIntoview(driver, relatedResource);
		relatedResource.click();
	}

}
