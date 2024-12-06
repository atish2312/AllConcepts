using cPractice.Helper;
using OpenQA.Selenium;
using OpenQA.Selenium.DevTools.V129.Storage;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cPractice.pageObjectModels
{
    public class downloadFile : BaseClass
    {
        
        public static void  GUIElement()
        {
            IWebElement Named = CommonMethods.waitForElementClickable(By.XPath("//*[@id='name']"));
            Named.SendKeys("Tester");

            IWebElement Gender = CommonMethods.waitForElementClickable(By.XPath("//div[@class=\"form-check form-check-inline\"]/input[@id='male']"));
            Gender.Click();


        }
        public static void getCountry()
        {

            IWebElement GetCountry = Driver.FindElement(By.XPath("//select[@id='country']"));
            GetCountry.Click();
            IReadOnlyCollection<IWebElement> AllCountry = Driver.FindElements(By.XPath("//select[@id=\"country\"]/option"));
            Boolean Matched = false;
            string selecting = string.Empty;
            foreach (IWebElement Country in AllCountry)
            {
                string mycountry = Country.Text;    

                if (mycountry.Equals("Japan"))
                {
                    Country.Click();
                    Matched = true;
                    selecting = mycountry;
                }
                Console.WriteLine(Country);


            }
            Console.WriteLine(selecting);
            Assert.IsFalse(Matched);
             
        }
        public static void handleCalandar()
        {
            string Year = "2002";
            string Month = "June";
            string Date = "02";

            IWebElement CurrentYear = Driver.FindElement(By.XPath("//*[@id=\"ui-datepicker-div\"]/div/div/span[2]"));
            IWebElement CurrentMonth = Driver.FindElement(By.XPath("//*[@id=\"ui-datepicker-div\"]/div/div/span"));
            
            while (true) {

                if(!CurrentYear.Equals(Year)&&) {


        }
        
           
            
           
    }
}
