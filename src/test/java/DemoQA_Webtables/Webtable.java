package DemoQA_Webtables;

import Constants.Constants;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.selenium.base.Testbase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Webtable extends Testbase{
    Faker faker = new Faker();
    @BeforeTest
    public void setUp() throws InterruptedException {
        Testbase base = new Testbase();
        base.initialize();
        driver = getDriver();
        driver.get("https://demoqa.com/webtables");
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test
    public void editData() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,500)");
        driver.findElement(By.xpath("//span[@id=\"edit-record-3\"]//*[local-name()=\"svg\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
        WebElement firstname = driver.findElement(By.xpath("//input[@id=\"firstName\"]"));
        firstname.clear();
        String first_name = faker.name().firstName();
        firstname.sendKeys(first_name);
        Thread.sleep(Constants.SHORT_WAIT);
        driver.findElement(By.xpath("//button[@id=\"submit\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test(dependsOnMethods = "editData")
    public void addNewdata() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id=\"addNewRecordButton\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
