using MongoDB.Bson.Serialization.IdGenerators;
using NUnit.Framework;
using OpenQA.Selenium;
using SmartTest;
using SmartTest.Extent_Report;
using SmartTest.HelperMethods;
using SmartTest.PageObjects;
using System.Collections.Generic;

public class Emodeler : BaseClass
{
    public static void SelectDropdowns()
    {
        CommonMethods.waitForElementClickable(By.XPath("//a[text()='Settings ']")).Click();
        ExtentReporting.TestSteps("Clicked on the Settings");

        IList<IWebElement> settingDropDown = driver.FindElements(By.XPath("(//ul[@class='dropdown-menu'])[2]"));
        foreach (IWebElement element in settingDropDown)
        {
            CommonMethods.waitForElementClickable(By.XPath("//a[text()='eModeler']")).Click();
            ExtentReporting.TestSteps("Selected the eModeler");
            break; // Assuming you only want the first dropdown option
        }
    }
    public static void EModelerPortals(List<string> names, List<string> email)
    {
        IList<IWebElement> modlerlist = driver.FindElements(By.XPath("//table[@class='tble-homepage table table-bordered table-striped']/tbody/tr/td[2]"));
        var parentWindow = driver.CurrentWindowHandle;
        int maxLinkOpen = 21;
        int openLink = 0;
        foreach (IWebElement element in modlerlist)
        {
            if (openLink >= maxLinkOpen || openLink >= names.Count || openLink >= email.Count) break;

            string myLink = element.Text; 
            driver.SwitchTo().NewWindow(WindowType.Tab);  
            driver.Navigate().GoToUrl(myLink);
            MainPage.commonwaits();
           Thread.Sleep(1000);
            if (IsElementDisplayed(By.XPath("//div[@id='introMain']")))
            {
                HandleAlert();
            }
            SubmitName(names[openLink], email[openLink]);
            CommonMethods.waitForElementClickable(By.XPath("//button[@class='blue-button']")).Click();
            ;
            CommonMethods.waitElementVisible(By.XPath("//div[text()='Save Successful']"));
            CommonMethods.waitForElementClickable(By.XPath("//button[text()='OK']")).Click();
            HandleOptionalButton();

            driver.Close();
            driver.SwitchTo().Window(parentWindow);
            openLink++; // Increment the counter for the next name
        }
    }
    private static bool IsElementDisplayed(By locator)
    {
        try
        {
            return driver.FindElement(locator).Displayed;
        }
        catch (NoSuchElementException)
        {
            return false;
        }
        catch (StaleElementReferenceException)
        {
            return false;
        }
    }
    private static void SubmitName(string name, string email)
    {
        CommonMethods.waitForElementClickable(By.XPath("//button[@id='nextstatus']")).Click();
        ExtentReporting.TestSteps("Clicked on the Submit Quote");
        CommonMethods.waitForElementClickable(By.XPath("(//input[@id='ProjectName'])[3]")).SendKeys(name);
        ExtentReporting.TestSteps("Send the The names in the inputField");
        Thread.Sleep(500);
        CommonMethods.waitForElementClickable(By.XPath("(//input[@name='Email'])[3]")).Clear();
        CommonMethods.waitForElementClickable(By.XPath("(//input[@name='Email'])[3]")).SendKeys(email);
        ExtentReporting.TestSteps("Send the email in the inputField");
    }

    private static void HandleOptionalButton()
    {
        var button = driver.FindElements(By.XPath("//button[@class='blue-button']"));
        if (button.Count > 0 && (button[0].Displayed || button[0].Enabled))
        {
            VanishMethod();
        }
    }
    public static void SendNames(List<string> names, List<string> email)
    {
        EModelerPortals(names, email);
    }
    public static void VanishMethod()
    {
        var button = driver.FindElement(By.XPath("//button[@class='blue-button']"));
        button.Click();
        ExtentReporting.TestSteps("Clicked on the sumbit button");
           
    }

    public static void HandleAlert()
    {
        if (IsElementDisplayed(By.XPath("//div[@id='introMain']")))
        {
            VanishMethod();
        }
    }
    public static void RunTest()
    {
        List<string> namesToSend = new List<string>
    {
        "testerat1", "testerat2", "testerat3", "testerat4",
        "testerat5", "testerat6", "testerat7", "testerat8",
        "tester9", "tester10", "testerat11", "tester12",
        "testerat13", "tester14", "tester15", "tester16",
        "tester17", "tester18", "tester19", "tester20",
        "tester21", "tester22"
    };

        List<string> emailToSend = new List<string>
    {
        "testetat1@gmail.com", "testerat2@gmail.com", "testerat3@gmail.com", "testerat4@gmail.com",
        "testerat5@gmail.com", "testerat6@gmail.com", "testerat7@gmail.com", "testerat8@gmail.com",
        "testersat9@gmail.com", "testerat10@gmail.com", "testerat11@gmail.com", "testerat12@gmail.com",
        "testerat13@gmail.com", "testerat14@gmail.com", "testerat15@gmail.com", "testerat16@gmail.com",
        "testerat17@gmail.com", "testerat18@gmail.com", "testerat19@gmail.com", "testerat20@gmail.com",
        "testerat21@gmail.com"
    };

        SendNames(namesToSend, emailToSend);    
    }
    public static void getableData()
    {
        string names = "testerat1,testerat2,testerat3,testerat4,testerat5,testerat6,testerat7,testerat8,tester9,tester10,testerat11,tester12,testerat13,tester14,tester15,tester16,tester17,tester18,tester19,tester20,tester21,tester22";

        string[] nameArray = names.Split(',');
        Console.WriteLine($"Total names: {nameArray.Length}");

        CommonMethods.waitForElementClickable(By.XPath("//a[text()='Jobs']")).Click();
        ExtentReporting.TestSteps("CLicked on the Jobs Button");
        Loginpage.getLogin();
        ExtentReporting.TestSteps("Enter the Login Credentials");
        CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-lock-msg']"));
        CommonMethods.waitElementVisible(By.XPath("//*[@id='grid_grid_columns']/table/tbody/tr/td[2]/div[2]"));
        bool restart = true; 
        while (restart)
        {
            restart = false; 
            IWebElement tableBody = CommonMethods.waitElementVisible(By.XPath("//div[@class='w2ui-grid-body']//table/tbody"));
            var rows = tableBody.FindElements(By.XPath(".//tr/td[2]"));
            foreach (var row in rows)
            {
                string rowText = row.Text.Trim();
                foreach (string name in nameArray)
                {
                    if (rowText.Equals(name))
                    {
                        Console.WriteLine($"Match found for: {name} in row: {rowText} - Deleting...");              
                        CommonMethods.waitForElementClickable(By.XPath("//button[text()='Delete']")).Click();
                      
                        Thread.Sleep(500);
                        CommonMethods.waitElementVisible(By.XPath("//div[text()='Confirmation']"));
                        CommonMethods.waitForElementClickable(By.XPath("//button[text()='Yes']")).Click();
                        CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-lock-msg']"));
                        restart = true; 
                        break; 
                    }
                }
                if (restart)
                {
                    break; 
                }
            }
        }
        return;
    }
}