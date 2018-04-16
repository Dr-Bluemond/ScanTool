/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scantool;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bluemond
 * @throws AWTException
 */
public class Event implements KeyListener, ActionListener {

    Form gui;
    int i;
    Excel x;
    int length = 0;
    int delay = 0;
    Point target = null;

    public Event(Form gin, Excel xin) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  

            /* 读入TXT文件 */
            String pathname = ".\\config"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
            File filename = new File(pathname); // 要读取以上路径的input。txt文件  
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line;
            line = br.readLine();
            length = Integer.parseInt(line);
            line = br.readLine();
            delay = Integer.parseInt(line);
        } catch (Exception e) {
            e.printStackTrace();

        }
        gui = gin;
        x = xin;
    }

    public void setLength(int lengthin) {
        if (length != lengthin) {
            gui.print("条码标准长度已设置为" + lengthin + "并已保存以供以后使用");
        }
        length = lengthin;

    }

    public void setDelay(int delayin) {
        if (delay != delayin) {
            gui.print("延迟已更改，不出错的前提下推荐延迟设置为0。");
        }
        delay = delayin;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("设置")) {
            new Setting(this).setVisible(true);
        } else {
            gui.print("请将鼠标放在流通系统窗口中，程序将在3秒钟后读取鼠标位置");
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        Point point = MouseInfo.getPointerInfo().getLocation();
                        target = point;
                        gui.print("坐标设置成功（" + target.x + "," + target.y + ")");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();

        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
            String a = gui.input.getText();
            if (a.equals("clear")) {
                gui.console.setText(null);
                gui.input.setText("");
            } else if (target == null) {
                new Error("请设置流通系统条码框坐标！").setVisible(true);
            } else if (a.length() == length) {
                x.writeLine(a);
                newCopyLine(a,delay);
                gui.print("已录入：" + a);

                gui.input.setText("");
            } else if (a.equals("")) {
                //Do nothing
            } else {
                gui.print("错误的条码：" + a);
                gui.input.setText("");
                new Error("条码长度错误,请重新录入这一本书。").setVisible(true);
            }

        }
    }

    public void copyLine(String line) {
        try {
            Robot robot = new Robot();
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB, delay);
            robot.delay(100);
            KeyPress.keyPressString(robot, line);
            robot.delay(10);
            KeyPress.keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
            robot.delay(10);
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB, delay);

        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public void newCopyLine(String line,int dly) {
        try {
            Robot r = new Robot();
            r.mouseMove(target.x, target.y);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.delay(20+dly);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            r.delay(20+dly);
            KeyPress.keyPressString(r, line);
            r.delay(20+dly);
            KeyPress.keyPress(r, KeyEvent.VK_ENTER); // 按下 enter 换行
            r.delay(20+dly);
            gui.input.requestFocus();
            
        } catch (AWTException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
