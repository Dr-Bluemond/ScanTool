/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scantool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Box.Filler;

/**
 *
 * @author Bluemond
 */
public class Form extends JFrame {

    JPanel panel1 = new JPanel();
    JPanel panel12 = new JPanel();
    JLabel consoleLabel = new JLabel("信息面板：");
    JButton target = new JButton("确定目标条码框");
    JButton setting = new JButton("设置");
    JTextArea console = new JTextArea();
    JScrollPane consolePane = new JScrollPane();
    JPanel panel2 = new JPanel();
    JLabel inputLabel = new JLabel("条码输入框：");
    JTextField input = new JTextField("");
    Filler filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(130, 0), new java.awt.Dimension(130, 0), new java.awt.Dimension(130, 32767));

    ;
    

    public Form(Excel x) {
        super("快捷录入器 v1.1 By Bluemond/BHSF");
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2, 200);

        Event listener = new Event(this, x);
        setLookAndFeel();
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        setAlwaysOnTop(true);
        console.setEditable(false);
        console.setSize(400, 450);
        input.addKeyListener(listener);
        input.setSize(400, 50);

        consolePane.setViewportView(console);
        BorderLayout layout1 = new BorderLayout();
        panel1.setLayout(layout1);
        FlowLayout flow = new FlowLayout();
        panel12.setLayout(flow);
        panel12.add(consoleLabel);
        panel12.add(filler1);
        target.addActionListener(listener);
        panel12.add(target);
        setting.addActionListener(listener);
        panel12.add(setting);
        panel1.add(BorderLayout.NORTH, panel12);

        panel1.add(BorderLayout.CENTER, consolePane);
        add(BorderLayout.CENTER, panel1);

        GridLayout layout2 = new GridLayout(2, 1, 0, 0);
        panel2.setLayout(layout2);
        panel2.add(inputLabel);
        panel2.add(input);
        add(BorderLayout.SOUTH, panel2);

        if (x.quantity != 0) {
            print("当前Excel文件中有" + x.quantity + "个条码，将会继续录入");
        } else {
            print("已新建Excel，可以开始录入。");
        }
        setVisible(true);

    }

    public void print(String in) {
        console.append(in + "\r\n");
        JScrollBar sBar = consolePane.getVerticalScrollBar();
        sBar.setValue(sBar.getMaximum());
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception e) {

        }
    }
}
