package gov.nci.framework;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
* Implementation of the RetryAnalyzer interface.
*/
public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 3;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun. TestNg will call
	 * this method every time a test fails. So we can put some code in here to
	 * decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried and false
	 * it not.
	 *
	 */
	@Override
	public boolean retry(ITestResult result) {

		if (counter < retryLimit) {
			counter++;
			System.out.println("Previous test failed. Retry attempt #" + counter);
			return true;
		}
		return false;
	}
	
}