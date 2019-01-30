package gov.nci.commonobjects;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import gov.nci.framework.PageObjectBase;

public class AZList extends PageObjectBase {

    public AZList (WebDriver browser) throws MalformedURLException, UnsupportedEncodingException {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    /*
     * Testing if the AZList exists on page and is visible
     */
    public boolean IsVisible() {
        List<WebElement> elementExists = getAZListControl();

        if (elementExists.size() > 0) {
            WebElement azElement = elementExists.get(0);
            return azElement.isDisplayed();
        }
        return false;
    }

    private List<WebElement> getAZListControl() {
            List<WebElement> azControls = getBrowser().findElements(By.cssSelector("div.az-list"));
            return azControls;
    }

}