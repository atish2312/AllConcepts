package Test;

import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class GUITest extends BaseClass{

    @Test
            public static void testGUI(){
        pageFactory.getGetGUIPage().gui();
    }
    }
