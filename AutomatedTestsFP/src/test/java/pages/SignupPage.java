package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignupPage extends Page {

    private WebDriver driver;
    public String URL;

    By signupButtonNavbar = By.xpath("//a[contains(@id,'signupButton')]");
    By signupButton = By.xpath("//button[contains(@type,'submit')]");
    By usernameField = By.xpath("//input[contains(@id,'username')]");
    By emailField = By.xpath("//input[contains(@id,'email')]");
    By passwordField = By.xpath("//input[contains(@id,'password')]");
    By passwordRepeatField = By.xpath("//input[contains(@id,'passwordRepeat')]");

    public SignupPage(WebDriver driver, String url){
        super(driver);
        this.driver = driver;
        this.URL = url;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return this.URL;
    }

    //Click on signup button from the navbar
    public void clickSignupNavbar() throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, signupButtonNavbar);
        Assert.assertNotNull(webElement, "No signup button in the navbar");
        webElement.click();
        Waits.Second();
    }

    //Set username
    public void setUsername(String strUsername) throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, usernameField);
        Assert.assertNotNull(webElement, "No input field for username");
        webElement.sendKeys(strUsername);
        Waits.Second();
    }

    //Set email
    public void setEmail(String email) throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, emailField);
        Assert.assertNotNull(webElement, "No input field for email");
        webElement.sendKeys(email);
        Waits.Second();
    }

    //Set password
    public void setPassword(String password) throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, passwordField);
        Assert.assertNotNull(webElement, "No input field for password");
        webElement.sendKeys(password);
        Waits.Second();
    }

    //Set password repeat
    public void setPasswordRepeat(String passwordRepeat) throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, passwordRepeatField);
        Assert.assertNotNull(webElement, "No input field for repeat password");
        webElement.sendKeys(passwordRepeat);
        Waits.Second();
    }

    //Click on signup
    public void clickSignup() throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, signupButton);
        Assert.assertNotNull(webElement, "No signup button");
        webElement.click();
        Waits.Second();
    }
}
