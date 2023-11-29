package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    String repName;

    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-"+timeStamp+".html";

        extentSparkReporter = new ExtentSparkReporter("./reports/"+repName);

        extentSparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        extentSparkReporter.config().setReportName("Pet Store Users API");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application", "Pet Store Users API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user", "ashutosh");
    }

    public void onTestSuccess(ITestResult iTestResult){
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.createNode(iTestResult.getName());
        extentTest.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult iTestResult){
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.createNode(iTestResult.getName());
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, iTestResult.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult iTestResult){
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.createNode(iTestResult.getName());
        extentTest.log(Status.SKIP, "Test Skipped");
        extentTest.log(Status.SKIP, iTestResult.getThrowable().getMessage());
    }

    public void onFinish(ITestContext iTestContext){
        extentReports.flush();
    }
}
