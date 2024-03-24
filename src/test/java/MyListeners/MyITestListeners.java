package MyListeners;

import KatalonDemoProject.Test_1;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyITestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("----------Starting Test---------->>>>"+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("----------Test is successful---------->>>>"+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("----------Test is skipped---------->>>>"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("----------Test failed----------->>>>"+result.getName());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String DynamicScreenshotName = result.getName()+formatter.format(now);
        File file = ((TakesScreenshot) Test_1.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File(System.getProperty("user.dir")+"./Screenshots/"+DynamicScreenshotName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
