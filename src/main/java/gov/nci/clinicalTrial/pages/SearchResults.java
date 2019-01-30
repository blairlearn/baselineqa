package gov.nci.clinicalTrial.pages;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import gov.nci.clinicalTrial.common.Checkbox;
import gov.nci.clinicalTrial.common.CriteriaList;
import gov.nci.commonobjects.Pager;
import gov.nci.framework.ElementChange;

/**
 * Represents the Clinicial Trial Search results page.
 * NOTE: Basic and advanced search use the same results page, so there's only one class.
 */
public class SearchResults extends ClinicalTrialPageObjectBase {

	// UI Elements
	@FindBy(how = How.CSS, using = ".cts-results-label")
	WebElement lbl_ResultsCount;

	// Select all checkboxes
	@FindBy(how = How.CSS, using = "#checkAllTop")
	WebElement topSelectAll;
	@FindBy(how = How.CSS, using = "checkAllLower")
	WebElement bottomSelectAll;

	// Toggle button for display of the search criteria.
	@FindBy(how = How.CSS, using = "#ctscb")
	WebElement SearchCriteriaDisplayToggle;

	@FindBy(how = How.CSS, using = ".clinicaltrials-results-criteria-display")
	WebElement SearchCriteria;

	// Results List display area
	@FindBy(how = How.CSS, using = ".cts-results-container")
	WebElement resultsArea;

	private Pager pagerControl;

	public SearchResults(WebDriver browser) throws MalformedURLException, UnsupportedEncodingException {
		this(browser, null);
	}

	/**
	 * Simulates a click on the search criteria show/hide toggle.
	 */
	public void ShowSearchCriteria() {
		SearchCriteriaDisplayToggle.click();
		ElementChange.WaitForText(GetBrowserInstance(), SearchCriteriaDisplayToggle, "Hide Search Criteria");
	}

	public CriteriaList GetSearchCriteria() {

		/**
		 * Build up a list of labels and criteria.
		 * 
		 * This is based on the assumption that the markup is structured as:
		 * 
		 * <table class="table no-auto-enlarge" style="width: 100%;">
		 *   <thead>
		 *     <tr> <!-- Header row ommitted --> </tr>
		 *   </thead>
		 *   <tbody>
		 *     <tr>
		 *       <td><strong> LABEL </strong></td>
		 *       <td> Criterion text </td>
		 *     </tr>
		 *   </tbody>
		 * </table>
		 * 
		 */
		CriteriaList criteria = new CriteriaList();
		int i = 1;
		List<WebElement> list = SearchCriteria.findElements(By.cssSelector("tbody tr"));
		for (WebElement entry : list) {
			List<WebElement> item = entry.findElements(By.cssSelector("td"));
			String label = item.get(0).getText();
			String text = item.get(1).getText();
			criteria.Add(label, text);
		}

		return criteria;
	}

	public SearchResults(WebDriver browser, ClinicalTrialPageObjectBase decorator) throws MalformedURLException, UnsupportedEncodingException {
		super(browser, decorator);
		PageFactory.initElements(browser, this);

		pagerControl = new Pager(browser);
	}

	public String getResultsCountText() {
		return lbl_ResultsCount.getText();
	}

	public boolean getResultsCountVisible() {
		return lbl_ResultsCount.isDisplayed();
	}

	public boolean getResultsListIsVisible() {
		return resultsArea.isDisplayed();
	}

	public Pager getPager() {
		return pagerControl;
	}

	public Checkbox getUpperSelectAll(){
		throw new RuntimeException("Not Implmented");
	}

	public Checkbox getLowerSelectAll() {
		throw new RuntimeException("Not Implmented");
	}


	@FindBy(how=How.XPATH, using=".//div[@class='cts-checkbox checkbox']")
	List <WebElement> check_SearchTrials;

	@FindBy(how=How.XPATH, using=".//input[@type='submit']")
	List <WebElement> btn_PrintSelected;

	public void selectCheckBox(){

		if (!((WebElement)check_SearchTrials.get(1)).isSelected())
		{
			((WebElement)check_SearchTrials.get(1)).click();
		}

		System.out.println("Checkbox selected");
	}

	public void clickPrint(){

		((WebElement) btn_PrintSelected.get(0)).click();
	}
}
