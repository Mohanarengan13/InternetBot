package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getReportInstance() {
        if (extentReports == null) {
            String reportPath = "reports/ExtentReport.html";
            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                reportFile.delete();
            }
            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("MockHack Automation Results");
            sparkReporter.config().setReportName("Automation Execution Dashboard");
            sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Project", "MockHack");
            extentReports.setSystemInfo("Framework", "Selenium + TestNG");
            extentReports.setSystemInfo("Tester", "Mohana");
            extentReports.setSystemInfo("Browser", "Chrome");
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extentReports;
    }
}