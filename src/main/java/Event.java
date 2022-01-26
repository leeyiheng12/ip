import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    DateTimeFormatter niceFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Duke.TaskType type = Duke.TaskType.EVENT;
    public String taskName;
    public LocalDateTime eventTime;

    public Event(String taskName, String eventTime) throws DukeException {
        this.taskName = taskName;
        try {
            this.eventTime = LocalDateTime.parse(eventTime, inputFormat);
        } catch (DateTimeParseException err) {
            throw new DukeException(
                "Datetime format is wrong! Format for events: 'event [some event] /at [dd/mm/yyyy-hh:mm]'");
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.done ? "X" : " ", this.taskName, this.eventTime.format(niceFormat));
    }

    public String exportToString() {
        return String.format("%s %s %s %s",
            this.type, this.taskName, this.done, this.eventTime.format(inputFormat));
    }
}