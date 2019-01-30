package gov.nci.clinicalTrial.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Exposes funtionality related to a single delighter.
 */
public class Delighter {
    
    private WebElement theDelighter;

    /**
     * Constructor
     * 
     * @param delighter A WebElement instance referencing a single delighter.
     */
    public Delighter(WebDriver browser, By selector) {
        this.theDelighter = browser.findElement(selector);
    }

    /**
     * Reports whether the delighter is displayed. Wrapper for WebElement.isDisplayed().
     * 
     * @return True if the delighter is displayed, false otherwise.
     */
    public boolean isDisplayed() {
        return theDelighter.isDisplayed();
    }

    /**
     * Reports the path portion of the delighter's URL.
     */
    public String getLinkPath() {
        WebElement link = theDelighter.findElement(By.cssSelector("a"));
        String linkString = link.getAttribute("href");

        URL parsedUrl;
		try {
			parsedUrl = new URL(linkString);
		} catch (MalformedURLException e) {
			throw new RuntimeException(String.format("Delighter link '%s' is not a valid URL.", linkString));
		}

        return parsedUrl.getFile();
    }

}