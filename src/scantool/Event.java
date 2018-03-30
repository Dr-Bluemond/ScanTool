/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scantool;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bluemond
 * @throws AWTException
 */
public class Event implements KeyListener{

    Form gui;
    Thread playing;
    int i;
    Excel x;

    public Event(Form in, Excel xin) {
        gui = in;
        x = xin;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
            String a = gui.input.getText();
            if (!a.equals("")) {
                x.writeLine(a);
                copyLine(a);
                // x.writeExcel(a);
                gui.input.setText("");

            }
        }
    }

    public void copyLine(String line) {
        try {
            Robot robot = new Robot();
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB);
            Thread.sleep(100);
            KeyPress.keyPressString(robot,line);
            Thread.sleep(10);
            KeyPress.keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
            Thread.sleep(10);
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB);

            KeyPress.keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
            
        } catch (AWTException | InterruptedException e) {
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
