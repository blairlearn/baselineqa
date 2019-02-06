package gov.cancer.tests.crosscutting;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelManager;
import gov.cancer.pageobject.crosscutting.BreadCrumbPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with bread crumbs.
 */
public class BreadCrumb_Test extends TestObjectBase {

  @Test(dataProvider = "BreadCrumbData")
  public void breadcrumbIsVisible(String path){

    TestRunner.PerformTest(BreadCrumbPage.class, path, (BreadCrumbPage page)->{

      Assert.assertTrue(page.isBreadCrumbVisible(), "Bread crumb is visible.");

    });

  }

  /**
   * TODO: Make loading data generic.
   * @return
   */
  @DataProvider(name = "BreadCrumbData")
  public Iterator<Object> BreadCrumbDataLoader() {

    ExcelManager excelReader = new ExcelManager("./test-data/BreadCrumbData.xlsx");

    ArrayList<Object> objects = new ArrayList<Object>();
    int rowCount = excelReader.getRowCount("SimplePageList");
    int colCount = excelReader.getColumnCount("SimplePageList");
    for(int rowNum = 2; rowNum <= rowCount; ++rowNum){
      for(int colNum = 1; colNum <= colCount; ++colNum){
        String data = excelReader.getCellData("SimplePageList", colNum, rowNum);
        Object ob[] = { data };
        objects.add(ob);
      }
    }

    return objects.iterator();
  }

}
