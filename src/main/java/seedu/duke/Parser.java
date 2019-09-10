import java.util.List;

public class Parser {
    public static String[] parseInput(String input) {
        return input.split(" ");
    }

    public static String getToDoDescription(List<String> parsedList) {
        return String.join(" ", parsedList.subList(1, parsedList.size()));
    }

    public static String getDeadlineDescription(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/by");
        if (index == -1) {
            throw new DukeException("Please give a deadline.");
        }
        return String.join(" ", parsedList.subList(1, index));

    }


    public static String getBy(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/by");
        if (index == -1) {
            throw new DukeException("Please give a deadline.");
        }
        return String.join(" ", parsedList.subList(index + 1, parsedList.size()));
    }

    public static String getEventDescription(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/at");
        if (index == -1) {
            throw new DukeException("Please give an event time.");
        }
        return String.join(" ", parsedList.subList(1, index));

    }


    public static String getAt(List<String> parsedList) throws DukeException {
        int index = parsedList.indexOf("/at");
        if (index == -1) {
            throw new DukeException("Please give an event time.");
        }
        return String.join(" ", parsedList.subList(index + 1, parsedList.size()));
    }

    public static boolean isNumeric(String s) {
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
