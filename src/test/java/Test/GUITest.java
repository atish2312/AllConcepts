package Test;

import Utils.Config.BaseClass;
import org.testng.annotations.Test;

public class GUITest extends BaseClass{

    @Test
<<<<<<< HEAD
            public static void testGUI() {
=======
            public static void testGUI() throws InterruptedException {
>>>>>>> a91d0c02500cb33ef1649cfcf78adacd2b8f02cc
        pageFactory.getGetGUIPage().gui();
        pageFactory.getDateTimePicker().dateTime();
    }

    }
