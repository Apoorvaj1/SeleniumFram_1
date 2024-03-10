package org.selenium.aj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Parameter_1 {
    @Test
    @Parameters({"browser"})
    public void test_usingParameter(String brow){
        System.out.println(brow);

        if(brow.equals("edge")){
            WebDriver driver = new EdgeDriver();
            driver.get("https://google.com");
        }
    }
}
