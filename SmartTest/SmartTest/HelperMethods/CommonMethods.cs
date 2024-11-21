using OpenQA.Selenium;
using SeleniumExtras.WaitHelpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;



namespace SmartTest.HelperMethods
{
    public class CommonMethods : BaseClass
    {

        public static IWebElement waitForElementClickable(By locator)
        {
            return waits.Until(ExpectedConditions.ElementToBeClickable(locator));
        }
        public static IWebElement waitForElementClickable(WebElement element)
        {
            return waits.Until(ExpectedConditions.ElementToBeClickable(element));
        }
        public static IWebElement waitElementVisible(By element)
        {
            return waits.Until(ExpectedConditions.ElementIsVisible(element));
        }

        public static bool InvisibilityOfElementLocated(By element)
        {
            return waits.Until(ExpectedConditions.InvisibilityOfElementLocated(element));
        }
        public static IWebElement elemenisPresent(By element)
        {
            return waits.Until(ExpectedConditions.ElementExists(element));
        }

        public static void loader()
        {
            CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-spinner']"));
            CommonMethods.waitElementVisible(By.XPath("//canvas[@id='drawingArea']"));
        }

        public static string commonpath()
        {
            var path = Directory.GetCurrentDirectory();
            var mynewpath = path.Replace("\\bin\\Debug\\net8.0", "");
            return mynewpath;
        }

    }

}
