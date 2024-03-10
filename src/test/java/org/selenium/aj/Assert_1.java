package org.selenium.aj;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assert_1 {
    @Test
    public void test(){
        Assert.assertEquals(true,true,"Failed");
        System.out.println("Hi Apoorv");
    }
}
