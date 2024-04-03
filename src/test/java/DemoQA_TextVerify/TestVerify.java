package DemoQA_TextVerify;

import Constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.selenium.base.Testbase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestVerify extends Testbase {

    @BeforeTest
    public void setUp() throws InterruptedException {
        Testbase base = new Testbase();
        base.initialize();
        driver = getDriver();
        driver.get("https://demoqa.com/modal-dialogs");
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test
    public void verifySmallModalText() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,1000)");
        boolean smallModal = driver.findElement(By.xpath("//button[@id=\"showSmallModal\"]")).isDisplayed();
        if(smallModal){
            driver.findElement(By.xpath("//button[@id=\"showSmallModal\"]")).click();
            Thread.sleep(Constants.SHORT_WAIT);
            String small_text = driver.findElement(By.xpath("//div[text()=\"This is a small modal. It has very less content\"]")).getText();
            System.out.println(small_text);
            String expected_Text = "This is a small modal. It has very less content";
            Assert.assertEquals(small_text,expected_Text);
            driver.findElement(By.xpath("//button[@id=\"closeSmallModal\"]")).click();
            Thread.sleep(Constants.SHORT_WAIT);

        }
    }

    @Test
    public void verifyLargeModalText() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id=\"showLargeModal\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
        String large_text = driver.findElement(By.xpath("//p[text()=\"Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text " +
                "ever since the 1500s, when an unknown printer " +
                "took a galley of type and scrambled it to " +
                "make a type specimen book. It has survived not only five centuries, " +
                "but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the " +
                "release of Letraset sheets containing Lorem Ipsum passages, and " +
                "more recently with desktop publishing software like Aldus PageMaker " +
                "including versions of Lorem Ipsum.\"]")).getText();
        System.out.println(large_text);
        driver.findElement(By.xpath("//button[@id=\"closeLargeModal\"]")).click();
    }
    @AfterTest
    public void testDown(){
        driver.quit();
    }

}
