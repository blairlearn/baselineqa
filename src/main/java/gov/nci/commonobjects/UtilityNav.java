package gov.nci.commonobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class UtilityNav {
	WebDriver driver;
	ExtentTest logger;

	public static final String CONTACT_PAGE_URL = "contact";
	public static final String LIVECHAT_PAGE_URL = "https://livehelp.cancer.gov/app/chat/chat_launch";
	public static final String PUBLICATIONS_PAGE_URL = "publications";
	public static final String DICTIONARY_PAGE_URL = "publications/dictionaries/cancer-terms";

	/*************** Page WebElements **********************/
	@FindBy(how = How.XPATH, using = "//div[@id='nvcgSlUtilityBar']")
	WebElement utilityNavBar;
	@FindBy(how = How.CSS, using = "#nvcgSlUtilityBar a")
	List<WebElement> allUtilityBarItems;
	@FindBy(how = How.LINK_TEXT, using = "1-800-4-CANCER")
	WebElement lnk_contact;
	@FindBy(how = How.LINK_TEXT, using = "Live Chat")
	WebElement lnk_liveChat;
	@FindBy(how = How.LINK_TEXT, using = "Publications")
	WebElement lnk_publications;
	@FindBy(how = How.LINK_TEXT, using = "Dictionary")
	WebElement lnk_UtilityDictionary;

	// Initializing the Page Objects
	public UtilityNav(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUtilityNavBar() {
		return utilityNavBar;
	}

	public List<WebElement> getAllUtilityBarItems() {
		return allUtilityBarItems;
	}

	public WebElement getContactLink() {
		return lnk_contact;
	}

	public WebElement getLiveChat() {
		return lnk_liveChat;
	}

	public WebElement getPublications() {
		return lnk_publications;
	}

	public WebElement getUtilityDictionary() {
		return lnk_UtilityDictionary;
	}

	public void clickContactLink() {
		lnk_contact.click();
	}

	public void clickLiveChat() {
		lnk_liveChat.click();
	}

	public void clickPublications() {
		lnk_publications.click();
	}

	public void clickUtilityDictionary() {
		lnk_UtilityDictionary.click();
	}

}
