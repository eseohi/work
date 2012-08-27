/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author ASIKHIA
 */
public class ChoiceGroupMidlet extends MIDlet implements CommandListener
{
    private Display display;
    private Form mainForm;
    private Command exit;
    private Command view;
    private ChoiceGroup email;
    private int replyIndex;
    private int choiceGroupIndex;
    
    public ChoiceGroupMidlet()
    {
        display = Display.getDisplay(this);
        
        email = new ChoiceGroup("Email Option", Choice.EXCLUSIVE);
        
        email.append("Read",null);
        replyIndex = email.append("reply", null);
        email.append("forward", null);
        email.append("delete", null);
        email.setSelectedIndex(replyIndex, true);
        //take not of the above for the on that is first selected
        exit = new Command("Exit", Command.EXIT, 1);
        view = new Command("view", Command.SCREEN, 2);
        mainForm = new Form("");
        choiceGroupIndex = mainForm.append(email);
        mainForm.addCommand(exit);
        mainForm.addCommand(view);
        mainForm.setCommandListener(this);
    }

    public void startApp() {
        display.setCurrent(mainForm);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c,Displayable d) 
    {
        if (c == view)
        {
            StringItem outcome = new StringItem("you selected: ", email.getString(email.getSelectedIndex()));
            mainForm.append(outcome);
           mainForm.delete(choiceGroupIndex);
           //take note of the abovw code
            mainForm.removeCommand(view);
        }
        else if (c == exit)
        {
            destroyApp(false);
            notifyDestroyed();
        }
    
    }
}
