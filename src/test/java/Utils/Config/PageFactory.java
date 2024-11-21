package Utils.Config;

import PageObjects.GUI;

public class PageFactory extends BaseClass {
    private GUI getGUIPages;

    public GUI getGetGUIPage()
    {
        if (getGUIPages == null) {
            getGUIPages = new GUI();
        }

        return getGUIPages;
    }
}
