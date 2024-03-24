package org.selenium.aj;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Collections;

public class EdgeOption {
    public static void main(String[] args) {
        EdgeOptions options = new EdgeOptions();
        //options.addArguments("--headless");  //headless means web browser without user interface.
                                               // Increases efficiency of testing web applications
        options.addArguments("--incognito");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        //options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        EdgeDriver driver = new EdgeDriver(options);
        driver.get("https://www.google.com");
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//button[@jsname=\"ZUkOIc\"]")).click();
        driver.switchTo().defaultContent();
        //driver.findElement(By.className("gLFyf")).sendKeys("Iphone15", Keys.ENTER);
        WebElement ab = driver.findElement(By.className("gLFyf"));
        ab.sendKeys("Iphone 15");
        driver.findElement(By.xpath("//input[@value=\"Google Search\"]")).click();
        System.out.println("Hello");
        driver.quit();

    }
}
