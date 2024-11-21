using SmartTest.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SmartTest.TestCase
{
    public class EmodelerTest: BaseClass
    {
        [Test]
        public static void emodeler()
        {
            Emodeler.SelectDropdowns();
            Emodeler.RunTest();
            Emodeler.getableData();

        }  
        
    }
}
