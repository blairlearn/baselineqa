package gov.cancer.tests.crosscutting;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelManager;
import gov.cancer.pageobject.crosscutting.BreadCrumbPage;
import gov.cancer.tests.TestObjectBase;

/**
 * Tests for pages with bread crumbs.
 */
public class BreadCrumb_Test extends TestObjectBase {

  @Test
  public void breadcrumbIsVisible(){

    BreadCrumbPage page = new BreadCrumbPage("about-cancer/coping/feelings");

    try {
      Assert.assertTrue(page.isBreadCrumbVisible(), "Bread crumb is visible.");
    }
    finally {
      page.close();
    }
  }


}
