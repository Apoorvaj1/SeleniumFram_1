package org.selenium.aj;

import org.testng.annotations.Test;

public class Group_1 {
    @Test (groups = {"sanity"}, enabled = false)
    public void testABC(){
        System.out.println("I am testABC");
    }
    @Test (groups = {"sanity"})
    public void testAJ(){
        System.out.println("I am testAJ");
    }
    @Test (groups = {"sanity"})
    public void testDJ(){
        System.out.println("I am testDJ");
    }
}
