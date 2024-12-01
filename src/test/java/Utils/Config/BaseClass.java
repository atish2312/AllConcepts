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


public class BaseClass {
    public  static WebDriver driver;
    public static WebDriverWait wait;
    public static PageFactory pageFactory;

    @BeforeClass
    public void  initializeBrowser(){
<<<<<<< HEAD
        String downloadPath = CommonMethods.myPath("/src/test/java/DownloadPath");
        String path = "C:\\TotalConcepts\\SeleniumPractice\\src\\test\\java\\DownloadPath";
=======
        String downloadPath = CommonMethods.myPath("/src/test/java/DownloadFolder");
        String myDownload = "E:\\My Daily Task\\AllConcepts\\src\\test\\java\\DownloadFolder";
>>>>>>> c8924ef06ba1036819b926ac6464a26f9b1d8745


        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        System.out.println(downloadPath);
        HashMap<String ,Object> chromeDownload = new HashMap<>();
        chromeDownload.put("download.default_directory",path);
        options.setExperimentalOption("prefs",chromeDownload);
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
