package gov.nci.commonobjects;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import gov.nci.framework.PageObjectBase;

public class SiteSearch extends PageObjectBase {


    private  WebElement pageOptionsControl;

    public SiteSearch (WebDriver browser) throws MalformedURLException, UnsupportedEncodingException {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public boolean SearchIsVisible() {
        WebElement control = getPageOptionsControl();
        if (control == null) {
            return false;
        }
        else {
            return control.isDisplayed();
            // return pageOptionsControl.isDisplayed();
        }
    }

    private WebElement getPageOptionsControl() {
        List<WebElement> controls = getBrowser().findElements(By.cssSelector("#siteSearchForm"));
        WebElement pageOptionsControl = null; 
        System.out.println("   Size = " + controls.size());

        if (controls.size() > 0) {
            pageOptionsControl = controls.get(0);
            // return pageOptionsControl;
        }

        return pageOptionsControl;
    }

}