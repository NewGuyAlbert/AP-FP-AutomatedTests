package tests;

import data.JsonRW;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignupPage;

import java.io.FileReader;

public class SignupTest extends TestBase{

    private SignupPage signupPage;

    private String newUsername;
    private String email = "nodemailernodemand@gmail.com";
    private String pass = "password";
    private String passRepeat = "password";

    @Test(priority = 0)
    public void getNewUsername() throws NumberFormatException  {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(super.signup_file));

            JSONObject jsonObject = (JSONObject) obj;

            String accountNumber =  String.valueOf(jsonObject.get("accountnumber"));

            newUsername = "AutomatedTest"+accountNumber;
            int result = Integer.parseInt(accountNumber) + 1;

            JsonRW jsonObj = new JsonRW(super.signup_file);
            jsonObj.writeJSON("accountnumber", String.valueOf(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Signup
     */
    @Test(priority = 1)
    public void Signup() throws InterruptedException {
        signupPage = new SignupPage(driver, super.DOMAIN);
        String actualResult = signupPage.navigateToPage(signupPage.getUrl());
        Assert.assertNotNull(actualResult);

        Thread.sleep(1000);
        signupPage.clickSignupNavbar();

        Thread.sleep(1000);
        signupPage.setUsername(newUsername);
        signupPage.setEmail(email);
        signupPage.setPassword(pass);
        signupPage.setPasswordRepeat(passRepeat);
        signupPage.clickSignup();

        //Assert if we landed on account page
        Assert.assertEquals(driver.getCurrentUrl().contains("/account"), true);

        Thread.sleep(2000);
        signupPage.clickLogout();

    }
}
