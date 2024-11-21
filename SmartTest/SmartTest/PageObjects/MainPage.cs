using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using SmartTest.Extent_Report;
using SmartTest.HelperMethods;
using System.Reflection;
using System.Reflection.Metadata;
using System.Security.Cryptography.X509Certificates;

namespace SmartTest.PageObjects
{
    public class MainPage : BaseClass
    {
        public static Actions actions = new Actions(driver);
        public static void CommonClicks()
        {
            CommonMethods.waitForElementClickable(By.XPath("//a[normalize-space()='Start from Scratch']")).Click();
            ExtentReporting.TestSteps("Click on Start from Scratch");
            CommonMethods.InvisibilityOfElementLocated(By.XPath("//p[text()='Loading...']"));
            Thread.Sleep(1000);
            CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-spinner']"));
            CommonMethods.waitElementVisible(By.XPath("//canvas[@id='drawingArea']"));
        }
        public static void commonwaits()
        {
            CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-spinner']"));
            CommonMethods.waitElementVisible(By.XPath("//canvas[@id='drawingArea']"));
            CommonMethods.waitElementVisible(By.XPath("//div[@class='color-price']"));
        }
        public static void getalldata()
        {
            IList<string> list = new List<string>();
            Actions actions = new Actions(driver);
            CommonMethods.waitForElementClickable(By.XPath("(//div[text()=\"Building Size\"])[1]")).Click();
            ExtentReporting.TestSteps("Clicked on the Building Size");
            CommonMethods.waitForElementClickable(By.XPath("//div[@class='w2ui-group group-expand']//div[1]//div[1]//div[2]//div[1]")).Click();
            IList<IWebElement> customizedata = driver.FindElements(By.XPath("//div[@id='w2ui-overlay']//tr//div"));
            ExtentReporting.TestSteps("Getting on all Building size Data");
            IWebElement width = driver.FindElement(By.XPath("//input[@name='Width']"));
            IWebElement length = driver.FindElement(By.XPath("//input[@name='Length']"));
            foreach (IWebElement ele in customizedata)
            {
                Console.WriteLine(ele.Text);

                list.Add(ele.Text);
            }
            for (int i = 0; i < list.Count; i++)
            {
                if (list.Contains("Custom Size"))
                {
                    Boolean clicked = false;
                    int attempt = 0;
                    while (!clicked && attempt < 3)
                    {
                        try
                        {
                            actions.MoveToElement(customizedata[list.IndexOf("Custom Size")]).Click().Build().Perform();
                            clicked = true;
                            ExtentReporting.TestSteps("Click on the Custom Size Button");
                        }

                        catch (Exception)
                        {
                            attempt++;

                        }
                    }
                }
                if (width.Enabled && length.Enabled)
                {
                    ExtentReporting.TestSteps("Check the Widh and Length Text field are enable or not");
                    width.SendKeys(Keys.Control + "a");
                    width.SendKeys(Keys.Delete);
                    width.SendKeys("40");
                    width.SendKeys(Keys.Enter);
                    CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-spinner']"));
                    CommonMethods.waitElementVisible(By.XPath("//canvas[@id='drawingArea']"));
                    length.SendKeys(Keys.Control + "a");
                    length.SendKeys(Keys.Delete);
                    length.SendKeys("20");
                    length.SendKeys(Keys.Enter);
                    ExtentReporting.TestSteps("Put the 40 and 20 value in Width and Lenght input field");
                    CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-spinner']"));
                    CommonMethods.waitElementVisible(By.XPath("//canvas[@id='drawingArea']"));
                    string widthvalue = width.GetAttribute("value");
                    string lengthvalue = length.GetAttribute("value");
                    Thread.Sleep(1000);
                    if (!(widthvalue.Equals("40'") && lengthvalue.Equals("20'")))
                    {
                        Assert.Fail();
                    }
                    break;
                }

            }

        }
        public static void getstaticdata(string value)
        {
            IList<string> list = new List<string>();
            commonwaits();
            CommonMethods.waitForElementClickable(By.XPath("//div[@class='w2ui-group group-expand']//div[1]//div[1]//div[2]//div[1]")).Click();

            IList<IWebElement> customizedata = driver.FindElements(By.XPath("//div[@id='w2ui-overlay']//tr//div"));
            IWebElement width = driver.FindElement(By.XPath("//input[@name='Width']"));
            IWebElement length = driver.FindElement(By.XPath("//input[@name='Length']"));

            foreach (IWebElement ele in customizedata)
            {
                Console.WriteLine(ele.Text);
                list.Add(ele.Text);
            }

            int index = list.IndexOf(value);

            if (index >= 0)
            {
                actions.MoveToElement(customizedata[index]).Click().Build().Perform();
                commonwaits();
                //Assert.I(width.Enabled);
                //Assert.IsFalse(length.Enabled);
            }
            else
            {
                Console.WriteLine($"Value '{value}' not found in customizedata.");
            }
            CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-spinner']"));
            CommonMethods.waitElementVisible(By.XPath("//canvas[@id='drawingArea']"));
        }
        public static void gettext()
        {
            getstaticdata("20' x 20'");
            Thread.Sleep(2000);
        }
        public static void dropdown1()
        {
            commonwaits();
            IWebElement measure = CommonMethods.waitForElementClickable(By.XPath("//div[@class='w2ui-group group-expand']//div[2]//div[1]//div[2]//div[1]"));
            Thread.Sleep(2000);
            measure.Click();
            IList<IWebElement> measureform = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']/tbody/tr/td/div"));
            foreach (IWebElement names in measureform)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='Outside of Post']")).Click();
                ExtentReporting.TestSteps("Selected the Outside of Post");
                break;

            }
            commonwaits();
            Thread.Sleep(2000);
            IWebElement roof_height = CommonMethods.waitForElementClickable(By.XPath("//div[@class='w2ui-group group-expand']//div[5]//div[1]//div[2]//div[1]"));
            Thread.Sleep(2000);
            roof_height.Click();
            IList<IWebElement> roofheight = driver.FindElements(By.XPath("//table[@class=\"w2ui-drop-menu\"]//div"));
            foreach (IWebElement ele in roofheight)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='Top Of Wall Material']")).Click();
                ExtentReporting.TestSteps("selected the Top of Wall Material");
                commonwaits();
                break;
            }
            commonwaits();
            IWebElement roofstyle = CommonMethods.waitForElementClickable(By.XPath("(//div[@class='w2ui-field-helper']/div[1])[4]"));
            Thread.Sleep(2000);
            roofstyle.Click();
            IList<IWebElement> myroof = driver.FindElements(By.XPath("//table[@class=\"w2ui-drop-menu\"]//div"));
            foreach (IWebElement ele in myroof)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='Gable']")).Click();
                ExtentReporting.TestSteps("Selected the Gable Dropdown");
                commonwaits();
                break;
            }
            commonwaits();
            IWebElement overhangs = CommonMethods.waitForElementClickable(By.XPath("(//div[@class='w2ui-field-helper']/div[1])[5]"));
            Thread.Sleep(3000);
            CommonMethods.waitElementVisible(By.XPath("(//input[@name=\"Overhangs\"])[1]"));
            overhangs.Click();
            IList<IWebElement> overhangers = driver.FindElements(By.XPath("//table[@class=\"w2ui-drop-menu\"]//div"));
            foreach (IWebElement ele in overhangers)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()=\"Advanced...\"]")).Click();
                IWebElement one = CommonMethods.waitForElementClickable(By.XPath("//input[@name=\"OhangLStr\"]"));
                one.SendKeys(Keys.Control + "a");
                one.SendKeys(Keys.Delete);
                one.SendKeys("1");
                one.SendKeys(Keys.Enter);
                ExtentReporting.TestSteps("Enter the value of 1 in Ohang");

                IWebElement two = CommonMethods.waitForElementClickable(By.XPath("//input[@name='OhangFStr']"));
                two.SendKeys(Keys.Control + "a");
                two.SendKeys(Keys.Delete);
                two.SendKeys("2");
                two.SendKeys(Keys.Enter);
                ExtentReporting.TestSteps("Enter the value of 1 in OhangFStr");
                //CommonMethods.waitForElementClickable(By.XPath("//input[@name=\"OhangRStr\"]")).SendKeys("3'");
                //CommonMethods.waitForElementClickable(By.XPath("//input[@name=\"OhangBStr\"]")).SendKeys("4'");
                CommonMethods.waitForElementClickable(By.XPath("//button[@name=\"Apply\"]")).Click();
                commonwaits();
                break;
            }
        }

        public static void rockPeak()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()=\"Roof Peak\"]")).Click();
            ExtentReporting.TestSteps("Clicked on the Roof Peak");
            CommonMethods.waitForElementClickable(By.XPath("//div[@class='w2ui-group group-expand']//div[1]//div[1]//div[2]//div[1]")).Click();
            IList<IWebElement> fontPeak = driver.FindElements(By.XPath("//table[@class=\"w2ui-drop-menu\"]//div"));
            foreach (IWebElement ele in fontPeak)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='Flying Gable']")).Click();
                ExtentReporting.TestSteps("Clickd on the Flying Gable Drop Down ");
                Thread.Sleep(2000);
                commonwaits();
                break;
            }
            IWebElement peakExtension = driver.FindElement(By.XPath("//input[@name='FrontPeakExtension']"));
            peakExtension.SendKeys(Keys.Control + "a" + Keys.Delete);
            peakExtension.SendKeys("20");
            peakExtension.SendKeys(Keys.Enter);
            ExtentReporting.TestSteps("Enter the 20 value in Front Peak Extension");
            commonwaits();
            CommonMethods.InvisibilityOfElementLocated(By.XPath("//div[@class='w2ui-spinner']"));
            CommonMethods.waitElementVisible(By.XPath("//canvas[@id='drawingArea']"));
            IWebElement backpeaklist = CommonMethods.waitForElementClickable(By.XPath("//div[@class='w2ui-group group-expand']//div[4]//div[1]//div[2]//div[1]"));
            backpeaklist.Click();
            commonwaits();
            ExtentReporting.TestSteps("Clicked on the Back Peak Drop Downs");
            IList<IWebElement> backPeak = driver.FindElements(By.XPath("//table[@class=\"w2ui-drop-menu\"]//div"));
            foreach (IWebElement eles in backPeak)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='Peak Out']")).Click();
                ExtentReporting.TestSteps("Selected the Peak Out");
                commonwaits();
                break;
            }
            IWebElement peakextension = CommonMethods.waitForElementClickable(By.XPath("//input[@name='BackPeakExtension']"));
            peakextension.SendKeys(Keys.Control + "a" + Keys.Delete);
            peakextension.SendKeys("20" + Keys.Enter);
            commonwaits();
            ExtentReporting.TestSteps("Send the value of 20 in Peak Extension");
            IWebElement backpeakextension = CommonMethods.waitForElementClickable(By.XPath("//input[@name='BackPeakOffset']"));
            backpeakextension.SendKeys(Keys.Control + "a" + Keys.Delete);
            backpeakextension.SendKeys("40" + Keys.Enter);
            Thread.Sleep(2000);
            commonwaits();
            ExtentReporting.TestSteps("Send the value of 40 in back peak Extension ");
        }

        public static void ProductSystems()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()=\"Product Systems\"]")).Click();
            Thread.Sleep(2000);
            ExtentReporting.TestSteps("Clicked on the Product Systems");
            IWebElement mainproductslist = CommonMethods.waitForElementClickable(By.XPath("(//input[@name='ProductSystem']/following::div/div)[1]"));
            mainproductslist.Click();

            Thread.Sleep(2000);
            IList<IWebElement> mainProductSystem = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
            foreach (IWebElement ele in mainProductSystem)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='T1-11 Sheathing']")).Click();
                Thread.Sleep(2000);
                ExtentReporting.TestSteps("Clickd on the T1=11 Sheating");
                commonwaits();
                break;
            }
            IWebElement wainscotProductSystem = driver.FindElement(By.XPath("//input[@name= 'WainscotProductSystem']/following::div/div"));
            Thread.Sleep(2000);
            wainscotProductSystem.Click();
            ExtentReporting.TestSteps("Click the dropdown of wainscot Product");
            IList<IWebElement> getwainscot = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
            foreach (IWebElement ele in getwainscot)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='Wainscot1']")).Click();
                Thread.Sleep(2000);
                ExtentReporting.TestSteps("Cliced on the Wainscot1 button");
                commonwaits();
                break;
            }
        }

        //public static void color()
        //{
        //    CommonMethods.waitForElementClickable(By.XPath("//div[text()=\"Colors\"]")).Click();
        //    ExtentReporting.TestSteps("Clicked on the Color");
        //    Thread.Sleep(2000);
        //    IWebElement clickarrow = CommonMethods.waitForElementClickable(By.XPath("//label[text()='Material Color 1']/following::div[5]/div"));
        //    Thread.Sleep(1000);
        //    clickarrow.Click();
        //    Thread.Sleep(500);
        //    ExtentReporting.TestSteps("Clicked on the Dropdown of Material Color");
        //    IList<IWebElement> roofcolor = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
        //    foreach (IWebElement roof in roofcolor)
        //    {
        //        CommonMethods.waitForElementClickable(By.XPath("//span[text()='SLATE BLUE']")).Click();
        //        ExtentReporting.TestSteps("Selected the SLATE BLUE of materail color");
        //        Thread.Sleep(2000);
        //        commonwaits();
        //        break;
        //    }
        //}

        public static void waincot()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()='Wainscot']")).Click();
            ExtentReporting.TestSteps("Clicked on the Wainscot ");
            Thread.Sleep(2000);
            IWebElement clickedon = driver.FindElement(By.XPath("(//input[@name='WainscotHeight'])[1]"));
            clickedon.SendKeys(Keys.Control + "a");
            clickedon.SendKeys(Keys.Delete);
            clickedon.SendKeys("10");
            clickedon.SendKeys(Keys.Enter);
            commonwaits();
        }

        public static void upperSheating()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()='Upper Sheathing']")).Click();
            ExtentReporting.TestSteps("Clicked on the uppersheating");
            Thread.Sleep(2000);
            CommonMethods.waitForElementClickable(By.XPath("(//input[@name='HasUpperSheathing'])[1]")).Click();
            commonwaits();
            IWebElement clickonthesheating = driver.FindElement(By.XPath("(//input[@name='UpperSheathingHeight'])[1]"));
            clickonthesheating.SendKeys(Keys.Control + "a");
            clickonthesheating.SendKeys(Keys.Delete);
            clickonthesheating.SendKeys("10");
            clickonthesheating.SendKeys(Keys.Enter);
            commonwaits();
        }
        public static void resetingProductSystem()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()=\"Product Systems\"]")).Click();
            Thread.Sleep(2000);
            ExtentReporting.TestSteps("Clicked on the Product Systems");
            IWebElement mainproductslist = CommonMethods.waitForElementClickable(By.XPath("(//input[@name='ProductSystem']/following::div/div)[1]"));
            mainproductslist.Click();
            Thread.Sleep(2000);
            IList<IWebElement> mainProductSystem = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
            foreach (IWebElement ele in mainProductSystem)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='None']")).Click();
                Thread.Sleep(2000);
                ExtentReporting.TestSteps("Clickd on the None");
                commonwaits();
                break;
            }
            IWebElement wainscotProductSystem = driver.FindElement(By.XPath("//input[@name= 'WainscotProductSystem']/following::div/div"));
            Thread.Sleep(2000);
            wainscotProductSystem.Click();
            Thread.Sleep(2000);
            commonwaits();
            ExtentReporting.TestSteps("Click the dropdown of wainscot Product");
            IList<IWebElement> getwainscot = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
            foreach (IWebElement ele in getwainscot)
            {
                CommonMethods.waitForElementClickable(By.XPath("//div[text()='None']")).Click();
                Thread.Sleep(2000);
                ExtentReporting.TestSteps("Cliced on the none");
                commonwaits();
                break;
            }
        }

        public static void ceilingLiner()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()='Ceiling Liner']")).Click();
            ExtentReporting.TestSteps("Clicked on the Ceiling Liner");
            Thread.Sleep(2000);
            CommonMethods.waitForElementClickable(By.XPath("(//input[@name='HasCeiling'])[1]")).Click();
            commonwaits();
            CommonMethods.waitForElementClickable(By.XPath("(//input[@name='FlatCeiling'])[1]")).Click();
            commonwaits();
            IWebElement celingcolor = CommonMethods.waitForElementClickable(By.XPath("(//label[text()='Ceiling Color']/following::div[5]/div)[1]"));
            celingcolor.Click();
            IList<IWebElement> dataceling = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
            foreach (IWebElement element in dataceling)
            {

                CommonMethods.waitForElementClickable(By.XPath("//span[text()='Copper Creek']")).Click();
                Thread.Sleep(2000);
                commonwaits();
                break;
            }
        }
        public static void wallLiner()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()='Wall Liner']")).Click();
            ExtentReporting.TestSteps("clicked on the Wall liner");
            commonwaits();
            CommonMethods.waitForElementClickable(By.XPath("(//input[@name='InternalHasLiner'])[1]")).Click();
            commonwaits();
            IWebElement wallinecolor = CommonMethods.waitForElementClickable(By.XPath("(//label[text()='Wall Liner Color']/following::div[5]/div)[1]"));
            wallinecolor.Click();
            IList<IWebElement> datawallliner = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
            foreach (IWebElement element in datawallliner)
            {

                CommonMethods.waitForElementClickable(By.XPath("//span[text()='Yukon']")).Click();
                ExtentReporting.TestSteps("selected the yukon");
                Thread.Sleep(2000);
                commonwaits();
                break;
            }
        }
        public static void floor()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()='Floor']")).Click();
            ExtentReporting.TestSteps("clicked on the floor ");
            commonwaits();
            CommonMethods.waitForElementClickable(By.XPath(" (//input[@name='HasFloor'])[1]")).Click();
            commonwaits();
            IWebElement floordrop1 = driver.FindElement(By.XPath("(//input[@name='FloorColor']/following::div/div[1])[1]"));
            Thread.Sleep(2000);
            floordrop1.Click();
            IList<IWebElement> datafloors = driver.FindElements(By.XPath("//table[@class='w2ui-drop-menu']//div"));
            foreach (IWebElement element in datafloors)
            {
                CommonMethods.waitForElementClickable(By.XPath("//span[text()='CANOUAN']")).Click();
                ExtentReporting.TestSteps("selected the Canouan");
                Thread.Sleep(2000);
                commonwaits();
                break;
            }
        }
        public static void comment()
        {
            CommonMethods.waitForElementClickable(By.XPath("//div[text()='Comments']")).Click();
            ExtentReporting.TestSteps("clicked on the comment");
            CommonMethods.waitForElementClickable(By.XPath("(//textarea[@name='FramerComments'])[1]")).SendKeys("Practice Only");

        }
    }
}
