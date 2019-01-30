package gov.nci.commonobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Pager {

    @FindBy(how=How.CSS, using=".pager")
    private  WebElement pagerControl;

    public Pager(WebDriver browser) {
        PageFactory.initElements(browser, this);
    }

    public boolean IsVisible() {
        return pagerControl.isDisplayed();
    }
}