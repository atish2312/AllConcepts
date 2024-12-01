package Utils.Config;


import Helpers.CommonMethods;
import Helpers.FetchProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

public class BaseClass {
    public  static WebDriver driver;
    public static WebDriverWait wait;
    public static PageFactory pageFactory;

    @BeforeClass
    public void  initializeBrowser(){
        String downloadPath = CommonMethods.myPath("/src/test/java/DownloadPath");
        String path = "C:\\TotalConcepts\\SeleniumPractice\\src\\test\\java\\DownloadPath";


        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        HashMap<String ,Object> chromeDownload = new HashMap<>();
        chromeDownload.put("download.default_directory",path);
        options.setExperimentalOption("prefs",chromeDownload);
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        pageFactory = new PageFactory();
        driver.get(FetchProperties.getEnvironmentVariablePath("BaseURl"));
    }
    @AfterClass
    public void logout(){
        driver.quit();
    }
}
