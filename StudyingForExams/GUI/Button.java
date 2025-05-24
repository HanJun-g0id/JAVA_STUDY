package SilSup.ten.Button;
import javax.swing.*;
import java.awt.event.*;

public class ButtonTest2 extends JFrame {
    private JButton button;
    private JLabel label;

    public ButtonTest2() {
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Button Test");
        JPanel panel = new JPanel();
        button = new JButton("버튼을 누르시오");
        label = new JLabel("아직 버튼이 눌러지지 않았습니다");
        button.addActionListener(new MyListener());
        panel.add(button);
        panel.add(label);
        this.add(panel);
        this.setVisible(true);
    }

    private class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == button) label.setText("마침내 버튼이 눌려졌습니다.");
        }
    }
    public static void main(String[] args) {
        ButtonTest2 frame = new ButtonTest2();
    }
}
