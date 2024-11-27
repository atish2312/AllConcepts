package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;


public class GUI extends BaseClass {


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



        String month = CommonMethods.waitForElementClickable(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[1]")).getText();
        int year = Integer.parseInt(driver.findElement(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[2]")).getText());

        while (true) {


            if (year < staticYear ||(year == staticYear &&  !month.equals(staticMonth))) {
                CommonMethods.waitForElementClickable(By.xpath("//span[text()=\"Next\"]")).click();
            }

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
        if (!mymonth) {
            Assert.fail();
        }
        WebElement year = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]"));
        Select myyear = new Select(year);
        List<WebElement> allYear = myyear.getOptions();
        Boolean allyear = false;
        for (WebElement theYear : allYear) {
            if (theYear.getText().equals("2027")) {
                theYear.click();
                allyear = true;
                break;
            }
        }
        if (!allyear) {
            Assert.fail();
        }
    }

    public void uploadFiles() {

        String file = "/src/test/java/Resources/TISH.xlsx";
        String mynewpath = CommonMethods.myPath(file);
        System.out.println(mynewpath);

        driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys(mynewpath);


    }

    public void uploadMultipleFile() {
        String[] file = {"/src/test/java/Resources/TISH.xlsx", "/src/test/java/Resources/file-sample_150kB.pdf"};
        for (String files : file) {
            String fullpath = CommonMethods.myPath(files);
            driver.findElement(By.xpath("//*[@id=\"multipleFilesInput\"]")).sendKeys(fullpath);
        }

    }

    public void staticWebTable() {
        String myData = "Learn Java Mukesh Java 500";
        List<WebElement> rows = driver.findElements(By.xpath("//table[@name=\"BookTable\"]/tbody/tr"));
        String name = null;
        for (WebElement row : rows) {
            String rowText = row.getText();
            if (rowText.contains("Learn Java")) {
                System.out.println(rowText);
                name = rowText;
                break;

            }
        }
        Assert.assertEquals(myData, name);
        Assert.assertNotNull(name);
    }

    public void dynamicTable() {
        WebElement CPULOAD = driver.findElement(By.xpath("//div[@class=\"display-values\"]/p/strong"));
        WebElement FireFox = driver.findElement(By.xpath("//div[@class=\"display-values\"]/p[2]/strong"));
        String myCPULoad = CPULOAD.getText();
        String MyFireFox = FireFox.getText();
        String CPULoad = null;
        List<WebElement> alltableData = driver.findElements(By.xpath("//table[@id=\"taskTable\"]/thead/tr/th"));
        for (WebElement Mytable : alltableData) {
            String head = Mytable.getText();
            if (head.contains("Name")) {
                List<WebElement> namerows = driver.findElements(By.xpath("//table[@id=\"taskTable\"]/tbody/tr/td[1]"));
                for (WebElement myName : namerows) {
                    String getName = myName.getText();
                    if (getName.contains("Chrome")) {
                        List<WebElement> cell = driver.findElements(By.xpath("//table[@id=\"taskTable\"]/tbody/tr//td"));
                        for (WebElement mycell : cell) {
                            String cellData = mycell.getText();
                            if (cellData.contains(myCPULoad)) {
                                CPULoad = cellData;
                            }
                        }
                    }

                }
            }
        }
        System.out.println(myCPULoad);
        Assert.assertEquals(myCPULoad, CPULoad);
    }

    public void paginationWebTable() {
        List<WebElement> MySound = driver.findElements(By.xpath("//table[@id=\"productTable\"]/tbody/tr"));
        for (WebElement check : MySound) {
            String mycheck = check.getText();



        }
    }
}



















