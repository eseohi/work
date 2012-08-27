/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author ASIKHIA
 */
public class copyandpastMidlet extends MIDlet implements CommandListener
{
private Display display;
private TextBox textbox;
private Command  exit;
private Command startMark;
private Command  copy;
private Command  paste;
private int beginOffset = 0; //the start index to copy
private  char[] clipBoard = null;//the clip board
private int clipboardChar = 0;//number of chars in a clipboard

public copyandpastMidlet()
{
    display = Display.getDisplay(this);
    exit = new Command("exit", Command.EXIT, 1);
    startMark = new Command("Mark", Command.SCREEN, 2);
    copy = new Command("copy", Command.SCREEN, 3);
    paste = new Command("paste", Command.SCREEN, 4);
    
    textbox = new TextBox("Clip Board", "AaBbcc", 8, TextField.ANY);
    textbox.addCommand(exit);
    textbox.addCommand(startMark);
    textbox.addCommand(copy);
    textbox.addCommand(paste);
    textbox.setCommandListener(this);
    clipBoard = new char[textbox.getMaxSize()];
    
}
    public void startApp() {
        display.setCurrent(textbox);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == startMark)
        {
            beginOffset = textbox.getCaretPosition();
            //take note of the above code
            char[] chr = new char[textbox.size()];
            textbox.getChars(chr);
            //this is where the copying occurs
            clipboardChar = textbox.getCaretPosition() - beginOffset;
            System.arraycopy(chr, beginOffset, clipBoard, 0, clipboardChar);
        }
        else if (c == exit)
        {
            destroyApp(false);
            notifyDestroyed();
            
        }
    }
}
