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

  @Test(dataProvider = "getBreadCrumbPaths")
  public void breadcrumbIsVisible(String path){

    TestRunner.run(BreadCrumbPage.class, path, (BreadCrumbPage page)->{

      Assert.assertTrue(page.isBreadCrumbVisible(), "Bread crumb is visible.");

    });

  }


  /**
   * Retrieves a list of paths to pages which are expected to have breadcrumbs.
   *
   * @return An iterable list of single element arrays, each containing a single
   * path.
   */
  @DataProvider(name = "getBreadCrumbPaths")
  public Iterator<Object> getBreadCrumbPaths(){

    Iterator<Object> raw = loadBreadCrumbData();
    ArrayList<Object> processed = new ArrayList<Object>();

    /**
     * Converts from
     * [
     *    [path, type, whatever],
     *    [path, type, whatever],
     *      :
     * ]
     *
     * to
     *
     * [
     *    [path],
     *    [path],
     *      :
     * ]
     */

    raw.forEachRemaining(item -> {
      Object path = ((Object[])item)[0];
      processed.add(new Object[]{path});
    });

    return processed.iterator();
  }


  /**
   * Single point of code for retriving bread crumb data from
   * the bread crumb data file.  This method retrieves all the
   * data from the SimplePageList sheet.
   *
   *   [
   *      [path, type, whatever],
   *      [path, type, whatever],
   *        :
   *   ]
   *
   * @return An iterable collection of data arrays.
   */
  private Iterator<Object> loadBreadCrumbData() {

    ExcelManager excelReader = ExcelManager.create(getDataFilePath("BreadCrumbData.xlsx"));

    String SHEET = "SimplePageList";

    ArrayList<Object> objects = new ArrayList<Object>();
    int rowCount = excelReader.getRowCount(SHEET);
    for(int rowNum = 2; rowNum <= rowCount; ++rowNum){
      String path = excelReader.getCellData(SHEET, "path", rowNum);
      String pageType = excelReader.getCellData("SimplePageList", "type", rowNum);

      Object[] row = {path, pageType};
      objects.add(row);
    }

    return objects.iterator();
  }

}
