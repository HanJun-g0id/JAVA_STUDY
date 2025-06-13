import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OHKGHJScheduleManager implements ScheduleManager {
    private final List<ScheduleEvent> events = new ArrayList<>();

    @Override
    public void addEvent(String title, String description, LocalDateTime start, LocalDateTime end) {
        events.add(new ScheduleEvent(title, description, start, end));
    }

    @Override
    public void deleteEvent(ScheduleEvent value) {
        events.remove(value);
    }

    @Override
    public List<ScheduleEvent> getEventsByDate(LocalDateTime date) {
        List<ScheduleEvent> result = new ArrayList<>();
        for (ScheduleEvent event : events) {
            if (event.getStartTime().toLocalDate().equals(date.toLocalDate())) {
                result.add(event);
            }
        }
        return result;
    }
}
