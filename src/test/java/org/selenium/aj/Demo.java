package org.selenium.aj;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Demo {
    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                new Object[]{"admin","admin123"},
                new Object[]{"admin","admin1234"},
                new Object[]{"admin","admin12356"}
        };
    }



    @Test (dataProvider = "getData")
    public void userLogin(String username, String password) throws InterruptedException {
        EdgeOptions option = new EdgeOptions();
        option.setPageLoadStrategy(PageLoadStrategy.EAGER);
        option.addArguments("--disable-notifications");
        WebDriver driver = new EdgeDriver(option);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("input#email")).sendKeys(username);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        Thread.sleep(2000);
        driver.quit();
        System.out.println(username);
        System.out.println(password);

    }

}

