/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author ASIKHIA
 */
public class multichoiceMidlet extends MIDlet implements ItemStateListener, CommandListener {

    private Display display;
    private Form mainForm;
    private Command exit;
    private Command view;
    private int selectAllInteger;
    private ChoiceGroup preference;
    private int ChoiceGroupIndex;

    public multichoiceMidlet() {
        display = Display.getDisplay(this);
        preference = new ChoiceGroup("preferences", Choice.MULTIPLE);
        preference.append("Auto Indent", null);
        preference.append("Replace Tab", null);
        preference.append("Wrap Text", null);
        selectAllInteger = preference.append("Select All", null);
        exit = new Command("exit", Command.EXIT, 1);
        view = new Command("view", Command.SCREEN, 2);

        //creating a form
        mainForm = new Form("");
        ChoiceGroupIndex = mainForm.append(preference);
        mainForm.addCommand(exit);
        mainForm.addCommand(view);
        mainForm.setCommandListener(this);
        mainForm.setItemStateListener(this);

    }

    public void startApp() {
        display.setCurrent(mainForm);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == view) {
            boolean selected[] = new boolean[preference.size()];
            preference.getSelectedFlags(selected);
            for (int i = 0; i < preference.size(); i++) {
                System.out.println(preference.getString(i) + (selected[i] ? ": selected" : "not slected"));
            }
        } else if (c == exit) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    public void itemStateChange(Item item) {
        if (item == preference) {
            if (preference.isSelected(selectAllInteger)) {
                for (int i = 0; i > preference.size(); i++) {
                    preference.setSelectedIndex(i, true);
                }
                preference.setSelectedIndex(selectAllInteger, false);
            }
        }
    }

    public void itemStateChanged(Item item) {
    }
}
