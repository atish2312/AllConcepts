using cPractice.pageObjectModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cPractice.Test_Suites
{
    public class GUITest : BaseClass
    {

        [Test]
        public void naming()
        {
            downloadFile.GUIElement();
            downloadFile.getCountry();

        }
    }
}
