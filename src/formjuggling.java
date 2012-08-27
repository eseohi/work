/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author ASIKHIA
 */
public class formjuggling extends MIDlet  implements CommandListener
{
    private Display display;
    private Form main;
    private Command insert;
    private DateField date;
    private TextField size;
    private TextField Quantity;
    private int dateindex;  //index of date
    public  formjuggling ()
    {
        display = Display.getDisplay(this);
        date = new DateField("the date is: ", DateField.DATE);
        date.setDate(new java.util.Date());
        
        size = new TextField("size", "Large", 5, TextField.ANY);
        Quantity = new TextField("Quatity: ", "8", 2, TextField.NUMERIC);
        insert = new Command("insert", Command.SCREEN, 1);
        main = new Form("my form");
        main.addCommand(insert);
        dateindex = main.append(date);//append date to form and index value where it was inserted
        //capture event
        main.setCommandListener(this);
        
    }
    

    public void startApp()
    {
        display.setCurrent(main);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d)
    {
        if (c == insert)
        {
            if (main.size() == 1)
            {
                main.insert(dateindex, Quantity);
                dateindex += 1;  //date index has changed ,updated it so wathch what will ahappen
            }
                else if (main.size() == 2 && main.get(1) == date)
                    main.set(dateindex, size);
            }
    }
    
}

