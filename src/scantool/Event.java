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

/**
 *
 * @author Bluemond
 * @throws AWTException
 */
public class Event implements ActionListener,KeyListener,
        Runnable {

    Form gui;
    Thread playing;
    int i;
    Excel x;

    public Event(Form in,Excel xin) {
        gui = in;
        x = xin;
    }
    @Override
    public void keyPressed(KeyEvent event){
        if(event.getKeyChar() == KeyEvent.VK_ENTER){
            String a = gui.codeInput.getText();
            if(!a.equals("")){
               // x.code.add(a);
               // x.writeExcel(a);
                gui.codeInput.setText("");

                
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("打开")) {
        }
        if (command.equals("清除")) {
        }
        if (command.equals("录入")) {
            gui.stop.setEnabled(true);
            try {
                pasteFile();
            } catch (Exception e) {
            }

        }
        if (command.equals("中止录入")) {
            playing = null;
            int j = i + 1;
//            gui.got.setText("中止于第" + i + "/" + x.code.size() + "个条码");
            gui.paste.setEnabled(true);
            gui.stop.setEnabled(false);

        }
    }




    void pasteFile() throws Exception {
        playing = new Thread(this);
        playing.start();
        gui.paste.setEnabled(false);
    }

    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        try {
            Robot robot = new Robot();
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB);
            gui.got.setText("录入将在3秒后开始");
            robot.delay(3000);
            gui.got.setText("正在录入");
            int del = Integer.parseInt(gui.delay.getText());
            int j = x.code.size();
            for (i = 0; i < j; i++) {
                if (playing == thisThread) {

                    KeyPress.keyPressString(robot, x.code.get(i));
                    robot.delay(30);
                    KeyPress.keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行

                    robot.delay(del);
                } else {
                    break;
                }
            }
            if (i == x.code.size()) {
                gui.got.setText("已完成录入");
                gui.paste.setEnabled(true);
                gui.stop.setEnabled(false);
            }
        } catch (AWTException e) {
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
