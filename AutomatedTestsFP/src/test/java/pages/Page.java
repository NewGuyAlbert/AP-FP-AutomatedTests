package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Page {

    WebDriver driver;

    By logoutButton = By.xpath("//a[contains(@id,'logoutButton')]");

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public String navigateToPage(String URL) {
        driver.get(URL);

        return driver.getTitle();
    }

    public boolean ifTxtOnPage(String text) {
        if(driver.getPageSource().contains(text))
        {
            return true;
        }
        return false;
    }


    public void scroll(int amount) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + amount + ")");
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);" + "window.scrollBy(0,-100);", element);
    }

    public void clickLogout() throws InterruptedException {
        WebElement webElement = Waits.ForXPath(driver, logoutButton);
        Assert.assertNotNull(webElement, "No logout button");
        webElement.click();
        Waits.Second();
    }
}
