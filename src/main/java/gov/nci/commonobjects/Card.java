package gov.nci.commonobjects;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import gov.nci.Utilities.ClickUtil;
import gov.nci.Utilities.ScrollUtil;
import gov.nci.framework.PageObjectBase;

public class Card extends PageObjectBase {

	private WebDriver driver;

	public Card(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify the Cards exist on page and are visible.
	 * 
	 * @param selector
	 * @return
	 */
	public boolean IsVisible(String selector) {
		List<WebElement> elementExists = getCards(selector);

		if (elementExists.size() > 0) {
			WebElement element = elementExists.get(0);
			return element.isDisplayed();
		}
		return false;
	}

	/**
	 * Get a list of Card elements by selector.
	 * 
	 * @param selector
	 * @return
	 */
	private List<WebElement> getCards(String selector) {
		List<WebElement> relatedResources = driver.findElements(By.cssSelector(selector));
		return relatedResources;
	}

	/**
	 * Using XPATH, return an element based on the contained text.
	 * @param text
	 * @return
	 */
	// TODO: make this a reusable utility function
	private WebElement getCardElementByText(String text) {
		WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
		return element;
	}

	
	/**
	 * Get a Card item at a given index.
	 * 
	 * @param selector
	 * @param index
	 * @return Webelement
	 */
	private WebElement getCard(String selector, int index) {
		try {
			List<WebElement> cards = getCards(selector);
			return cards.get(index);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid card index.");
			return null;
		}
	}

	/**
	 * Get text for a selected Card.
	 * 
	 * @param selector
	 * @param index
	 * @return
	 */
	public String getCardText(String selector, int index) {
		WebElement cardTitle = getCard(selector, index);
		return cardTitle.getText();
	}

	/**
	 * Get text for the first Card.
	 * 
	 * @param selector
	 * @return
	 */
	public String getCardText(String selector) {
		return getCardText(selector, 0);
	}
	
	/**
	 * Click on a Card link at a given index.
	 * 
	 * @param selector
	 * @param index
	 */
	public void clickCardLink(String selector, int index) {
		WebElement cardLink = getCard(selector, index);
		ScrollUtil.scrollIntoview(driver, cardLink);
		cardLink.click();
	}

	/**
	 * Click on the first Card link for a given card.
	 * 
	 * @param selector
	 */
	public void clickCardLink(String selector) {
		clickCardLink(selector, 0);
	}

	/**
	 * Click on a given text value.
	 * @param text
	 */
	public void clickCardText(String text) {
		WebElement element = getCardElementByText(text);
		ClickUtil.forceClick(driver, element);
	}
	
}
