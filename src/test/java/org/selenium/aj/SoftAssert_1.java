package org.selenium.aj;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssert_1 {
    @Test
    public void test12(){
        SoftAssert sofa = new SoftAssert();
        sofa.assertEquals(true,false,"Hello AJ");
        System.out.println("Will I print?");
        sofa.assertAll();
    }
}
