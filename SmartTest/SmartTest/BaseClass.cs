using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using SeleniumExtras.WaitHelpers;
using SmartTest.Extent_Report;
using SmartTest.HelperMethods;
using SmartTest.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SmartTest
{

    public class BaseClass
    {
        public static IWebDriver driver;
        public static WebDriverWait waits;

        [SetUp]
        public static void intialize()
        {
            ExtentReporting.createtest(TestContext.CurrentContext.Test.Name);
            ChromeOptions options = new ChromeOptions();
            options.AddArgument("Start-Maximized");
            driver = new ChromeDriver(options);
            driver.Url = TestContext.Parameters.Get("BaseUrl");
            waits = new WebDriverWait(driver, TimeSpan.FromSeconds(90));
            Loginpage.getLogin();

        }
        [TearDown]
        public void tearDown()
        {
            endTest();
            ExtentReporting.endReport();
            driver.Dispose();

        }
        private void endTest()
        {
            var status = TestContext.CurrentContext.Result.Outcome.Status;
            var message = TestContext.CurrentContext.Result.Message;
            switch (status)
            {
                case NUnit.Framework.Interfaces.TestStatus.Failed:
                    ExtentReporting.LogFail($"This test is fail" + message);
                    break;
                case NUnit.Framework.Interfaces.TestStatus.Passed:
                    ExtentReporting.LogPass($"This test is Pass" + message);
                    break;
            }
        }
    }
}
