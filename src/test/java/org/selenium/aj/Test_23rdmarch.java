package org.selenium.aj;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class Test_23rdmarch {
    EdgeDriver driver;
    @Description("This is setup")
    @BeforeTest
    public void setUp(){
        EdgeOptions option = new EdgeOptions();
        option.setPageLoadStrategy(PageLoadStrategy.EAGER);
        option.addArguments("--incognito");
        option.addArguments("--guest");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void firstCase() throws InterruptedException {
        driver.get("https://www.ebay.com/b/Apple-Cell-Phones-Smartphones/9355/bn_319682");
        List<WebElement> listofspace = driver.findElements(By.cssSelector("a[class$=\"b-guidancecard__link--noimg\"]"));
        /*for(WebElement space:listofspace){
            System.out.println(space.getText());
            if(space.getText().equals("128 GB")){

            }
        }*/
        System.out.println("Size is "+listofspace.size());
        for(int i=0;i<listofspace.size();i++){
            String space = listofspace.get(i).getText();
            if(space.contains("128 GB")){
                listofspace.get(i).click();
                break;
            }
        }
        List<WebElement> abc = driver.findElements(By.cssSelector("h3[class=\"s-item__title\"]"));
        System.out.println("Size of abc "+abc.size());
        for(int i=0;i<abc.size();i++){
            String abc1 = abc.get(i).getText();
            System.out.println(abc1);
            if(abc1.contains("Apple iPhone X XR XS XS Max 64GB 128GB 256GB - Unlocked Verizon AT&T T-Mobile")){
                abc.get(i).click();
                break;
            }
        }
        Thread.sleep(3000);
    }
    @Test
    public void secondCase() throws InterruptedException {
        driver.get("https://www.google.com");
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(3000);
    }

    @Test
    public void thirdCase() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkbox = driver.findElements(By.xpath("//input[@type=\"checkbox\"]"));
        System.out.println("Checkbox size "+checkbox.size());
        for(int i=0;i<checkbox.size();i++){
            String checkbox_name = checkbox.get(i).getText();
            System.out.println(checkbox_name);
            }
        Thread.sleep(3000);
    }
    @Description("This is teardown")
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
