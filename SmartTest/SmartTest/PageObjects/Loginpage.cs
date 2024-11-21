using OpenQA.Selenium;
using SmartTest.Extent_Report;
using SmartTest.HelperMethods;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SmartTest.PageObjects
{
    public  class Loginpage : BaseClass
    {
        public static void  getLogin()
        {
            CommonMethods.waitForElementClickable(By.XPath("//input[@id='Username']")).SendKeys(TestContext.Parameters.Get("Email"));
            CommonMethods.waitForElementClickable(By.XPath("//input[@id='Password']")).SendKeys(TestContext.Parameters.Get("Password"));
            CommonMethods.waitForElementClickable(By.XPath("//input[@value='Log in']")).Click();
            ExtentReporting.TestSteps("Filled the Login Details");
        }        
    }
}
