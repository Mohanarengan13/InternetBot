package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    public static ExtentReports getReportInstance() {
        if (extentReports == null) {
            String reportPath = "reports/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("InternetBot Automation Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "Selenium Java");
            extentReports.setSystemInfo("Tester", "Mohana");
        }
        return extentReports;
    }
}