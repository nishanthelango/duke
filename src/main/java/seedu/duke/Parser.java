package seedu.duke;

import java.util.List;

public class Parser {

    /**
     * Parses a String, using a whitespace to separate the tokens
     *
     * @param input the user input as String
     * @return the parsed Input as as String array
     */
    public static String[] parseInput(String input) {
        return input.split(" ");
    }

    /**
     * Gets the description of a Todo task from a list of strings
     *
     * @param parsedList a List containing the parsed Todo task data
     * @return the description of the Todo task as a string
     */
    public static String getToDoDescription(List<String> parsedList) {
        return String.join(" ", parsedList.subList(1, parsedList.size()));
    }

    /**
     * Gets the description of a Deadline task from a list of strings
     *
     * @param parsedList a List containing the parsed Deadline task data
     * @return the description of the Deadline task as a string
     */
    public static String getDeadlineDescription(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/by");
        if (index == -1) {
            throw new DukeException("Please give a deadline.");
        }
        return String.join(" ", parsedList.subList(1, index));

    }

    /**
     * Gets the time and date of Deadline task from a list of strings
     *
     * @param parsedList a List containing the parsed Deadline task data
     * @return the time and date of the Deadline task as a string
     */
    public static String getBy(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/by");
        if (index == -1) {
            throw new DukeException("Please give a deadline.");
        }
        return String.join(" ", parsedList.subList(index + 1, parsedList.size()));
    }

    /**
     * Gets the description of an Event task from a list of strings
     *
     * @param parsedList a List containing the parsed Event task data
     * @return the description of the Event task as a string
     */
    public static String getEventDescription(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/at");
        if (index == -1) {
            throw new DukeException("Please give an event time.");
        }
        return String.join(" ", parsedList.subList(1, index));

    }

    /**
     * Gets the time and date of an Event task from a list of strings
     *
     * @param parsedList a List containing the parsed Event task data
     * @return the time and date of the Event task as a string
     */
    public static String getAt(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/at");
        if (index == -1) {
            throw new DukeException("Please give an event time.");
        }
        return String.join(" ", parsedList.subList(index + 1, parsedList.size()));
    }

    /**
     * Checks if a string is numeric
     *
     * @param s the string to be checked
     * @return true if the string is numeric, false otherwise
     */
    public static boolean isNumeric(String s) {
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
