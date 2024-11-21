package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GUI  extends BaseClass {

    public static void gui(){
        CommonMethods.waitForElementClickable(GUILocators.name).sendKeys("Atish");
        CommonMethods.waitForElementClickable(GUILocators.email).sendKeys("atishraj238@gmail.com");
        CommonMethods.waitForElementClickable(GUILocators.phone).sendKeys("9877936415");
        CommonMethods.waitForElementClickable(GUILocators.address).sendKeys("Tester");
        CommonMethods.waitForElementClickable(GUILocators.male).click();
        CommonMethods.waitForElementClickable(GUILocators.selectingDays).click();
        Select s= new Select(CommonMethods.waitForElementClickable(GUILocators.country));
        for (WebElement nameOfCountry : s){
            System.out.println(nameOfCountry.getText());

        }

    }
}
