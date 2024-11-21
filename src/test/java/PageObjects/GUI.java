package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class GUI  extends BaseClass {

    public void gui() {
        CommonMethods.waitForElementClickable(GUILocators.name).sendKeys("Atish");
        CommonMethods.waitForElementClickable(GUILocators.email).sendKeys("atishraj238@gmail.com");
        CommonMethods.waitForElementClickable(GUILocators.phone).sendKeys("9877936415");
        CommonMethods.waitForElementClickable(GUILocators.address).sendKeys("Tester");
        CommonMethods.waitForElementClickable(GUILocators.male).click();
        CommonMethods.waitForElementClickable(GUILocators.selectingDays).click();
        WebElement listCountry = CommonMethods.waitForElementClickable(GUILocators.country);
        Select s = new Select(listCountry);
        List<WebElement> specificCountry = s.getOptions();
        boolean japanFound = false;
        for (WebElement nameOfCountry : specificCountry) {
            String selectingCountry = nameOfCountry.getText();
            System.out.println(selectingCountry);
            if (selectingCountry.equals("Japan")) {
                nameOfCountry.click();
                japanFound = true;
                break;

            }
        }

        if (!japanFound) {
            System.out.println("Japan not found in the country dropdown");
            Assert.fail("Japan was not found in the country dropdown");
        }
    }
}

