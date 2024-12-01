package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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


        CommonMethods.waitForElementClickable(By.xpath("//input[@id=\"datepicker\"]")).click();

        String staticMonth = "April";
        int staticYear = 2002;


        String month = CommonMethods.waitForElementClickable(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[1]")).getText();
        int year = Integer.parseInt(driver.findElement(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[2]")).getText());

        while (true) {



            if (year < staticYear || (year == staticYear && !month.equals(staticMonth))) {
                CommonMethods.waitForElementClickable(By.xpath("//span[text()=\"Next\"]")).click();
            } else if (year > staticYear || (year == staticYear && !month.equals(staticMonth))) {
                CommonMethods.waitForElementClickable(By.xpath("//span[text()=\"Prev\"]")).click();
            }

            // Re-fetch the current month and year after clicking a button to update the date picker
            month = CommonMethods.waitForElementClickable(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[1]")).getText();
            year = Integer.parseInt(driver.findElement(By.xpath("//div[@class=\"ui-datepicker-title\"]/span[2]")).getText());

            // If we've reached the correct month and year, exit the loop
            if (year == staticYear && month.equals(staticMonth)) {
                break;
            }
        }
        CommonMethods.waitForElementClickable(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td/a[text()=\"4\"]")).click();
        System.out.println("Reached the target month: " + month + " and year: " + year);

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
        WebElement FireFox = driver.findElement(By.xpath("//div[@class='display-values']/p[2]/strong"));
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
        boolean found = false;


        while (!found) {

            List<WebElement> myAllRows = driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr"));
            for (WebElement row : myAllRows) {
                String rowText = row.findElement(By.xpath("./td[1]")).getText();
                if (rowText.equals("8")) {
                    row.findElement(By.xpath(".//input[@type='checkbox']")).click();
                    System.out.println("Found and selected row: " + row.getText());
                    found = true;
                    break;
                }
            }

            if (!found) {
                List<WebElement> pagination = driver.findElements(By.xpath("//ul[@class='pagination']/li"));
                for (int i = 1; i <= pagination.size(); i++) {
                    WebElement pageLink = driver.findElement(By.xpath("//ul[@class='pagination']/li[" + i + "]/a"));
                    if (pageLink.getAttribute("class").contains("active")) {
                        continue;
                    }
                    pageLink.click();
                    break;
                }
            }
        }
    }

    public void footerLinks(){
        driver.findElement(By.linkText("Home")).click();
    }

    public void HiddenElements(){
        driver.findElement(By.linkText("Hidden Elements & AJAX")).click();
        CommonMethods.waitForElementClickable(By.xpath("//div[@id=\"container\"]/input[1]")).sendKeys("tester");
        WebElement input2 =  driver.findElement(By.xpath("//div[@id=\"container\"]/input[2]"));
        WebElement input2Hidden = driver.findElement(By.xpath("//button[text()=\"Toggle Input Box 2\"]"));
        if (input2.getAttribute("class").contains("hidden")) {
            input2Hidden.click();

        }
        input2.sendKeys("tester");
      String mytext =   driver.findElement(By.xpath("//*[@id=\"container\"]/div[1]/span")).getText();
      System.out.println(mytext);
      driver.findElement(By.xpath("//button[text()=\"Load AJAX Content\"]")).click();
        CommonMethods.waitLoadTest1(By.xpath("//div[@id=\"ajaxContent\"]/h2"));
    }

    public void downloadFile(){
        CommonMethods.waitForElementClickable(By.linkText("Download Files")).click();
        CommonMethods.waitForElementClickable(By.xpath("//textarea[@id=\"inputText\"]")).sendKeys("tester1");
        driver.findElement(By.xpath("//button[@id=\"generateTxt\"]")).click();
        CommonMethods.waitForElementClickable(By.linkText("Download Text File")).click();
    }
    public void wikipedia(){
       WebElement wikipedia =  CommonMethods.waitForElementClickable(By.xpath("//input[@class=\"wikipedia-search-input\"]"));
       wikipedia.sendKeys("test");
       wikipedia.sendKeys(Keys.ENTER);

        List<WebElement>link = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']/a"));
        boolean found = false;
        for(WebElement alllink : link){
            String mylink = alllink.getText();
            System.out.println(mylink);
            if (mylink.equals("Test cricket")){
                JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();",alllink);
                found = true;
                break;
            }

        }
        if(!found){
            Assert.fail();
        }

    }

}





















