/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.IOException;

/**
 * @author ASIKHIA
 */
public class workMidlet extends MIDlet implements CommandListener
{
    private Display display;
    private Form mainpage;
    private Command test;
    private Command exit;
    private TextField telephonenumber;
    private TextField password;
    private String areaCodeTable [][] = {{"512","022"},{"717","567"}};
    public workMidlet ()
    {
        display = Display.getDisplay(this);
        test = new Command("test", Command.SCREEN, 1);
        exit = new Command("exit", Command.SCREEN, 1);
        telephonenumber = new TextField("Phone:", "", 9, TextField.PHONENUMBER);
        //creating a form
        mainpage = new Form("Area Codes");
        mainpage.addCommand(test);
        mainpage.addCommand(exit);
        mainpage.append(telephonenumber);
        //mainpage.append(password);
        mainpage.setCommandListener(this);
    }

    
    
    public void startApp() {
        display.setCurrent(mainpage);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    public  void commandAction(Command c, Displayable d) {
        if (c == test) {
            if (telephonenumber.size() == 9)
            {
                char buffer[] = new char[9];
                telephonenumber.getChars(buffer);
                StringItem tmp= new StringItem(null, ("the area code is: " + (areaCodeLookup(buffer)+(" it has not being updated"))));
                if (mainpage.size() == 1)
                    mainpage.append(tmp);
                else
                    mainpage.set(1, tmp);
            }
        }
        else if (c == exit)
        {
            destroyApp(false);
            notifyDestroyed();
        }
    }
   /* public void password() {
        password.getString();
        password = new TextField("password", "", 10, TextField.ANY | TextField.PASSWORD);
        
        //return 
               display.setCurrent(mainpage);
              }
          
              */

private boolean areaCodeLookup(char [] buffer) {
    String str = new String(buffer,0,3);
  //take note of the above
   
       for(int x = 0;x < areaCodeTable.length; x++)
    {
        if (str.equals(areaCodeTable[x][0])){
        telephonenumber.delete(0, 3);
        telephonenumber.insert(areaCodeTable[x][1].toCharArray(),0, 3 ,0);
        //take not of how the arrays where used
        return true;
        }
    }
    
return false;
}
}


