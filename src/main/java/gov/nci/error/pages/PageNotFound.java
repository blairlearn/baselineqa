package gov.nci.error.pages;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import gov.nci.framework.PageObjectBase;
import gov.nci.Utilities.ScrollUtil;

public class PageNotFound extends PageObjectBase {

	WebDriver driver;	
	
	/**
	 * @param driver
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */	
	public PageNotFound(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	

	/**************** Error Page Elements *****************************/
	@FindBy(css = ".error-content-english")
	WebElement txt_err_content_en;
	@FindBy(css = ".error-content-spanish")
	WebElement txt_err_content_es;
	@FindBy(how = How.XPATH, using = "//label[@for='englishl']")
	WebElement btn_search_en;
	@FindBy(how = How.XPATH, using = "//label[@for='spanishl']")
	WebElement btn_search_es;
	@FindBy(css = ".error-searchbar input#nfKeyword")
	WebElement input_err_search;		
	@FindBy(css = ".error-searchbar button#nf-search__button")
	WebElement btn_err_search;
	
	public WebElement getContentEn() {
		return txt_err_content_en;
	}

	public WebElement getContentEs() {
		return txt_err_content_es;
	}
	
	public WebElement betBtnSearchEn() {
		return btn_search_en;
	}

	public WebElement getBtnSearchEs() {
		return btn_search_es;
	}

	public WebElement getInputKeyword() {
		return input_err_search;
	}

	public WebElement getBtnSearch() {
		return btn_err_search;
	}	
	
	/**************** Error Page Actions *****************************/	
	
	/**
	 * Select 'English' radio button
	 */	
	public void selectEnglish() {
		btn_search_en.click();
	}

	/**
	 * Select 'Spanish' radio button
	 */
	public void selectSpanish() {
		btn_search_es.click();
	}

	/**
	 * Places text in the siteside results search input field.
	 * @param searchText the value to insert in the keyword field.
	 */
	public void setSitewideSearchKeyword(String searchText) {
		input_err_search.sendKeys(searchText);
	}

	/**
	 * Clicks the form's search button.
	 */
	public void clickSearchButton() throws MalformedURLException, UnsupportedEncodingException {
		btn_err_search.click();
 	}
	
}
