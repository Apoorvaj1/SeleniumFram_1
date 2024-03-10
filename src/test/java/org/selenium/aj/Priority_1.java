package org.selenium.aj;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Priority_1{
    @BeforeTest
    public void testLogin(){
        System.out.println("Login");
    }
    @AfterTest
    public void testCart(){
        System.out.println("Cart");
    }
    @Test(priority = 3)
    public void testHome(){
        System.out.println("Home");
    }
}
