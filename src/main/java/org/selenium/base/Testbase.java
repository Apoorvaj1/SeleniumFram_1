package org.selenium.base;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Testbase {
    public static WebDriver driver;
    public void initialize(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--guest");
        options.addArguments("--incognito");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }
    public static WebDriver getDriver(){
        return driver;
    }


}
