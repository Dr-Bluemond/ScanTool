package scantool;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JTextArea;

public class Error extends JFrame {

    public static void main(String[] args) {
        new Error();
    }

    public Error() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        setSize(d.width, d.height);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(Color.red);
        getContentPane().setVisible(true);//如果改为true那么就变成了红色。
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setAlwaysOnTop(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
                    dispose();
                }
            }
        });
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBackground(Color.red);
        text.setFont(new Font(null, 1, 100));
        text.setText("条码长度错误，请按下“空格键”来确认知晓，并重新录入这一本书。");
        text.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
                    dispose();
                }
            }
        });
        add(text);

    }
}
