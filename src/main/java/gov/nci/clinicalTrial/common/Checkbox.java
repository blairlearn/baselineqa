package gov.nci.clinicalTrial.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import gov.nci.Utilities.ScrollUtil;

/**
 * Exposes functionality related to a single checkbox
 */
public class Checkbox {

	private WebElement theCheckbox;
	private WebDriver browser;
	private String cssSelector;

	public Checkbox(WebDriver browser, By selector) {
		this.theCheckbox = browser.findElement(selector);
	}

	public Checkbox(WebDriver browser, String cssSelector) {
		this.browser = browser;
		this.theCheckbox = browser.findElement(By.cssSelector(cssSelector));
		this.cssSelector = cssSelector;
	}

	public boolean IsVisible() {
		return theCheckbox.isDisplayed();
	}

	/**
	 * Javascript function to make checkbox visible and set checked=true.
	 * 
	 * @param id
	 */
	public void checkCheckbox(String id) {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript(
				"var x=document.getElementById('" + id + "');x.style.display='inline';x.click();x.checked=true;");
		Actions action = new Actions(browser);
		/// action.pause(500);
	}

	/**
	 * Javascript function to make checkbox visible and set checked=false.
	 * 
	 * @param id
	 */
	public void uncheckCheckbox(String id) {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript(
				"var x=document.getElementById('" + id + "');x.style.display='inline';x.checked=false;");
		Actions action = new Actions(browser);
		/// action.pause(500);
	}

	/**
	 * Uncheck all input elements on a page.
	 */
	public static void uncheckAll(WebDriver browser) {
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript("for(var checkboxes=document.getElementsByTagName('input'),x=0;x<checkboxes.length;x++)"
				+ "'checkbox'==checkboxes[x].type&&(checkboxes[x].checked=!1);");
		Actions action = new Actions(browser);
		/// action.pause(500);
	}
}