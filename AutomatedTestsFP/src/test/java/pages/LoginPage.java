package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends Page {

    private WebDriver driver;
    public String URL;


    By loginButtonNavbar = By.xpath("//a[contains(@id,'loginButton')]");
    By loginButton = By.xpath("//button[contains(@type,'submit')]");
    By usernameField = By.xpath("//input[contains(@id,'username')]");
    By passField = By.xpath("//input[contains(@id,'password')]");

    public LoginPage(WebDriver driver, String url) {
        super(driver);
        this.driver = driver;
        this.URL = url;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return this.URL;
    }

    //Set username
    public void setUsername(String strUsername) throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, usernameField);
        Assert.assertNotNull(webElement, "No input field for username");
        webElement.sendKeys(strUsername);
        Waits.Second();
    }

    //Set user password
    public void setUserPass(String strUserPass) throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, passField);
        Assert.assertNotNull(webElement, "No input field for pass");
        webElement.sendKeys(strUserPass);
        Waits.Second();
    }

    //Click on login button
    public void clickLogin() throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, loginButton);
        Assert.assertNotNull(webElement, "No login button");
        webElement.click();
        Waits.Second();
    }

    //Click on login button from the navbar
    public void clickLoginNavbar() throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, loginButtonNavbar);
        Assert.assertNotNull(webElement, "No login button in the navbar");
        webElement.click();
        Waits.Second();
    }
}
