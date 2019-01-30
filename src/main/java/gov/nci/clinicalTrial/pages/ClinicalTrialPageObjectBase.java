package gov.nci.clinicalTrial.pages;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.nci.Utilities.ClickUtil;
import gov.nci.Utilities.ScrollUtil;
import gov.nci.framework.PageObjectBase;
import gov.nci.framework.PageObjectBase.IPageChangeAction;

/**
 * This is the base class for all Clinical Trial page objects. This class
 * implements the Decorator pattern (https://en.wikipedia.org/wiki/Decorator_pattern)
 * to separate behaviors (e.g. suppress vs. allowing the pro-active chat prompt) into
 * behavior-specific classes, separate from the page objects.
 */
public class ClinicalTrialPageObjectBase extends PageObjectBase {

	// Reference to the decorator object.
	private ClinicalTrialPageObjectBase decorator;
	private WebDriver browser;

	public ClinicalTrialPageObjectBase(WebDriver browser, ClinicalTrialPageObjectBase decorator) throws MalformedURLException, UnsupportedEncodingException {
		super(browser);
		this.decorator = decorator;
		this.browser = browser;
	}

	/**
	 * Get a given input element based on its selector.
	 * 
	 * @param selector
	 * @return
	 */
	private WebElement getSelectedField(String selector) {
		WebElement element = browser.findElement(By.cssSelector(selector));
		return element;
	}

	/**
	 * Get an element attribute.
	 * 
	 * @param selector
	 * @param attribute
	 * @return
	 */
	public String getSelectedFieldAttribute(String selector, String attribute) {
		WebElement element = getSelectedField(selector);
		return element.getAttribute(attribute);
	}
	
	/**
	 * Click a given input element based on its selector.
	 * 
	 * @param selector
	 */
	public void clickSelectedField(String selector) {
		ClickUtil.forceClick(browser, selector);
	}

	/**
	 * Complete a given field based on its selector.
	 * 
	 * @param selector
	 * @param value
	 */
	public void setSelectedField(String selector, String value) {
		ClickUtil.setElementValue(browser, selector, value);
	}
	
	/**
	 * Get a collection of checkbox WebElements.
	 * 
	 * @param CSS selector
	 * @return
	 */
	private List<WebElement> getFieldCollection(String selector) {
		List<WebElement> elements = browser.findElements(By.cssSelector(selector));
		return elements;
	}

	/**
	 * Get a list of attribute values from the getFieldCollection() WebElements.
	 * 
	 * @param CSS selector
	 * @param HTML attribute
	 * @return
	 */
	public List<String> getFieldAttributeCollection(String selector, String attribute) {
		List<String> attrList = new ArrayList<String>();
		List<WebElement> elements = getFieldCollection(selector);
		for (WebElement element : elements) {
			attrList.add(element.getAttribute(attribute));
		}
		return attrList;
	}

	/**
	 * Navigate away from the CTS page.
	 * 
	 * @param url
	 */
	public void abandon(String url) {
		ClickUtil.stall(browser);
		expectUrlChange(() -> {
			browser.get(url);
		});
		
	}
	
}