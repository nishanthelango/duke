package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {


    protected String by;
    private LocalDateTime byDateTime;

    /**
     * Constructor for Deadline
     *
     * @param description the description of the Deadline
     * @param by the deadline time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime = toDateTime();
    }

    /**
     * Constructor for Deadline
     * Used when reading from storage
     *
     * @param description description of the Deadline
     * @param by the Deadline date and time
     * @param isDone true if the Deadline is completed, false otherwise
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.byDateTime = toDateTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Gets a formatted String containing information about the Deadline
     * to be stored in a .txt file
     *
     * @return the formatted String
     */
    @Override
    public String toText() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Converts the String by to LocalDateTime
     *
     * @return the Deadline in LocalDateTime format
     */
    public LocalDateTime toDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(by, dateTimeFormatter);
    }


}
