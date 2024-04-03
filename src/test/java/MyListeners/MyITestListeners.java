package MyListeners;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.base.Testbase;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyITestListeners extends Testbase implements ITestListener {

    private static String getTestMethodName(ITestResult result){
        return result.getMethod().getConstructorOrMethod().getName();
    }

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
        File file = ((TakesScreenshot) Testbase.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String path = System.getProperty("user.dir")+"./Screenshots/"+DynamicScreenshotName+".png";
            FileUtils.copyFile(file,new File(path));


        } catch (IOException e) {
            e.printStackTrace();
        }
        //saveLogs(result.getMethod().getConstructorOrMethod().getName());
        saveScreenshotOnFailure(Testbase.getDriver());


        Object testClass = result.getInstance();
        driver = ((Testbase) testClass).getDriver();
        if(driver != null){
            System.out.println("Screenshot captured for test case: "+getTestMethodName(result)+" failed");
            saveScreenshotOnFailure(driver);
        }
       // saveLogs(getTestMethodName(result)+ " failed and screenshot taken");

    }
    @Attachment(value = "Screenshot", type="image/png")
    public byte[] saveScreenshotOnFailure(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    /*@Attachment(value="Stacktrace",type = "text/plain")
    public static String saveLogs(String message){
        return message;
    }*/


}
