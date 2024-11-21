package Utils.Config;


import Helpers.FetchProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {
    public  static WebDriver driver;
    public static WebDriverWait wait;
    public static PageFactory pageFactory;

    @BeforeClass
    public void  initializeBrowser(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        pageFactory = new PageFactory();
        driver.get(FetchProperties.getEnvironmentVariablePath("BaseURl"));
    }
    @AfterClass
    public void logout(){
        driver.quit();
    }
}
