package com.DsAlgo.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	 public static final ExtentReports extentReports = new ExtentReports();
	    public synchronized static ExtentReports createExtentReports() {
	        ExtentSparkReporter reporter = new ExtentSparkReporter("./target/extent-reports/extent-report.html");
	        reporter.config().thumbnailForBase64(true);
	        reporter.config().setReportName("Extent Report");
	        extentReports.attachReporter(reporter);
	        extentReports.setSystemInfo("Team name", "Dream Team");
	        extentReports.setSystemInfo("Project name", "Dsalgo testng");
	        return extentReports;
	    }

}
