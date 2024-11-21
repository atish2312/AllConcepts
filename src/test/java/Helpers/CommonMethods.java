package Helpers;

import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonMethods  extends BaseClass {

    public static WebElement waitForElementClickable(By locator ){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static WebElement waitForElementClickable(WebElement locator ){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
