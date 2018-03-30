/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scantool;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Bluemond
 */
public class Form extends JFrame {

    JTextArea console = new JTextArea("I'm a console but I don't do anything now");
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
        add(BorderLayout.CENTER,console);
        add(BorderLayout.SOUTH,input);
        
        setVisible(true);
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
