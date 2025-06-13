package ObjectOriented;
import javax.swing.*;
import java.awt.*;
import java.time.*;

public class EventDialog {
    public static void showDialog(Component parent, LocalDate date, ScheduleManager manager, Runnable onSuccess) {
        showDialog(parent, date, manager, null, onSuccess);
    }

    public static void showDialog(Component parent, LocalDate date, ScheduleManager manager, ScheduleEvent eventToEdit, Runnable onSuccess) {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("제목:"));
        JTextField titleField = new JTextField(eventToEdit != null ? eventToEdit.getTitle() : "");
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("내용:"));
        JTextField descField = new JTextField(eventToEdit != null ? eventToEdit.getDescription() : "");
        inputPanel.add(descField);

        inputPanel.add(new JLabel("시작 시간:"));
        JComboBox<String> startBox = createTimeComboBox();
        startBox.setEditable(true);
        inputPanel.add(startBox);

        inputPanel.add(new JLabel("종료 시간:"));
        JComboBox<String> endBox = createTimeComboBox();
        endBox.setEditable(true);
        inputPanel.add(endBox);

        if (eventToEdit != null) {
            startBox.setSelectedItem(eventToEdit.getStartTime().toLocalTime().toString());
            endBox.setSelectedItem(eventToEdit.getEndTime().toLocalTime().toString());
        }

        int result = JOptionPane.showConfirmDialog(parent, inputPanel, eventToEdit == null ? "일정 등록" : "일정 수정", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText().trim();
                String desc = descField.getText().trim();
                String startStr = ((String)startBox.getEditor().getItem()).trim();
                String endStr = ((String)endBox.getEditor().getItem()).trim();
                LocalTime startTime = LocalTime.parse(startStr);
                LocalTime endTime = LocalTime.parse(endStr);

                if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
                    JOptionPane.showMessageDialog(parent, "종료 시간은 시작 시간보다 늦어야 합니다.");
                    return;
                }

                LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
                LocalDateTime endDateTime = LocalDateTime.of(date, endTime);

                if (eventToEdit == null) {
                    manager.addEvent(title, desc, startDateTime, endDateTime);
                } else {
                    manager.deleteEvent(eventToEdit);
                    manager.addEvent(title, desc, startDateTime, endDateTime);
                }
                onSuccess.run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "시간 형식이 잘못되었습니다. (예: 13:30)");
            }
        }
    }

    private static JComboBox<String> createTimeComboBox() {
        JComboBox<String> box = new JComboBox<>();
        for (int h = 0; h < 24; h++) {
            box.addItem(String.format("%02d:00", h));
            box.addItem(String.format("%02d:30", h));
        }
        return box;
    }
}
