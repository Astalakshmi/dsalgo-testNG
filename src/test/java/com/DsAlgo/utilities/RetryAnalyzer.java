package com.DsAlgo.utilities;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class RetryAnalyzer implements IRetryAnalyzer {
	   private static final int    maxTry = 1;
	   private              int    count  = 0;
	 @Override
	public boolean retry (final ITestResult iTestResult) {
	       if (!iTestResult.isSuccess ()) {
	           if (this.count < maxTry) {
	               LoggerLoad.info ("Retrying test " + iTestResult.getName () + " with status " + getResultStatusName (
	                   iTestResult.getStatus ()) + " for the " + (this.count + 1) + " time(s).");
	               this.count++;
	               return true;
	           }
	       }
	       return false;
	 }
	 public String getResultStatusName (final int status) {
	       String resultName = null;
	       if (status == 1) {
	           resultName = "SUCCESS";
	       }
	       if (status == 2) {
	           resultName = "FAILURE";
	       }
	       if (status == 3) {
	           resultName = "SKIP";
	       }
	       return resultName;
	   }
		
	}