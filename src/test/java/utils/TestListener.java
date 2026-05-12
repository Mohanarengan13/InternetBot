package utils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.krct.BaseTest;
public class TestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        ScreenshotUtil.captureScreenshot(
                BaseTest.driver, result.getName() + "_PASS");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotUtil.captureScreenshot(
                BaseTest.driver, result.getName() + "_FAIL");
    }
}