package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    private LocalDateTime atDateTime;

    /**
     * Constructor for Event
     * @param description the description of the Event
     * @param at the date and time of the Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.atDateTime = toDateTime();
    }

    /**
     * Constructor for Event
     * @param description the description of the Event
     * @param at the date and time of the Event
     * @param isDone true if the Event is completed, false otherwise
     */

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        this.atDateTime = toDateTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Gets a formatted String containing information about the Event
     * to be stored in a .txt file
     * @return the formatted String
     */
    @Override
    public String toText() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.at;
    }

    /**
     * Converts the String at to LocalDateTime
     * @return the deadline in LocalDateTime format
     */
    public LocalDateTime toDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(at, dateTimeFormatter);
    }





}
