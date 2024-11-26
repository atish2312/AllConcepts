package Utils.Config;

import PageObjects.DateTimePicker;
import PageObjects.GUI;

public class PageFactory extends BaseClass {
    private  GUI getGUIPages;
    private DateTimePicker dateTimePicker;

    public  GUI getGetGUIPage()
    {
        if (getGUIPages == null) {
            getGUIPages = new GUI();
        }
        return  getGUIPages;
    }
    public  DateTimePicker getDateTimePicker(){
        if(dateTimePicker == null){
            dateTimePicker = new DateTimePicker();

        }
        return dateTimePicker;
    }

}
