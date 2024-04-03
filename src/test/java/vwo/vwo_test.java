package vwo;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
public class vwo_test {
    @Test
    @Description("verify the login page of VWO")
    public void testVWOlogin() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://app.vwo.com/");

        //print all anchor tags on vwo.com
        //a tags and print the get text

        List<WebElement> all_tag = driver.findElements(By.tagName("a"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Size: "+all_tag.size());
        for(int i=0;i<all_tag.size();i++){
            String tag = all_tag.get(i).getText();
            System.out.println(i+ "  " +tag);
        }
        driver.quit();
    }
}

