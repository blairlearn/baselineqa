package gov.nci.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * === DO NOT USE THIS CLASS IN ANY NEW CODE! ===
 * 
 * This class consists of "miscellaneous" functions and will be refactored.
 * 
 * "Scroll into view" methods should become part of ScrollUtil.
 * 
 * "Wait for Navigation" methods should be moved to a suitable class in gov.nci.framework
 *  	(Potentially, but not for certain, PageObjectBase).
 * 
 * "Wait fo element" methods need to be considered on a case-by-case basis whether they
 * 		should be deleted, or else moved to a suitable helper class in gov.nci.framework.
 * 
 */
@Deprecated
public class FunctionLibrary {

	public static void scrollIntoview(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);" + "window.scrollBy(0,-100);",
				element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void scrollIntoView(WebDriver driver, int l) {
		((JavascriptExecutor) driver).executeScript("scroll(0, " + l + ");");
	}

	public static void waitForElement(WebDriver driver, WebElement elementToBeLoaded) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	}

	public static boolean waitForTitle(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait.until(ExpectedConditions.titleContains(title));
	}

	public static boolean waitElementToContainText(WebDriver driver, WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public static boolean waitURLToBe(WebDriver driver, String url) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait.until(ExpectedConditions.urlToBe(url));
	}

}
