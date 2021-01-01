package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    public static long TIMEOUT_SECONDS = 60;


    public static void Second() throws InterruptedException {
        //return Milliseconds(1000);
        Thread.sleep(1000);
    }

    public static Boolean Milliseconds(long waitMilliseconds) {
        try {
            Thread.sleep(waitMilliseconds);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }

    // Waits for element to load properly and returns whether it loaded successfully
    public static Boolean waitForElement(WebDriver driver, WebElement webElement, int timeout) {
        if (driver == null || webElement == null) {
            return false;
        }
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return true;
    }


    // Finds element by xpath, waits for it to load properly, and returns it
    public static WebElement ForXPath(WebDriver driver, By xpath) {

        WebElement webElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(xpath));
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        //TODO maybe delete line below
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }
}
