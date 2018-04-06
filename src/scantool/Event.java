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
    Thread playing;
    int i;
    Excel x;
    public int length = 0;

    public Event(Form in, Excel xin) {
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
        } catch (Exception e) {
            e.printStackTrace();

        }
        gui = in;
        x = xin;
    }

    public void setLength(int lengthin) {
        length = lengthin;
        gui.output("条码标准长度已设置为" + length + "并已保存以供以后使用");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Setting set = new Setting(this);
        set.setVisible(true);

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
                gui.output("已录入：" + a);

                gui.input.setText("");
            } else {
                gui.output("错误的条码：" + a);
                gui.input.setText("");
                new Error();
            }

        }
    }

    public void copyLine(String line) {
        try {
            Robot robot = new Robot();
            KeyPress.keyPressWithAlt(robot, KeyEvent.VK_TAB);
            Thread.sleep(100);
            KeyPress.keyPressString(robot, line);
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
