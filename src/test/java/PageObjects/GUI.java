package PageObjects;

import Helpers.CommonMethods;
import Resources.Locators.GUILocators;
import Utils.Config.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;


public class GUI extends BaseClass {
  public   Actions actions = new Actions(driver);


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
    public void alerts(){

        driver.findElement(By.xpath("//button[text()=\"Simple Alert\"]")).click();
       Alert alert  = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        driver.switchTo().defaultContent();
        WebElement mytext = driver.findElement(By.xpath("//p[@id=\"demo\"]"));
        System.out.println(mytext.getText());
        driver.findElement(By.xpath("//button[text()='Confirmation Alert']")).click();
        System.out.println(alert.getText());
        alert.dismiss();
        System.out.println(driver.findElement(By.xpath("//p[@id=\"demo\"]")).getText());

        driver.findElement(By.xpath("//button[text()=\"Prompt Alert\"]")).click();
        alert.sendKeys("Atish");
        System.out.println(alert.getText());
        alert.accept();
        System.out.println(driver.findElement(By.xpath("//p[@id=\"demo\"]")).getText());
    }
    public void mytab() {
        String currentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@id='PopUp']")).click();
        driver.quit();
        Set<String> mytables = driver.getWindowHandles();
        for (String all : mytables) {
            if (!mytables.equals(currentWindow)) {
                driver.switchTo().window(currentWindow);
            }

        }
    }
    public void dragAndDrop(){
        Actions actions = new Actions(driver);
        WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement drop = driver.findElement(By.xpath("//div[@id='droppable']"));
        actions.dragAndDrop(drag,drop).perform();
        WebElement get = driver.findElement(By.xpath("//div[@id='droppable']/p"));
        System.out.println(get.getText());
    }
    public void Slider() throws InterruptedException {
       // WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        // Initialize Actions class
        Actions actions = new Actions(driver);
     WebElement pop  =   driver.findElement(By.xpath("//div[@id='slider-range']/span[2]"));

    Thread.sleep(2000);
        // Drag the slider to the right (50 pixels as an example)
        actions.dragAndDropBy(pop, 302, 0).perform();

      WebElement priceRange = driver.findElement(By.xpath("//input[@id='amount']"));
        System.out.println("Updated Price Range: " + priceRange.getText());
    }
    public void dropDown(){
        driver.findElement(By.xpath("//input[@id=\"comboBox\"]")).click();
        List<WebElement> alldown = driver.findElements(By.xpath("//div[@id=\"dropdown\"]/div"));
        boolean found = false;

        for (WebElement my : alldown) {
            System.out.println(my.getText());
            if (my.getText().equals("Item 46"))
            {
                my.click();
                found = true;
                break;

            }

        }

    }

    public void getLink(){
        List<WebElement> alllink = driver.findElements(By.tagName("a"));
        System.out.println(alllink.size());
        for (WebElement all : alllink){
            String link = all.getAttribute("href");
            if (link == null || link.isEmpty()){
                System.out.println(link);
                continue;
            }
            try{
                HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode >= 400){
                    System.out.println("broken link "+responseCode + link);
                }
                else {
                    System.out.println("valid link"+responseCode);
                }

            } catch (Exception e) {
                System.out.println(link+"due expection"+e.getMessage());
            }

        }
    }
    public void shawdowDom(){

        WebElement shadow = driver.findElement(By.xpath("//div[@id=\"shadow_host\"]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement shadow1 = (WebElement) js.executeScript("return arguments[0].shadowRoot",shadow);

        WebElement shadowElement = shadow1.findElement(By.cssSelector())

    }


}





















