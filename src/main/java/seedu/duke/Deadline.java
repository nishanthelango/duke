import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    private LocalDateTime byDateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime = toDateTime();
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.byDateTime = toDateTime();

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toText() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    public LocalDateTime toDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(by, dateTimeFormatter);
    }


}
