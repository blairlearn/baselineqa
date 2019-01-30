package gov.nci.clinicalTrial.pages;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import gov.nci.Utilities.ScrollUtil;
import gov.nci.clinicalTrial.common.Delighter;
import gov.nci.framework.AutoSuggestHelper;

public class BasicSearch extends ClinicalTrialPageObjectBase {

	WebDriver browser;

	/**************** Basic Search Form *********************************/
	@FindBy(how = How.XPATH, using = "//*[@id='form--cts-basic']")
	private WebElement form_Search;

	/*************** Basic Search Page UI Elements **********************/
	@FindBy(how = How.CSS, using = ".search-tip")
	private WebElement txt_SearchTip;
	@FindBy(how = How.CSS, using = ".search-tip a")
	private WebElement lnk_AdvSearch;
	@FindBy(how = How.XPATH, using = ".//input[@id='q']")
	private WebElement txt_CancerType;
	@FindBy(how = How.CSS, using = "#fieldset--type > legend > span")
	private WebElement lbl_CancerType;
	@FindBy(how = How.CSS, using = "#fieldset--type > a")
	private WebElement lnk_CancerTypeHelp;
	@FindBy(how = How.CSS, using = "#fieldset--age > legend > span")
	private WebElement lbl_Age;
	@FindBy(how = How.CSS, using = "#fieldset--age > a")
	private WebElement lnk_AgeHelp;
	@FindBy(how = How.CSS, using = "#fieldset--zip > legend > span")
	private WebElement lbl_Zipcode;
	@FindBy(how = How.CSS, using = "#fieldset--zip > a")
	private WebElement lnk_ZipcodeHelp;
	@FindBy(how = How.XPATH, using = ".//input[@id='z']")
	private WebElement txt_Zipcode;
	@FindBy(how = How.XPATH, using = ".//input[@id='a']")
	private WebElement txt_Age;
	@FindBy(how = How.XPATH, using = ".//*[@id='q']")
	private WebElement msg_CancerType;
	@FindBy(how = How.XPATH, using = ".//*[@id='fieldset--age']/div")
	private WebElement text_Age;
	@FindBy(how = How.XPATH, using = ".//*[@id='fieldset--zip']/div")
	private WebElement text_Zipcode;
	@FindBy(how = How.XPATH, using = ".//input[@type='submit'][@class='submit button'] [@value='Find Trials']")
	private WebElement btn_Search;

	@FindBy(how = How.XPATH, using = "//*[@id='cgvBody']/div[1]")
	private WebElement text_HeaderText;
	@FindBy(how = How.XPATH, using = "//*[@id='cgvBody']/div[1]/p/a[2]")
	private WebElement lnk_NextSteps;
	@FindBy(how = How.CSS, using = ".api-reference-content > p:nth-child(1) > a:nth-child(1)")
	private WebElement lnk_CTSApi;
	@FindBy(how = How.XPATH, using = "//fieldset[@id='fieldset--type']/legend['Cancer Type/Keyword']")
	private WebElement lgd_cancerType;

	// Delighters
	private Delighter delighter_LiveHelp;
	private Delighter delighter_What;
	private Delighter delighter_Which;

	// Error messages
	@FindBy(how = How.XPATH, using  = "//fieldset[@id='fieldset--age']/div[@class='error-msg']")
	private WebElement err_AgeInputDisplay;

	@FindBy(how = How.XPATH, using = "//fieldset[@id='fieldset--zip']/div[@class='error-msg']")
	private WebElement err_ZipCodeInputDisplay;

	// Constructor - Initializing the Page objects
	public BasicSearch(WebDriver browser, ClinicalTrialPageObjectBase decorator) throws MalformedURLException, UnsupportedEncodingException {
		super(browser, decorator);
		this.browser = browser;
		PageFactory.initElements(browser, this);

		delighter_LiveHelp = new Delighter(browser, By.cssSelector(".delighter.cts-livehelp"));
		delighter_What = new Delighter(browser, By.cssSelector(".delighter.cts-what"));
		delighter_Which = new Delighter(browser, By.cssSelector(".delighter.cts-which"));
	}


	public WebElement getHeaderText() {
		return text_HeaderText;
	}

	public WebElement getCancerTypeLabel() {
		return lbl_CancerType;
	}

	public WebElement getCancerTypeMessageElement() {
		return msg_CancerType;
	}

	public WebElement getCancerTypeHelpLink() {
		return lnk_CancerTypeHelp;
	}

	public WebElement getAgeLabel() {
		return lbl_Age;
	}

	public WebElement getAgeHelpLink() {
		return lnk_AgeHelp;
	}

	public WebElement getTextAgeElement() {
		return text_Age;
	}

	public WebElement getZipCodeLabel() {
		return lbl_Zipcode;
	}

