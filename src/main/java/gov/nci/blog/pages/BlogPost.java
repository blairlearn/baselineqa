package gov.nci.blog.pages;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.nci.framework.ElementChange;
import gov.nci.framework.PageObjectBase;
import gov.nci.Utilities.ClickUtil;
import gov.nci.Utilities.ScrollUtil;
import gov.nci.blog.common.BlogLinks;
import gov.nci.blog.common.BlogRightRail;

public class BlogPost extends PageObjectBase {

	WebDriver driver;
	BlogRightRail rightRail;
	BlogLinks blogLinks;
	
	/**
	 * @param driver
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */	
	public BlogPost(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		this.rightRail = new BlogRightRail(driver);
		this.blogLinks = new BlogLinks(driver);
		PageFactory.initElements(driver, this);
	}
	
	public BlogRightRail getRightRail() {
		return this.rightRail;
	}

	public BlogLinks getBlogLinks() {
		return this.blogLinks;
	}
	
	/**************** Blog Post Page Elements *****************************/
	private final String SELECTOR_DEF = "#cgvBody a.definition";
	
	@FindBy(css = "#cgvBody a:not(.definition)")
	WebElement lnk_body;

	@FindBy(css = SELECTOR_DEF)
	WebElement lnk_definition;
	
	@FindBy(css = "#blog-cards .feature-card a")	
	WebElement lnk_blogFeatureCard;
	
	public String getBodyLinkText() {
		return lnk_body.getText();
	}

	public String getDefinitionLinkText() {
		return lnk_definition.getText();
	}
	
	public String getRecommendedLinkText() {
		return lnk_blogFeatureCard.getText();
	}

	/**************** Blog Post Page Actions *****************************/

	/**
	 * Clicks on the first "Recommended From NCI" card.
	 */
	public void clickBodyLink() {
		ScrollUtil.scrollIntoview(driver, lnk_body);
		lnk_body.click();
 	}

	/**
	 * Click on first definition LINK.
	 */
	public void clickDefinition() {
		ScrollUtil.scrollIntoview(driver, lnk_definition);
		lnk_definition.click();
 	}	
	
	/**
	 * Click on first definition without opening a popup.
	 */
	public void clickDefinitionNoPopup() {
		ScrollUtil.scrollIntoview(driver, lnk_definition);
		ElementChange.clearAttribute(driver, SELECTOR_DEF, "onclick");
		lnk_definition.click();
 	}	
	
	/**
	 * Clicks on the first "Recommended From NCI" card.
	 */
	public void clickRecommended() {
		ClickUtil.forceClick(driver, lnk_blogFeatureCard);
 	}
		
}
