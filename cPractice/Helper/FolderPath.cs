using Microsoft.VisualBasic;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cPractice.Helper
{
    public class FolderPath : BaseClass
    {
       public static string folderPath()
        {
            var dir =  new DirectoryInfo(Directory.GetCurrentDirectory());
            for (int i = 0; i < 3 && dir.Parent != null; i++)
            {
                dir = dir.Parent;
            }
            return dir.FullName;
        }
        public static string folderName(string fileName) { 
            var path = folderPath();
            return Path.Combine(path, fileName);
        
        }





    }
}
