package ObjectOriented;
import javax.swing.*;
import java.awt.*;

public class RoundedRedButton extends JButton {
    private Color normalBackground = new Color(220, 53, 69);
    private Color hoverBackground = new Color(200, 35, 51);
    private Color pressedBackground = new Color(180, 30, 45);

    public RoundedRedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Dialog", Font.BOLD, 12));
        setPreferredSize(new Dimension(40, 22));
        setBackground(normalBackground);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(hoverBackground);
                repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(normalBackground);
                repaint();
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                setBackground(pressedBackground);
                repaint();
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                setBackground(hoverBackground);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        // Do nothing to keep custom background
    }
}
