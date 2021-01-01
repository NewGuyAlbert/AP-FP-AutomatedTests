package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    public String DOMAIN = System.getProperty("exec.domain");

    public String enviroment;
    public String signup_file="newUsernameIndex.json";



    /**
     * BeforeClass Test annotation will run the function to setup all the needed
     * dependencies that need to be parsed to the test pages
     * Loads the domain and the store product to test if needed
     * @throws IOException
     */
    @BeforeClass
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup(); // Maven dependancy

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("safebrowsing.enabled", "true");
        String downloadFilepath = "C:\\Downloads";
        chromePrefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--safebrowsing-disable-extension-blacklist");
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("--allow-unchecked-dangerous-downloads[3]");



        driver = new ChromeDriver(options); // Create a Chrome driver. All test and page classes use this driver.
        wait = new WebDriverWait(driver, 5); // Create a wait. All test and page classes use this wait.

        // Maximize Window
        driver.manage().window().maximize();

        if (this.DOMAIN.contains("staging")) {
            this.enviroment = "staging";
        }
        else {
            this.enviroment = "production";
        }
    }


    /**
     * Always quit the driver instance when done
     */
    @AfterClass
    public void quit() {
        driver.quit();
    }

}

