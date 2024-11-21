using SmartTest.HelperMethods;
using SmartTest.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace SmartTest.TestCase
{
    public class HomePage : BaseClass
    {
        [Test]   
        public static void myhomeapage()
        {
            MainPage.CommonClicks();
            MainPage.getalldata();
            MainPage.gettext();
            MainPage.dropdown1();
            MainPage.rockPeak();
            MainPage.ProductSystems();
           // MainPage.color();
            MainPage.waincot();
            MainPage.upperSheating();
            MainPage.resetingProductSystem();
            MainPage.ceilingLiner();
            MainPage.wallLiner();
            MainPage.floor();
            MainPage.comment();

        }

    }
}
