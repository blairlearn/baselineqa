package gov.cancer.clinicaltrials;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import gov.cancer.utilities.BrowserManager;
import gov.cancer.utilities.ExcelManager;
import gov.cancer.clinicaltrials.common.ApiReference;
import gov.cancer.clinicaltrials.pages.BasicSearch;
import gov.cancer.clinicaltrials.pages.SearchResults;
import gov.cancer.clinicaltrials.pages.SuppressChatPromptPageObject;
import gov.cancer.commonobjects.Banner;
import gov.cancer.commonobjects.BreadCrumb;
import gov.cancer.framework.ParsedURL;

public class BasicSearch_Test extends BaseClass {

	// WebDriver driver;
	private final String TESTDATA_SHEET_NAME = "BasicSearch";
	private final String BASIC_CANCERTYPE_SHEET_NAME = "Basic Cancer Types";
	private final String API_REFERENCE_H3 = "The Clinical Trials API: Use our data to power your own clinical trial search";

	private final String BREAD_CRUMB = "Home\nAbout Cancer\nCancer Treatment\nClinical Trials Information";

	private static final String KEYWORD_PARAM = "q";
	private static final String CANCERTYPE_PARAM = "t";
	private static final String AGE_PARAM = "a";
	private static final String ZIPCODE_PARAM = "z";
	private static final String RESULTS_LINK = "rl";

	BasicSearch basicSearch;
	String resultPageUrl;
	String advSearchPageUrl;
	String testDataFilePath;

	@BeforeClass
	@Parameters({ "browser" })
	public void setup(String browser) {
		logger = report.startTest(this.getClass().getSimpleName());

		pageURL = config.getPageURL("BasicClinicalTrialSearchURL");

    driver.get(pageURL);

		try {
			// Create search page with chat prompt suppressed.
			SuppressChatPromptPageObject chatPrompt = new SuppressChatPromptPageObject(driver, null);
			basicSearch = new BasicSearch(driver, chatPrompt);
		} catch (Exception e) {
			basicSearch = null;
			logger.log(LogStatus.ERROR, "Error creating Basic Search page.");
		}

		testDataFilePath = config.getProperty("ClinicalTrialData");
  }

	// Verifying the UI components of Basic Search

	/**
	 * Verifies presence, visibility, and content of the search header text
	 */
	@Test
	public void uiVerificationHeaderText() {


		WebElement headerTextElement = basicSearch.getHeaderText();
		Assert.assertTrue(headerTextElement.isDisplayed(), "Basic CTS header text not displayed");
		Assert.assertTrue(headerTextElement.getText().contains("Steps to Find a Clinical Trial"),
        "header text mismatch");
	}

	/**
	 * Verifies presence, visibility, and content of the search help icon
	 */
	@Test
	public void uiVerificationSearchTip() {

		WebElement searchTipElememt = basicSearch.getSearchTipElement();
		Assert.assertTrue(searchTipElememt.isDisplayed(), "Search Tip not displayed");
		Assert.assertTrue(
				searchTipElememt.getText().contains("Search Tip: For more search options, use our advanced search"),
				"Search Tip text mismatched");
	}

	/**
	 * Verifies presence, visibility, and content of the cancer type input field.
	 */
	@Test
	public void uiVerificationCancerType() {

		WebElement cancerTypeLabel = basicSearch.getCancerTypeLabel();
		Assert.assertTrue(cancerTypeLabel.isDisplayed(), "Cancer Type label not displayed");

		WebElement cancerTypeHelpLink = basicSearch.getCancerTypeHelpLink();
		Assert.assertTrue(cancerTypeHelpLink.isDisplayed(), "Cancer Type help icon not displayed");

		WebElement cancerTypeMessageElement = basicSearch.getCancerTypeMessageElement();
		Assert.assertTrue(cancerTypeMessageElement.isDisplayed(), "Cancer Type Placeholder message not displayed");
		Assert.assertTrue(
				cancerTypeMessageElement.getAttribute("placeholder").contains("Start typing to select a cancer type"),
				"Cancer type message text mismatch");
	}

