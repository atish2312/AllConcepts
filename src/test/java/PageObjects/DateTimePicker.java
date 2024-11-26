package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DateTimePicker extends BaseClass {

    public void dateTime() throws InterruptedException {

        CommonMethods.waitForElementClickable(GUILocators.dateTime).click();

        WebElement next = CommonMethods.waitForElementClickable(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']"));
        WebElement May = CommonMethods.waitForElementClickable(By.xpath("//span[text()='May']"));
        WebElement year = CommonMethods.waitForElementClickable(By.xpath("//span[text()='2024']"));

        boolean isMayDisplayed = false;
        boolean isYearDisplayed = false;

        do {
            Thread.sleep((2000));
            next.click(); // Click the "Next" button
            isMayDisplayed = May.isDisplayed(); // Check if "May" is displayed
            isYearDisplayed = year.isDisplayed(); // Check if "2024" is displayed
        } while (!(isMayDisplayed && isYearDisplayed)); // Continue until both are displayed






    }
}
