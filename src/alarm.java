/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ASIKHIA
 */
public class alarm extends MIDlet implements ItemStateListener, CommandListener
{
    private Display display;
    private Form form;
    private Command snooze;
    private Command exit;
    private Command reset;
    private DateField snoozetime;
    private int dateIndex;
    private Date currentTime;
    private Timer timer;
    private SnoozeTimer snoozetimer;
    
    
    
    private boolean dateOK = false;
    public alarm() 
    {
        
    }

    public void startApp() 
    {
        display = Display.getDisplay(this);
        form = new Form("time me now!: ");
        currentTime = new Date();
        snoozetime = new DateField("Snooze", Command.EXIT);
        snoozetime.setDate(currentTime);
        snooze = new Command("Snooze", Command.SCREEN, 1);
        reset = new Command("reset", Command.SCREEN, 1);
        exit = new Command("exit", Command.EXIT, 1);
        form.append(snoozetime);
        form.addCommand(snooze);
        form.addCommand(exit);
        form.addCommand(reset);
        form.setCommandListener(this);
        form.setItemStateListener(this);
        display.setCurrent(form);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void itemStateChanged(Item item)
    {
        if (item == snoozetime)
        {
            if (snoozetime.getDate().getTime() < currentTime.getTime())
                dateOK = false;
            else
                dateOK = true;
        }
    }

    public void commandAction(Command c, Displayable d)
    {
        if (c == snooze)
        {
        if (dateOK ==false)
        {
            Alert al = new Alert("you must be nuts u need a time clock scumbag");
            al.setTimeout(Alert.FOREVER);
            al.setType(AlertType.ERROR);
            display.setCurrent(al);
        }
        else
        {
            timer = new Timer();
            snoozetimer = new SnoozeTimer();
            long amount = snoozetime.getDate().getTime() - currentTime.getTime();
            timer.schedule(snoozetimer, amount);
            form.removeCommand(snooze);
            form.removeCommand(reset);
            form.delete(dateIndex);
            form.setTitle("now ama wake your ass up!!!");
                    
        }
    }
    else if (c == reset)
    {
        snoozetime.setDate(currentTime = new Date());
    }
    else if (c == exit)
        destroyApp(false);
        notifyDestroyed();
    }

    private class SnoozeTimer extends TimerTask
    {

        public final void run() 
        {
        Alert al = new Alert("Time up lazy ass!");
        al.setTimeout(Alert.FOREVER);
        al.setType(AlertType.ALARM);
        AlertType.ERROR.playSound(display);
        display.setCurrent(al);
        cancel();
    }
}
}
