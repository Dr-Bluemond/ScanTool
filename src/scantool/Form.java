/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scantool;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Bluemond
 */
public class Form extends JFrame {
    
    
    
    
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel consoleLabel = new JLabel("信息面板：");
    JTextArea console = new JTextArea();
    JScrollPane consolePane = new JScrollPane();
    JLabel inputLabel = new JLabel("条码输入框：");
    JTextField input = new JTextField("");
    

    public Form(Excel x) {
        super("快捷录入器 v1.0 By Bluemond From BHSF");

        Event listener = new Event(this, x);
        setLookAndFeel();
        setSize(450,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        
        setAlwaysOnTop(true);
        console.setEditable(false);
        console.setSize(450, 450);
        input.addKeyListener(listener);
        input.setSize(450,50);
        
        consolePane.setViewportView(console);
        BorderLayout layout1 = new BorderLayout();
        panel1.setLayout(layout1);
        panel1.add(BorderLayout.NORTH,consoleLabel);

        panel1.add(BorderLayout.CENTER,consolePane);
        add(BorderLayout.CENTER,panel1);
        

        GridLayout layout2 = new GridLayout(2,1);
        panel2.setLayout(layout2);
        panel2.add(inputLabel);
        panel2.add(input);
        add(BorderLayout.SOUTH,panel2);
        
        if (x.quantity!=0){
        output("当前Excel文件中有"+x.quantity+"个条码，继续录入");
        }else{
            output("已新建Excel，可以开始录入。");
        }
        setVisible(true);
        
        
    }
    public void output(String in){
        console.append(in+"\r\n");
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
