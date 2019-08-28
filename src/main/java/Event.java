import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    private LocalDateTime atDateTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.atDateTime = toDateTime();
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        this.atDateTime = toDateTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toText() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.at;
    }

    public LocalDateTime toDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(at, dateTimeFormatter);
    }





}
