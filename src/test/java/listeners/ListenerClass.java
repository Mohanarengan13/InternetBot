package listeners;

import com.krct.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;
public class ListenerClass implements ITestListener {
    ExtentReports extentReports = ExtentReportManager.getReportInstance();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        if (BaseTest.driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(
                    BaseTest.driver,
                    result.getMethod().getMethodName());
            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}