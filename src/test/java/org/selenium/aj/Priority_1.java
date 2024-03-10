package org.selenium.aj;

import org.testng.annotations.Test;

public class Priority_1{
    @Test(priority = 1)
    public void testLogin(){
        System.out.println("Login");
    }
    @Test(priority = 2)
    public void testCart(){
        System.out.println("Cart");
    }
    @Test(priority = 3)
    public void testHome(){
        System.out.println("Home");
    }
}
