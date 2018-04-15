package scantool;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Error extends JFrame {
    
    JTextArea text = new JTextArea();
    JButton ok = new JButton("确定（空格）");
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screen = toolkit.getScreenSize();
    Dimension d = new Dimension(screen.width/2,screen.height/2);
    Color ssr = new Color(227,130,163);
    Color bilibili = new Color(250,114,154);


    public Error() {
        super("ERROR");
        
        setSize(d.width, d.height);
        setLocation(screen.width/4, screen.height/4);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
//        getContentPane().setBackground(pink);//设置背景为红色
        getContentPane().setVisible(true);
        setAlwaysOnTop(true);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
                    dispose();
                }
            }
        });

        text.setLineWrap(true);
        text.setEditable(false);
        text.setBackground(bilibili);
        text.setFont(new Font(null, 1, 70));
        text.setText("条码长度错误,请重新录入这一本书。");

        text.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
                    dispose();
                }
            }
        });
        add(BorderLayout.CENTER, text);

        ok.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ok.setPreferredSize(new Dimension(d.width, d.height / 6));
        ok.setFont(new Font(null, 1, 70));
        add(BorderLayout.SOUTH, ok);
    }
}
