/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author ASIKHIA
 */
public class timeMidlet extends MIDlet implements ItemStateListener, CommandListener
{
private Display display;
private Form form;
private Command Exit;
private Command veiw;//go to next page
private DateField date;
private Form Form2;

public timeMidlet() 
{
    display = Display.getDisplay(this);
    //credating the date and populating it with curent date
    date = new DateField("date is: ", DateField.DATE);
    date.setDate(new java.util.Date());
    
    Exit = new Command("exit", Command.EXIT, 2);
    veiw = new Command("veiw", Command.SCREEN, 1);
    form = new Form("here is the date");
    Form2 = new Form("all is well");
    form.addCommand(Exit);
    form.addCommand(veiw);
    form.append(date);
    form.setCommandListener(this);
    form.setItemStateListener(this);
}

    public void startApp() {
        display.setCurrent(form);
      //  display.setCurrent(Form2);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void itemStateChanged(Item item) {
        date.setLabel("new date: ");
    }

    public void commandAction(Command c, Displayable d) 
    {
       /* if (c == Exit)
        {
            destroyApp(false);
            notifyDestroyed();
        }*/
        if (c == veiw)
        {
         display.setCurrent(Form2);   
        }
        else {
            destroyApp(true);
            notifyDestroyed();
        }
    }
   
}
