using AventStack.ExtentReports;
using AventStack.ExtentReports.Reporter;
using Microsoft.Extensions.Hosting;
using MongoDB.Bson.Serialization.IdGenerators;
using SmartTest.HelperMethods;
using System;
using System.Collections.Generic;
using System.IO;  // Added to handle Directory operations
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace SmartTest.Extent_Report
{


    public class ExtentReporting : BaseClass
    {
        private static ExtentReports reports;
        public static ExtentTest test;

        public static ExtentReports startReport()
        {
            if (reports == null)
            {          
                var reportPath = CommonMethods.commonpath() + "\\Reports";
         
                Directory.CreateDirectory(reportPath);
                
                var reportFile = reportPath + "\\ExtentReport.html";

                // Initialize the HTML reporter with the report file path
                var htmlreporter = new ExtentHtmlReporter(reportFile);
                reports = new ExtentReports();
                reports.AttachReporter(htmlreporter);
            }
            return reports;
        }
        public static void createtest(string createTest)
        {
            test = startReport().CreateTest(createTest);
        }
        public static void endReport()
        {
            startReport().Flush();
        }
        public static void LogPass(string info)
        {
            test.Pass(info);
        }
        public static void LogFail(string info)
        {
            test.Fail(info);
        }

        public static void LogSkip(string info)
        {
            test.Skip(info);

        }
       public static void TestSteps(string teststeps)
        {
            test.Info(teststeps); 
        }
    }
}