	public WebElement getZipCodeHelpLink() {
		return lnk_ZipcodeHelp;
	}

	public WebElement getZipCodeTextElement() {
		return text_Zipcode;
	}

	public WebElement getZipCodeField() {
		return txt_Zipcode;
	}

	public WebElement getSearchTipElement() {
		return txt_SearchTip;
	}

	public WebElement getSearchButton() {
		return btn_Search;
	}

	public WebElement getNextStepsLink() {
		return lnk_NextSteps;
	}

	/**
	 * Delighter accessors
	 */
	public Delighter getLiveHelpDelighter() {
		return delighter_LiveHelp;
	}

	public Delighter getWhatAreDelighter() {
		return delighter_What;
	}

	public Delighter getWhichTrialDelighter() {
		return delighter_Which;
	}

	/**
	 * Error Displays
	 */
	public WebElement getAgeInputError() {
		return err_AgeInputDisplay;
	}

	public WebElement getZipCodeInputError() {
		return err_ZipCodeInputDisplay;
	}

	/**
	 * Clicks the form's search button.
	 *
	 * @return A BasicSearchResults object containing the results of the form
	 *         submission.
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 */
	public SearchResults clickSearchButton() throws MalformedURLException, UnsupportedEncodingException {

		// Make the search button visible so it can be clicked.
		ScrollUtil.scrollIntoview(this.browser, btn_Search);

		expectUrlChange(() ->{
			btn_Search.click();
		});

		SearchResults result = new SearchResults(this.browser);
		return result;
	}

	/**
	 * Simulates pressing "Enter" while the Keyword field has focus.
	 *
	 * @return A BasicSearchResults object containing the results of the form
	 *         submission.
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 */
	public SearchResults pressEnterOnKeywordField() throws MalformedURLException, UnsupportedEncodingException {

		expectUrlChange(() -> {
			txt_CancerType.sendKeys(Keys.RETURN);
		});

		SearchResults result = new SearchResults(this.browser);
		return result;
	}

	/**
	 * Simulates pressing "Enter" while the Age field has focus.
	 *
	 * @return A BasicSearchResults object containing the results of the form
	 *         submission.
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 */
	public SearchResults pressEnterOnAgeField() throws MalformedURLException, UnsupportedEncodingException {

		expectUrlChange(() -> {
			txt_Age.sendKeys(Keys.RETURN);
		});

		SearchResults result = new SearchResults(this.browser);
		return result;
	}

	/**
	 * Simulates pressing "Enter" while the ZIP code field has focus.
	 *
	 * @return A BasicSearchResults object containing the results of the form
	 *         submission.
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 */
	public SearchResults pressEnterOnZipCodeField() throws MalformedURLException, UnsupportedEncodingException {

		expectUrlChange(() -> {
			txt_Zipcode.sendKeys(Keys.RETURN);
		});

		SearchResults result = new SearchResults(this.browser);
		return result;
	}

	/**
	 * Selects the cancer type from the autosuggest list.
	 *
	 * @param cancerType String containing the exact cancer type name to be selected. This must be the full name.
	 */
	public void setExactCancerType(String cancerType) throws MalformedURLException, UnsupportedEncodingException {

		// CSS selector for the autosuggest. This appears in the DOM on page load.
		String autoSuggestListSelector = "ul.ui-autocomplete.ui-front:not(.sitewide-search-menu)";

		// CSS selector for the first list item, relative to the overall list. This appears in the DOM
		// when the list is first rendered.
		String autoSuggestListItemSelector = "li.ui-menu-item";

		AutoSuggestHelper suggestHelper = new AutoSuggestHelper(browser);
		suggestHelper.SelectExact(cancerType, txt_CancerType, autoSuggestListSelector, autoSuggestListItemSelector);
	}

	/**
	 * Places text in the "Cancer type or keyword" field, but doesn't select anything
	 * from autocmplete.
	 *
	 * @param searchText the value to insert in the keyword field.
	 */
	public void setSearchKeyword(String searchText) {
		txt_CancerType.sendKeys(searchText);
	}

	/**
	 * Places a value in the search form's "Age" field.
	 * Allows non-integer values in order to allow tests of error handling.
	 *
	 * @param age the value to place in the age field.
	 */
	public void setSearchAge(String age){
		txt_Age.sendKeys(age);
	}

	/**
	 * Places a value in the search form's "ZIP code" field. Allows non-integer
	 * values in order to allow tests of error handling.
	 *
	 * @param zipCode the value to place in the ZIP code field
	 */
	public void setSearchZip(String zipCode){
		txt_Zipcode.sendKeys(zipCode);
	}

	/**
	 * Gets the URL associated with the Advanced Search link in the
	 * "Search tips" box.
	 */
	public String getAdvancedSearchLinkUrl() {
		return lnk_AdvSearch.getAttribute("href");
	}
}
