package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.swing.*;
import java.util.List;

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
        Select selectCountry = new Select(listCountry);
        List<WebElement> specificCountry = selectCountry.getOptions();
        boolean japanFound = false;
        for (WebElement country : specificCountry) {
            if (country.getText().equals("Japan")) {
                country.click();
                japanFound = true;
                break;
            }
        }

        if (!japanFound) {
            System.out.println("Japan not found in the country dropdown");
            Assert.fail("Japan was not found in the country dropdown");
        }


        CommonMethods.waitForElementClickable(By.xpath("//input[@id=\"datepicker\"]")).click();



    String staticMonth = "April";
    int staticYear = 2002;


    // Assuming staticYear and staticMonth are set previously:
    String month = CommonMethods.waitForElementClickable(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[1]")).getText();
    int year = Integer.parseInt(driver.findElement(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[2]")).getText());

    while(true)

    {

        // If the current year is less than the static year (2025), click "Next" to go forward
        if (year < staticYear && !month.equals(staticMonth)) {
            CommonMethods.waitForElementClickable(By.xpath("//span[text()=\"Next\"]")).click();
        }
        // If the current year is greater than the static year (2025), click "Prev" to go back
        else if (year > staticYear || (year == staticYear && !month.equals(staticMonth))) {
            CommonMethods.waitForElementClickable(By.xpath("//span[text()=\"Prev\"]")).click();
        }

        // Re-fetch the current month and year after clicking a button to update the date picker
        month = CommonMethods.waitForElementClickable(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[1]")).getText();
        year = Integer.parseInt(driver.findElement(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[2]")).getText());

        // If we've reached the correct month and year, exit the loop
        if (year == staticYear && month.equals(staticMonth)) {
            break;
        }
        System.out.println("Reached the target month: " + month + " and year: " + year);

        Assert.assertEquals(staticYear, year);
        Assert.assertEquals(staticMonth, month);

        CommonMethods.waitForElementClickable(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td/a[text()=\"4\"]")).click();
    }
}


    public void getMyLatest() {


        CommonMethods.waitForElementClickable(By.xpath("//input[@id=\"txtDate\"]")).click();
        WebElement months = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]"));
        Select sop = new Select(months);
        List<WebElement> allmonths = sop.getOptions();
        boolean mymonth = false;
        for (WebElement my : allmonths) {
            System.out.println(my.getText());
            if (my.getText().equals("Apr")) {
                my.click();
                mymonth = true;
                break;
            }
        }
        if(!mymonth){
            Assert.fail();
        }
        WebElement year = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]"));
        Select myyear = new Select(year);
        List<WebElement> allYear = myyear.getOptions();
        Boolean allyear = false;
        for(WebElement theYear : allYear){
            if (theYear.getText().equals("2027")){
                theYear.click();
                allyear =true;
                break;
            }
        }
        if (!allyear){
            Assert.fail();
        }
    }
    public void uploadFiles(){
        CommonMethods method = new CommonMethods();
        method
        String file = "src/test/java/Resources/TISH.xlsx";
        driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys(file);

    }
}









