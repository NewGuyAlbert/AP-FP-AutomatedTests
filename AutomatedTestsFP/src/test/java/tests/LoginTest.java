package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends TestBase{

    private LoginPage loginPage;

    private String username = "albert";
    private String pass = "password";

    /**
     * Login
     */
    @Test(priority = 0)
    public void Login() throws InterruptedException {
        loginPage = new LoginPage(driver, super.DOMAIN);
        String actualResult = loginPage.navigateToPage(loginPage.getUrl());
        Assert.assertNotNull(actualResult);

        Thread.sleep(1000);
        loginPage.clickLoginNavbar();

        Thread.sleep(1000);
        loginPage.setUsername(username);
        loginPage.setUserPass(pass);
        loginPage.clickLogin();

        //Assert if we landed on account page
        Assert.assertEquals(driver.getCurrentUrl().contains("/account"), true);

        Thread.sleep(2000);
        loginPage.clickLogout();

    }
}
