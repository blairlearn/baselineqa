package gov.nci.blog.pages;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.nci.framework.ElementChange;
import gov.nci.framework.PageObjectBase;
import gov.nci.Utilities.ScrollUtil;
import gov.nci.blog.common.BlogLinks;
import gov.nci.blog.common.BlogRightRail;

public class BlogSeries extends PageObjectBase {

	WebDriver driver;
	BlogRightRail rightRail;
	BlogLinks blogLinks;
	
	/**
	 * @param driver
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */	
	public BlogSeries(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
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
	
	/**************** Blog Series Page Elements *****************************/

	@FindBy(css = ".dynamic.list li.list-item a.title")
	WebElement lnk_postTitle;


	/**************** Blog Series Page Actions *****************************/
	
	/**
	 * Click on the first Blog Post title
	 */
	public void clickBlogPostTitle() {
		ScrollUtil.scrollIntoview(driver, lnk_postTitle);
		lnk_postTitle.click();
 	}

		
}
