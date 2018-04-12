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
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Error extends JFrame {

    JTextArea text = new JTextArea();
    JButton ok = new JButton("确定（空格）");
    Dimension d;


    public Error() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        setSize(d.width, d.height);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.red);//设置背景为红色
        getContentPane().setVisible(true);
        setVisible(true);
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
        text.setBackground(Color.red);
        text.setFont(new Font(null, 1, 100));
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
        ok.setPreferredSize(new Dimension(d.width, d.height / 8));
        ok.setFont(new Font(null, 1, 100));
        add(BorderLayout.SOUTH, ok);
    }
}
