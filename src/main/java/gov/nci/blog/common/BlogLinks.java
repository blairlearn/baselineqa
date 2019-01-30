package gov.nci.blog.common;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.nci.Utilities.ScrollUtil;
import gov.nci.framework.ElementChange;
import gov.nci.framework.PageObjectBase;

public class BlogLinks extends PageObjectBase {

	WebDriver driver;

	public BlogLinks(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**************** Blog Common Link Elements *****************************/
	private final String LNK_SUBSCRIBE = ".subscribeRSS a";

	@FindBy(css = LNK_SUBSCRIBE)
	WebElement lnk_subscribe;

	@FindBy(css = ".blog-post-older a")
	WebElement lnk_olderPost;

	@FindBy(css = ".blog-post-newer a")
	WebElement lnk_newerPost;

	@FindBy(css = ".blog-page a.older")
	WebElement lnk_olderSeries;

	@FindBy(css = ".blog-page a.newer")
	WebElement lnk_newerSeries;

	/**************** Blog Common Link Actions *****************************/

	/**
	 * Click the subscription link, but don't navigate away from the page.
	 */
	public void clickSubscribeNoNav() {
		ElementChange.removeHref(driver, LNK_SUBSCRIBE);
		lnk_subscribe.click();
	}

	/**
	 * Click 'Older' post link.
	 */
	public void clickOlderPost() {
		ScrollUtil.scrollIntoview(driver, lnk_olderPost);
		lnk_olderPost.click();
	}

	/**
	 * Click 'Newer' post link.
	 */
	public void clickNewerPost() {
		ScrollUtil.scrollIntoview(driver, lnk_newerPost);
		lnk_newerPost.click();
	}

	/**
	 * Click 'Older' series link.
	 */
	public void clickOlderSeries() {
		ScrollUtil.scrollIntoview(driver, lnk_olderSeries);
		lnk_olderSeries.click();
	}

	/**
	 * Click 'Newer' series link.
	 */
	public void clickNewerSeries() {
		ScrollUtil.scrollIntoview(driver, lnk_newerSeries);
		lnk_newerSeries.click();
	}

	/**
	 * Click a blog element by selector.
	 * 
	 * @param selector
	 */
	public void clickBlogCommon(String selector) {
		WebElement element = driver.findElement(By.cssSelector(selector));
		ScrollUtil.scrollIntoview(driver, element);
		element.click();
	}

}