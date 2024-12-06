using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using OpenQA.Selenium.Support.UI;
using SeleniumExtras.WaitHelpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cPractice.Helper
{
    public class CommonMethods : BaseClass
    {
        public static IWebElement element;
        public static Actions GetAction() => new Actions(Driver);
        public static SelectElement GetSelectElement(IWebElement element) => new SelectElement(element);
        public static WebDriverWait GetWebDriverWait() => new WebDriverWait(Driver, TimeSpan.FromSeconds(5));
        
        public static IWebElement waitForElementClickable(IWebElement element)
        {
            return wait.Until(ExpectedConditions.ElementToBeClickable(element));    
          
            }
        public static IWebElement waitForElementClickable(By element)
        {
            return wait.Until(ExpectedConditions.ElementToBeClickable(element));

        }
    }
 }














