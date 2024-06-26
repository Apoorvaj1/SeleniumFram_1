package KatalonDemoProject;

import Constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.selenium.base.Testbase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

@Listeners(MyListeners.MyITestListeners.class)

public class Test_1 extends Testbase {
    public static WebDriver driver;
    @BeforeTest
    public void setUp() throws InterruptedException {
        Testbase base = new Testbase();
        base.initialize();
        driver = getDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test
    public void verifyTitle(){
        String actual_title = driver.getTitle();
        Assert.assertEquals(actual_title, Constants.HOME_PAGE_TITLE);
        System.out.println("Title is "+actual_title);
    }
    @Test
    public void verifyHomeURL(){
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,Constants.HOME_PAGE_URL);
        System.out.println("URL is "+actualURL);
    }
    @Test
    public void verifyText(){
        Boolean text_1 = driver.findElement(By.xpath("//div[@class=\"text-vertical-center\"]/h1")).isDisplayed();
        System.out.println(text_1);
        Boolean text_2 = driver.findElement(By.xpath("//div[@class=\"text-vertical-center\"]/h3")).isDisplayed();
        System.out.println(text_2);
    }
    @Test
    public void verifyMakeAppointmentButton(){
        boolean verify_Button = driver.findElement(By.cssSelector("a[id$=\"-appointment\"]")).isDisplayed();
        if(verify_Button){
            System.out.println("Yeah! Make Appointment Button is available");
        }
        else{
            System.out.println("Ohh! Button is not available");
        }
    }
    @Test(dependsOnMethods = {"verifyMakeAppointmentButton"})
    public void enterCredentials() throws InterruptedException {
        driver.findElement(By.cssSelector("a[id$=\"-appointment\"]")).click();
        WebElement username = driver.findElement(By.xpath("//input[@id=\"txt-username\"]"));
        username.sendKeys(Constants.USERNAME);
        WebElement password = driver.findElement(By.xpath("//input[@id=\"txt-password\"]"));
        password.sendKeys(Constants.PASSWORD);
        driver.findElement(By.cssSelector("button[class$=\"btn-default\"]")).click();
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test(dependsOnMethods = {"enterCredentials"})
    public void verifyAppointmentURL(){
        String AppointmentURL = driver.getCurrentUrl();
        System.out.println("Appointment screen URL is "+AppointmentURL);
    }
    @Test(dependsOnMethods = {"verifyAppointmentURL"})
    public void makeAppointment() throws InterruptedException {
        Select select = new Select(driver.findElement(By.xpath("//select[@id=\"combo_facility\"]")));
        List<WebElement> Facility_list = select.getOptions();
        for(WebElement list:Facility_list){
            System.out.println(list.getText());

        }
        select.selectByIndex(2);
        boolean checkbox = driver.findElement(By.xpath("//input[@id=\"chk_hospotal_readmission\"]")).isSelected();
        if(!checkbox){
            driver.findElement(By.xpath("//input[@id=\"chk_hospotal_readmission\"]")).click();
        }
        List<WebElement> radiobutton_Healthcare = driver.findElements(By.xpath("//label[@class=\"radio-inline\"]"));
        for(WebElement radiobutton:radiobutton_Healthcare){
            System.out.println(radiobutton.getText());
            }
        WebElement medicaid = driver.findElement(By.xpath("//input[@id=\"radio_program_medicaid\"]"));
        if(!medicaid.isSelected()){
            medicaid.click();
        }

        driver.findElement(By.cssSelector("span[class$=\"glyphicon-calendar\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"datepicker-days\"]/table/tbody/tr[3]/td[4]")).click();
        driver.findElement(By.xpath("//textarea[@id=\"txt_comment\"]")).sendKeys("Hello Automation 123");

        boolean button_appointment = driver.findElement(By.xpath("//button[contains(text(),\"Book Appointment\")]")).isDisplayed();
        if(button_appointment){
            driver.findElement(By.xpath("//button[contains(text(),\"Book Appointment\")]")).click();
        }
        System.out.println("Facility dropdown size is "+Facility_list.size());
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test (dependsOnMethods = {"makeAppointment"})
    public void verifyAppointmentConfirmation(){
        boolean appointment_Confirmation = driver.findElement(By.xpath("//div[@class=\"col-xs-12 text-center\"]/h2")).isDisplayed();
        System.out.println("Booking Confirmation is "+appointment_Confirmation);
        Assert.assertTrue(appointment_Confirmation);

        List<WebElement> details = driver.findElements(By.cssSelector("div[class=\"col-xs-offset-2 col-xs-8\"]"));
        for(WebElement details_1:details){
            System.out.println(details_1.getText());
        }
    }
    @Test (dependsOnMethods = {"verifyAppointmentConfirmation"})
    public void goToHomePage() throws InterruptedException {
        driver.findElement(By.linkText("Go to Homepage")).click();
        Thread.sleep(Constants.SHORT_WAIT);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
