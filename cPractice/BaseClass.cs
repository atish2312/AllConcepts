using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata;
using System.Text;
using System.Threading.Tasks;

namespace cPractice
{
    public class BaseClass
    {
        public static IWebDriver Driver;
        public static WebDriverWait wait;

        [SetUp] 
        public void intialize()
        {
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("Start-Maximized");
            Driver = new ChromeDriver(options);
            Driver.Url = TestContext.Parameters.Get("BaseUrl");
            wait = new WebDriverWait(Driver,TimeSpan.FromSeconds(90));


        }

        [TearDown]
        public static void BrowserClose()
        {
            Driver.Close();
            Driver.Dispose();

        }
    }
}
