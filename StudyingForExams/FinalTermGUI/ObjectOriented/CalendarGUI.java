package ObjectOriented;
import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class CalendarGUI extends JFrame {
    private JPanel datePanel;
    private JLabel monthLabel;
    private LocalDate currentDate;
    private final ScheduleManager scheduleManager;

    public CalendarGUI(ScheduleManager manager) {
        this.scheduleManager = manager;
        setTitle("OHK & GHJ Calendar(ver.OOP)");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        currentDate = LocalDate.now();

        // 상단
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

        // 요일
        JPanel dayOfWeekPanel = new JPanel(new GridLayout(1, 7, 3, 0));
        for (DayOfWeek dow : DayOfWeek.values()) {
            JLabel label = new JLabel(dow.getDisplayName(TextStyle.SHORT, Locale.ENGLISH), SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(Font.BOLD, 14));
            dayOfWeekPanel.add(label);
        }

        // 날짜 패널
        datePanel = new JPanel(new GridLayout(6, 7, 3, 3));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(dayOfWeekPanel, BorderLayout.NORTH);
        centerPanel.add(datePanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        updateCalendar();
    }

    private void updateCalendar() {
        datePanel.removeAll();

        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        monthLabel.setText(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + currentDate.getYear());

        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int startDay = firstDayOfMonth.getDayOfWeek().getValue();
        int daysInMonth = yearMonth.lengthOfMonth();

        int dayOfWeekIndex = 1;
        for (; dayOfWeekIndex < startDay; dayOfWeekIndex++) {
            datePanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++, dayOfWeekIndex++) {
            final LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), day);

            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.setFont(dayButton.getFont().deriveFont(Font.BOLD));
            dayButton.setMargin(new Insets(0, 0, 0, 0));
            dayButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            dayButton.addActionListener(e -> {
                EventDialog.showDialog(this, date, scheduleManager, this::updateCalendar);
            });

            JPanel eventPanel = new JPanel();
            eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
            eventPanel.setBorder(null);

            List<ScheduleEvent> events = scheduleManager.getEventsByDate(LocalDateTime.of(date, LocalTime.MIDNIGHT));
            for (ScheduleEvent event : events) {
                EventRowPanel eventRow = new EventRowPanel(event, this, scheduleManager, this::updateCalendar);
                eventPanel.add(eventRow);
            }

            JScrollPane scrollPane = new JScrollPane(eventPanel);
            scrollPane.setPreferredSize(new Dimension(100, 32));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);

            dayPanel.add(dayButton);
            dayPanel.add(scrollPane);

            datePanel.add(dayPanel);
        }

        for (; dayOfWeekIndex <= 42; dayOfWeekIndex++) {
            datePanel.add(new JLabel(""));
        }

        datePanel.revalidate();
        datePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScheduleManager manager = new OHKGHJScheduleManager();
            CalendarGUI obj = new CalendarGUI(manager);
            obj.setVisible(true);
        });
    }
}
