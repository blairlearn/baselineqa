package gov.nci.commonobjects;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import gov.nci.framework.PageObjectBase;

public class PageOptions extends PageObjectBase {

    public PageOptions (WebDriver browser) throws MalformedURLException, UnsupportedEncodingException {
        super(browser);
        // pageOptionsControl = null;
        PageFactory.initElements(browser, this);
    }

    /*
     * Testing if the PageOptionsControl exists on page and is visible
     */
    public boolean IsVisible() {
        List<WebElement> elementExists = getPageOptionsControl();

        if (elementExists.size() > 0) {
            WebElement pocElement = elementExists.get(0);
            return pocElement.isDisplayed();
        }
        return false;
    }

    public boolean ButtonVisible(String buttonSelector) {
        List<WebElement> elementExists = getButtonControl(buttonSelector);

        if (elementExists.size() > 0) {
            WebElement pocButton = elementExists.get(0);
            return pocButton.isDisplayed();
        }
        return false;
    }

    private List<WebElement> getPageOptionsControl() {
            List<WebElement> pocControls = getBrowser().findElements(By.cssSelector("#PageOptionsControl1"));
            // int controlCount = pocControls.size();
            // System.out.println("   Size = " + controlCount);

            return pocControls;
    }

    private List<WebElement> getButtonControl(String buttonSelector) {
            List<WebElement> buttonControls = getBrowser().findElements(By.cssSelector("li." +
                                                                                        buttonSelector));
            // int controlCount = buttonControls.size();
            // System.out.println("   Size = " + controlCount);

            return buttonControls;
    }
}