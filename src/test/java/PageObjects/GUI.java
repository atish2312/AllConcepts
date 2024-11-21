package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import java.util.List;

public class GUI  extends BaseClass {

<<<<<<< HEAD
    public  void gui(){
=======
    public void gui() {
>>>>>>> a27d23f30ea60516cfbe515cc4b0f98548d08a9c
        CommonMethods.waitForElementClickable(GUILocators.name).sendKeys("Atish");
        CommonMethods.waitForElementClickable(GUILocators.email).sendKeys("atishraj238@gmail.com");
        CommonMethods.waitForElementClickable(GUILocators.phone).sendKeys("9877936415");
        CommonMethods.waitForElementClickable(GUILocators.address).sendKeys("Tester");
        CommonMethods.waitForElementClickable(GUILocators.male).click();
        CommonMethods.waitForElementClickable(GUILocators.selectingDays).click();
<<<<<<< HEAD
        Select s= new Select(CommonMethods.waitForElementClickable(GUILocators.country));
        List<WebElement> countries = s.getOptions();
        for (WebElement nameOfCountry : countries){
            System.out.println(nameOfCountry.getText());
=======
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
>>>>>>> a27d23f30ea60516cfbe515cc4b0f98548d08a9c

            }
        }

        if (!japanFound) {
            System.out.println("Japan not found in the country dropdown");
            Assert.fail("Japan was not found in the country dropdown");
        }
    }
}

