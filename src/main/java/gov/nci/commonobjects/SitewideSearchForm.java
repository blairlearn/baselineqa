package gov.nci.commonobjects;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SitewideSearchForm {
	
	// TODO: handle JS error on console
	// TODO: refactor & replace WebElements (see Checkbox)
	// Constructor to initialize the page component object
	public SitewideSearchForm(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		PageFactory.initElements(driver, this);
	}

	/**************** Sitewide Search Form Elements *****************************/
	@FindBy(css = "#siteSearchForm")
	WebElement form_sw_search;
	@FindBy(css = "#siteSearchForm label")
	WebElement lbl_sw_search;
	@FindBy(css = "#siteSearchForm input#swKeyword")
	WebElement input_sw_search;
	@FindBy(css = "#siteSearchForm button#sitesearch")
	WebElement btn_sw_search;
	
	public WebElement getSitewideSearchForm() {
		return form_sw_search;
	}

	public WebElement getSitewideSearchFormLabel() {
		return lbl_sw_search;
	}

	public WebElement getSitewideSearchFormInput() {
		return input_sw_search;
	}

	public WebElement getSitewideSearchFormButton() {
		return btn_sw_search;
	}
	
	/**
	 * Places text in the siteside search input field.
	 * @param searchText the value to insert in the keyword field.
	 */
	public void setSitewideSearchKeyword(String searchText) {
		input_sw_search.sendKeys(searchText);
	}
	
	/**
	 * Clicks the form's search button.
	 */
	public void clickSearchButton() throws MalformedURLException, UnsupportedEncodingException {
		btn_sw_search.click();
	}
	
	/** 
	 * Fill out search form and click submit.
	 * @param searchText
	 */
	public void doSitewideSearch(String searchText) {
		try {
			setSitewideSearchKeyword(searchText);
			clickSearchButton();
		} catch (Exception ex) {
			System.out.println("Error submitting sitewide search");
		}
	}
	
	
}
