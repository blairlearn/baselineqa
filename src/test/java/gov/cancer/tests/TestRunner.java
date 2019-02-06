package gov.cancer.tests;

import gov.cancer.pageobject.PageObjectBase;

public class TestRunner {
  public interface ITestAction {
    void test();
  }

  public static <T extends PageObjectBase> void PerformTest(T page, ITestAction action) {
    try {
      action.test();
    } finally {
      page.close();
    }
  }

}
