package gov.nci.clinicalTrial.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@Deprecated
public class ApiReference {

	// public static final String API_REFERENCE_H3 = "The Clinical Trials API:
	// Use our data to power your own clinical trial search";

	WebDriver driver;

	@FindBy(how = How.XPATH, using = ".//*[@id='ui-id-4']")
	WebElement apiReferenceH3;
	@FindBy(how = How.XPATH, using = ".//div[@class='api-reference-content']")
	WebElement apiReferenceContent;
	@FindBy(how = How.XPATH, using = ".//a[@href='/syndication/api']")
	WebElement apiReferenceLink;
	@FindBy(how = How.XPATH, using = ".//input[@class='submit button']")
	WebElement btn_Search;

	public ApiReference(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getApiReference() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn_Search);

		return apiReferenceH3;

	}

	public WebElement getApiReferenceText() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn_Search);

		return apiReferenceContent;
	}

	public void verifyApiReferenceLink() {
		apiReferenceLink.click();

	}

}
