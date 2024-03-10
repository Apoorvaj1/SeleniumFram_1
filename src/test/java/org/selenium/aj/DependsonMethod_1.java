package org.selenium.aj;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependsonMethod_1 {

    @Test
    public void test_3(){
        System.out.println("Hello AJ");
    }
    @Test
    public void test_1(){
        Assert.assertEquals(true,true);
    }
    @Test(dependsOnMethods = "test_1")
    public void test_2(){
        System.out.println("Apoorv");
    }
}
