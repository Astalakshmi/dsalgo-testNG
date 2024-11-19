package com.DsAlgo.utilities;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


	public class ExtentTestManager {
	    public static Map<Long, ExtentTest> extentTestMap = new HashMap<>();
	    public static ExtentReports extent = ExtentManager.createExtentReports();
	
		public static synchronized ExtentTest getTest() {
	        return extentTestMap.get(Thread.currentThread().getId());
	    }
	   
		public static synchronized ExtentTest startTest(String testName, String desc) {
	        ExtentTest test = extent.createTest(testName, desc);
	        extentTestMap.put(Thread.currentThread().getId(), test);
	        return test;
	    }
	

}
