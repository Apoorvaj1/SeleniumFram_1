package DemoQA_Alert;

import Constants.Constants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.selenium.base.Testbase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlertTest_1 extends Testbase {
    JavascriptExecutor js;
    @BeforeTest
    public void setUp() throws InterruptedException {
        Testbase base = new Testbase();
        base.initialize();
        driver = getDriver();
        driver.get("https://demoqa.com/alerts");
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test
    public void alert_1() throws InterruptedException {
        js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,500)");
        driver.findElement(By.xpath("//button[@id=\"alertButton\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
        driver.switchTo().alert().accept();
        Thread.sleep(Constants.SHORT_WAIT);
    }

    @Test
    public void alert_2() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id=\"timerAlertButton\"]")).click();
        Thread.sleep(Constants.MEDIUM_WAIT);
        driver.switchTo().alert().accept();
        Thread.sleep(Constants.MEDIUM_WAIT);
    }
    @Test
    public void alert_3() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id=\"confirmButton\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
        driver.switchTo().alert().accept();
        Thread.sleep(Constants.SHORT_WAIT);
        String success_text = driver.findElement(By.xpath("//span[@id=\"confirmResult\"]")).getText();
        System.out.println(success_text);
        driver.findElement(By.xpath("//button[@id=\"confirmButton\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
        driver.switchTo().alert().dismiss();
        Thread.sleep(Constants.SHORT_WAIT);
        String cancel_text = driver.findElement(By.xpath("//span[@id=\"confirmResult\"]")).getText();
        System.out.println(cancel_text);

    }
    @Test
    public void alert_4() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id=\"promtButton\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println("Alert text is: "+text);
        alert.sendKeys("Hello Apoorv");
        Thread.sleep(Constants.MEDIUM_WAIT);
        alert.accept();
        Thread.sleep(Constants.SHORT_WAIT);
        String text1 = driver.findElement(By.xpath("//span[@id=\"promptResult\"]")).getText();
        System.out.println(text1);
    }
    @AfterTest
    public void tearDown(){
        getDriver().quit();
    }
}
