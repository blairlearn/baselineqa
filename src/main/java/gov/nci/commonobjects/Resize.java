package gov.nci.commonobjects;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import gov.nci.framework.PageObjectBase;

public class Resize extends PageObjectBase {

	public WebDriver driver;
	public Actions action;

	// Constructor to initialize the page object
	public Resize(WebDriver driver) throws MalformedURLException, UnsupportedEncodingException {
		super(driver);
		this.driver = driver;
		this.action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Browser view breakpoints
	 */
	public static Dimension small = new Dimension(600, 800);
	public static Dimension med = new Dimension(700, 800);
	public static Dimension large = new Dimension(1100, 800);
	public static Dimension xlarge = new Dimension(1600, 800);

	/**
	 * All the proxy browser 'actions' go in here. These are not tests, but things
	 * that we do to fire off analytics events. These actions will populate our list
	 * of har objects, which will then be tested.
	 */
	public void toSmall() {
		driver.manage().window().setSize(small);
		/// action.pause(500).perform();
	}

	public void toMed() {
		driver.manage().window().setSize(med);
		/// action.pause(500).perform();
	}

	public void toLarge() {
		driver.manage().window().setSize(large);
		/// action.pause(500).perform();
	}

	public void toXlarge() {
		driver.manage().window().setSize(xlarge);
		/// action.pause(500).perform();
	}

	public void maximize() {
		driver.manage().window().maximize();
		/// action.pause(500).perform();
	}

}
