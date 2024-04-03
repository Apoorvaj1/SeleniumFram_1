package OrangeHRMProject;

import Constants.Constants;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.selenium.base.Testbase;
import org.testng.annotations.*;

public class OrangeTest extends Testbase {

    Faker faker = new Faker();
    String enteredFirstName;
    String enteredLastName;

    @DataProvider(name = "loginTest")
    public Object[][] getData(){
        return new Object[][]{
                {"admin123","admin123"},
                {"Admin","admin123456"},
                {"Admin","admin123"}
        };
    }
    @BeforeTest
    public void setUp() throws InterruptedException {
        Testbase base = new Testbase();
        base.initialize();
        driver = getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(Constants.SHORT_WAIT);
    }
    @Test
    public void loginValidationTest() throws InterruptedException {
        WebElement loginButton = driver.findElement(By.cssSelector("button[class$=\"orangehrm-login-button\"]"));
        loginButton.click();
        WebElement username = driver.findElement(By.cssSelector("input[class$=\"oxd-input--error\"][name=\"username\"]"));
        String usernameValidation = driver.findElement(RelativeLocator.with(By.tagName("span")).below(username)).getText();
        System.out.println(usernameValidation);
        WebElement password = driver.findElement(By.cssSelector("input[class*=\"oxd-input\"][type=\"password\"]"));
        String passwordValidation = driver.findElement(RelativeLocator.with(By.tagName("span")).below(password)).getText();
        System.out.println(passwordValidation);
        Thread.sleep(Constants.SHORT_WAIT);
        if(driver.getPageSource().contains("Login")){
            System.out.println("Yeah Login text is present");
        }
    }
    @Test(dependsOnMethods = "loginValidationTest", dataProvider = "loginTest")
    public void enterUsernameandPassword(String username1,String password1) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(Constants.SHORT_WAIT);
        WebElement username = driver.findElement(By.cssSelector("input[class$=\"oxd-input--focus\"][name=\"username\"]"));
        username.sendKeys(username1);
        WebElement password = driver.findElement(By.cssSelector("input[class*=\"oxd-input\"][type=\"password\"]"));
        password.sendKeys(password1);
        WebElement loginButton = driver.findElement(By.cssSelector("button[class$=\"orangehrm-login-button\"]"));
        loginButton.click();
        Thread.sleep(Constants.MEDIUM_WAIT);

    }
    @Test (dependsOnMethods = "enterUsernameandPassword")
    public void addEmployee() throws InterruptedException {
        WebElement PIM = driver.findElement(By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[2]"));
        PIM.click();
        Thread.sleep(Constants.SHORT_WAIT);
       WebElement addEmployee = driver.findElement(By.linkText("Add Employee"));
       addEmployee.click();
       Thread.sleep(Constants.SHORT_WAIT);
       enteredFirstName = faker.name().firstName();
       WebElement employeeFullName = driver.findElement(By.cssSelector("input.oxd-input.oxd-input--active.orangehrm-firstname"));
       employeeFullName.sendKeys(enteredFirstName);
       enteredLastName = faker.name().lastName();
       WebElement employeeLastName = driver.findElement(By.cssSelector("input.oxd-input.oxd-input--active.orangehrm-lastname"));
       employeeLastName.sendKeys(enteredLastName);
       WebElement saveButton = driver.findElement(By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space"));
       if(saveButton.isDisplayed()){
           saveButton.click();
       }
       System.out.println("------------Entered details are-------------");
       System.out.println(enteredFirstName);
       System.out.println(enteredLastName);
       Thread.sleep(Constants.SHORT_WAIT);
    }

    @Test(dependsOnMethods = "enterUsernameandPassword")
    public void verifyEmployeeData() throws InterruptedException {
        driver.findElement(By.linkText("Employee List")).click();
        Thread.sleep(Constants.MEDIUM_WAIT);
        String name12 = enteredFirstName+" "+enteredLastName;
        if(driver.getPageSource().contains("Employee Information")){
            boolean visible = driver.findElement(By.xpath("(//div[@class=\"oxd-autocomplete-text-input oxd-autocomplete-text-input--active\"])[1]")).isDisplayed();
            System.out.println(visible);
            Thread.sleep(Constants.SHORT_WAIT);
            driver.findElement(By.xpath("(//input[@placeholder=\"Type for hints...\"])[1]")).sendKeys(name12);
            driver.findElement(By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")).click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0,1000)");
            Thread.sleep(Constants.MEDIUM_WAIT);
            WebElement Id = driver.findElement(By.xpath("(//div[@class=\"oxd-table-cell oxd-padding-cell\"])[2]"));
            String name = driver.findElement(RelativeLocator.with(By.tagName("div")).toRightOf(Id)).getText();
            System.out.println("Name is "+name);
            if(name.equals(enteredFirstName)){
                driver.findElement(By.xpath("(//span[@class=\"oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input\"])[2]")).click();
                Thread.sleep(Constants.SHORT_WAIT);
                driver.findElement(By.xpath("(//button[@class=\"oxd-icon-button oxd-table-cell-action-space\"])[1]")).click();
                Thread.sleep(Constants.SHORT_WAIT);
                driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\"]")).click();
            }
        }
        Thread.sleep(Constants.LONG_WAIT);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
