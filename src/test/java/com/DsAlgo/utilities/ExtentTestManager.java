package com.DsAlgo.utilities;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling createExtentReports() method from
 * ExtentManager. At startTest() method, an instance of ExtentTest created and
 * put into extentTestMap with current thread id. At getTest() method, return
 * ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
	public static Map<Long, ExtentTest> extentTestMap = new HashMap<>();
	public static ExtentReports extent = ExtentManager.createExtentReports();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((long) Thread.currentThread().getId());
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.createTest(testName, desc);
		extentTestMap.put((long) Thread.currentThread().getId(), test);
		return test;
	}
}
