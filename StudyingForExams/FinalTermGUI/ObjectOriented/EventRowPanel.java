package ObjectOriented;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventRowPanel extends JPanel {
    public EventRowPanel(ScheduleEvent event, Component parent, ScheduleManager manager, Runnable onUpdate) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 10, 0));

        JLabel timeLabel = new JLabel("[" + event.getStartTime().toLocalTime() + " ~ " + event.getEndTime().toLocalTime() + "]");
        timeLabel.setFont(new Font("Dialog", Font.BOLD, 10));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(event.getTitle());
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        titleLabel.setForeground(new Color(47, 156, 245));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel(event.getDescription());
        descLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton deleteButton = new RoundedRedButton("X");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    parent,
                    "일정을 삭제하시겠습니까?",
                    "삭제 확인",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                manager.deleteEvent(event);
                onUpdate.run();
            }
        });

        add(Box.createVerticalGlue());
        add(timeLabel);
        add(Box.createVerticalStrut(2));
        add(titleLabel);
        add(Box.createVerticalStrut(2));
        add(descLabel);
        add(Box.createVerticalStrut(5));
        add(deleteButton);
        add(Box.createVerticalGlue());

        // 일정 패널 클릭 시 수정 다이얼로그
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == deleteButton) return;
                EventDialog.showDialog(parent, event.getStartTime().toLocalDate(), manager, event, onUpdate);
            }
        });
    }
}
