package gov.nci.clinicalTrial.pages;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;


/**
 * Behavior-specific page object to block the clinical trial proactive
 * chat prompt from being rendered.
 */
public class SuppressChatPromptPageObject extends ClinicalTrialPageObjectBase {

    public SuppressChatPromptPageObject(WebDriver browser, ClinicalTrialPageObjectBase decorator)
            throws MalformedURLException, UnsupportedEncodingException {
        super(browser, decorator);

        // Block the proactive chat prompt
        Cookie optionCookie = new Cookie("ProactiveLiveHelpForCTSPrompt-opt", "true", null);
        browser.manage().addCookie(optionCookie);
    }
}