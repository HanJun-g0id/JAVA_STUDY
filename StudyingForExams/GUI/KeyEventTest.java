package SilSup.ten.KeyEventTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventTest extends JFrame implements KeyListener {
    private JTextField field;
    private JPanel panel;
    private JTextArea area;
    public KeyEventTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("문자를 입력하시오"));
        field = new JTextField(10);
        panel.add(field);
        area = new JTextArea(3, 10);
        add(panel, BorderLayout.NORTH);
        add(area, BorderLayout.CENTER);
        field.addKeyListener(this);
        setTitle("KeyEventTest");
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new KeyEventTest();
    }
    public void keyTyped(KeyEvent e) {
        display(e, "Key Typed");
    }
    protected void display(KeyEvent e, String s) {
        char c = e.getKeyChar();
        int KeyCode = e.getKeyCode();
        String modifiers = "Alt : " + e.isAltDown() + " Ctrl : " + e.isControlDown() + " Shift : " + e.isShiftDown();
        area.append(s + "문자 " + c + " (코드: " + KeyCode + ")" + modifiers + "\n");
    }
    public void keyPressed(KeyEvent e) {
        display(e, "Key Pressed");
    }
    public void keyReleased(KeyEvent e) {
        display(e, "Key Released");
    }
}
