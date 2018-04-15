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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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
        if(length != lengthin){
           gui.print("条码标准长度已设置为" + lengthin + "并已保存以供以后使用");
        }
        length = lengthin;
        
    }
    public void setDelay(int delayin){
        if(delay != delayin){
            gui.print("延迟已更改，延迟过低会出错");
        }
        delay = delayin;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Setting(this).setVisible(true);

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
            String a = gui.input.getText();
            if (a.equals("clear")) {
                gui.console.setText(null);
                gui.input.setText("");
            } else if (a.length() == length) {
                x.writeLine(a);
                copyLine(a);
                gui.print("已录入：" + a);

                gui.input.setText("");
            } else if (a.equals("")) {
                //Do nothing
            } else {
                gui.print("错误的条码：" + a);
                gui.input.setText("");
                new Error().setVisible(true);
            }

        }
    }

    public void copyLine(String line) {
        try {
            Robot robot = new Robot();
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB,delay);
            robot.delay(100);
            KeyPress.keyPressString(robot, line);
            robot.delay(10);
            KeyPress.keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
            robot.delay(10);
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB,delay);
            
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
