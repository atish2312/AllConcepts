package Helpers;

import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonMethods  extends BaseClass {


    public static WebElement waitForElementClickable(By locator ){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static WebElement waitForElementClickable1(WebElement locator ){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }
    public static WebElement waitLoadTest(WebElement locator) {
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }
    public static WebElement waitLoadTest1(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static  String  myPath(String path){
       String rootDir =  System.getProperty("user.dir");
        return Paths.get(rootDir,path).toString();

    }



}