	/**
	 * Verifies presence, visibility, and content of the age input field.
	 */
	@Test
	public void uiVerificationAgeField() {

		WebElement ageLabel = basicSearch.getAgeLabel();
		Assert.assertTrue(ageLabel.isDisplayed(), "Age label not displayed");

		WebElement ageHelpLink = basicSearch.getAgeHelpLink();
		Assert.assertTrue(ageHelpLink.isDisplayed(), "Age help icon is displayed");

		WebElement ageHelpText = basicSearch.getTextAgeElement();
		Assert.assertTrue(ageHelpText.isDisplayed(), "Age help text not displayed");
		Assert.assertTrue(ageHelpText.getText().contains("Your age helps determine which trials are right for you."),
				"Age text mismatch");
	}

	/**
	 * Verifies presence, visibility, and content of the ZIP code input field.
	 */
	@Test
	public void uiVerificationZipCodeField() {

		WebElement zipCodeField = basicSearch.getZipCodeField();
		Assert.assertTrue(zipCodeField.isDisplayed(), "ZipCode Input field not displayed.");

		WebElement zipCodeLabel = basicSearch.getZipCodeLabel();
		Assert.assertTrue(zipCodeLabel.isDisplayed(), "ZipCode label not displayed.");

		WebElement zipCodeHelpLink = basicSearch.getZipCodeHelpLink();
		Assert.assertTrue(zipCodeHelpLink.isDisplayed(), "ZipCode help icon not displayed.");

		WebElement zipCodeTextElement = basicSearch.getZipCodeTextElement();
		Assert.assertTrue(zipCodeTextElement.isDisplayed(), "ZipCode help text not displayed.");
		Assert.assertTrue(zipCodeTextElement.getText().contains("Show trials near this U.S. ZIP code."),
				"Zip code help text mismatch.");
	}

	/**
	 * Verifies presence, visibility, and content of the search button
	 */
	@Test
	public void uiVerificationSearchButton() {

		WebElement searchButton = basicSearch.getSearchButton();
		Assert.assertTrue(searchButton.isDisplayed(), "Find Results button not displayed");
	}

	/**
	 * Verify link to the clinical trials "Next Steps" page.
	 */
	@Test
	public void uiVerificationNextSteps() {

		WebElement nextStepsLink = basicSearch.getNextStepsLink();
		Assert.assertTrue(nextStepsLink.isDisplayed(), "Next steps link not displayed");
		Assert.assertEquals(nextStepsLink.getTagName(), "a", "Next steps link not an 'a' tag.");

		String link = nextStepsLink.getAttribute("href");
		Assert.assertNotEquals(link, null, "Next steps link is missing");

		try {
			URL url = new URL(link);
			Assert.assertEquals(url.getPath(), "/about-cancer/treatment/clinical-trials/search/trial-guide",
					"Next steps link path is mismatched.");
		} catch (MalformedURLException ex) {
			Assert.fail("Error parsing Next steps URL");
		}
	}

	/**
	 * Test search with all default vaules.
	 */
	//@Test
	public void searchDefault() {
		try {
			SearchResults result = basicSearch.clickSearchButton();

			// Verify the search parameters were set correctly.
			ParsedURL url = result.getPageUrl();
			Assert.assertEquals(url.getPath(), "/about-cancer/treatment/clinical-trials/search/r",
					"Unexpected URL path.");

			Assert.assertEquals(url.getQueryParam(KEYWORD_PARAM), "", "Keyword parameter not matched.");
			Assert.assertEquals(url.getQueryParam(CANCERTYPE_PARAM), "", "Cancer Type parameter not matched.");
			Assert.assertEquals(url.getQueryParam(AGE_PARAM), "", "Age parameter not matched.");
			Assert.assertEquals(url.getQueryParam(ZIPCODE_PARAM), "", "ZIP code parameter not matched.");
			Assert.assertEquals(url.getQueryParam(RESULTS_LINK), "1", "Results Link parameter not matched.");

		} catch (MalformedURLException | UnsupportedEncodingException e) {
			Assert.fail("Error loading result page.");
			e.printStackTrace();
		}
	}

}
