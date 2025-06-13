import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;

public class CalendarGUI extends JFrame {
    private JPanel datePanel;
    private JLabel monthLabel;
    private LocalDate currentDate;
    private ScheduleManager scheduleManager;

    public CalendarGUI() {
        setTitle("OHK & GHJ Calendar");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        currentDate = LocalDate.now();
        scheduleManager = new OHKGHJScheduleManager();

        // 상단 패널 (월 변경)
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");
        monthLabel = new JLabel("", SwingConstants.CENTER);

        prevButton.addActionListener(e -> {
            currentDate = currentDate.minusMonths(1);
            updateCalendar();
        });
        nextButton.addActionListener(e -> {
            currentDate = currentDate.plusMonths(1);
            updateCalendar();
        });

        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);

        // 요일 패널
        JPanel dayOfWeekPanel = new JPanel(new GridLayout(1, 7, 3, 0));
        for (DayOfWeek dow : DayOfWeek.values()) {
            JLabel label = new JLabel(dow.getDisplayName(TextStyle.SHORT, Locale.ENGLISH), SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(Font.BOLD, 14));
            dayOfWeekPanel.add(label);
        }

        // 날짜 패널 (6행 7열 고정)
        datePanel = new JPanel(new GridLayout(6, 7, 3, 3)); // 항상 6주(6행)로 고정

        // 전체 레이아웃
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(dayOfWeekPanel, BorderLayout.NORTH);
        centerPanel.add(datePanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        updateCalendar();
    }

    private void updateCalendar() {
        datePanel.removeAll();

        // 월 레이블 업데이트
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        monthLabel.setText(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + currentDate.getYear());

        // 시작 요일 및 총 일 수
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int startDay = firstDayOfMonth.getDayOfWeek().getValue();
        int daysInMonth = yearMonth.lengthOfMonth();

        // 앞 빈칸
        int dayOfWeekIndex = 1;
        for (; dayOfWeekIndex < startDay; dayOfWeekIndex++) {
            datePanel.add(new JLabel(""));
        }

        // 날짜 출력
        for (int day = 1; day <= daysInMonth; day++, dayOfWeekIndex++) {
            final LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), day);

            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.setFont(dayButton.getFont().deriveFont(Font.BOLD));
            dayButton.setMargin(new Insets(0, 0, 0, 0));
            dayButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // 일정 추가 버튼 동작
            dayButton.addActionListener(e -> {
                EventDialog.showDialog(this, date, scheduleManager, this::updateCalendar);
            });

            // 일정 패널
            JPanel eventPanel = new JPanel();
            eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
            eventPanel.setBorder(null); // 내부 패널 보더 제거

            // 일정 추가
            List<ScheduleEvent> events = scheduleManager.getEventsByDate(LocalDateTime.of(date, LocalTime.MIDNIGHT));
            for (ScheduleEvent event : events) {
                EventRowPanel eventRow = new EventRowPanel(
                        event,
                        this,
                        scheduleManager,
                        this::updateCalendar
                );
                eventPanel.add(eventRow);
            }

            // 스크롤 영역 (테두리 제거, 크기 축소)
            JScrollPane scrollPane = new JScrollPane(eventPanel);
            scrollPane.setPreferredSize(new Dimension(100, 32)); // 더 작게
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);

            dayPanel.add(dayButton);
            dayPanel.add(scrollPane);

            datePanel.add(dayPanel);
        }

        // 남은 칸 빈칸으로 채우기 (항상 6행 7열 유지)
        for (; dayOfWeekIndex <= 42; dayOfWeekIndex++) {
            datePanel.add(new JLabel(""));
        }

        datePanel.revalidate();
        datePanel.repaint();
    }

    static class EventDialog {
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
            startBox.setEditable(true); // 직접 입력 가능
            inputPanel.add(startBox);

            inputPanel.add(new JLabel("종료 시간:"));
            JComboBox<String> endBox = createTimeComboBox();
            endBox.setEditable(true); // 직접 입력 가능
            inputPanel.add(endBox);

            // 기존 이벤트일 경우 시간 세팅
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

        // 00:00~23:30 30분 단위 콤보박스 생성
        private static JComboBox<String> createTimeComboBox() {
            JComboBox<String> box = new JComboBox<>();
            for (int h = 0; h < 24; h++) {
                box.addItem(String.format("%02d:00", h));
                box.addItem(String.format("%02d:30", h));
            }
            return box;
        }
    }

    static class EventRowPanel extends JPanel {
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

            JButton deleteButton = new RoundedButton("X");
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
                    // 삭제 버튼 클릭은 무시
                    if (e.getSource() == deleteButton) return;
                    EventDialog.showDialog(parent, event.getStartTime().toLocalDate(), manager, event, onUpdate);
                }
            });
        }
    }

    static class RoundedButton extends JButton {
        private Color normalBackground = new Color(220, 53, 69);
        private Color hoverBackground = new Color(200, 35, 51);
        private Color pressedBackground = new Color(180, 30, 45);

        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Dialog", Font.BOLD, 12));
            setPreferredSize(new Dimension(40, 22));
            setBackground(normalBackground);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(hoverBackground);
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(normalBackground);
                    repaint();
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    setBackground(pressedBackground);
                    repaint();
                }
                @Override
                public void mouseReleased(MouseEvent e) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarGUI obj = new CalendarGUI();
            obj.setVisible(true);
        });
    }
}
