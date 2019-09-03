import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static final String INVALID_COMMAND_ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String INVALID_TODO_ERROR_MESSAGE = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String INVALID_DEADLINE_ERROR_MESSAGE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    private static final String INVALID_EVENT_ERROR_MESSAGE = "☹ OOPS!!! The description of an event cannot be empty.";

    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        greetUser();
        taskList = readData();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printLine();
                printIndented("Bye. Hope to see you again soon!");
                printLine();
                writeData();
                return;
            }
            try {
                reply(input);
            } catch (DukeException e) {
                printIndented(e.getMessage());
                printLine();
            }

        }
    }

    private static void printIndented(String s) {
        System.out.println(INDENTATION + s);
    }

    private static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private static void greetUser() {
        printLine();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printLine();
    }

    private static void reply(String input) throws DukeException {

        printLine();
        if (input.equals("list")) {
            if (taskList.size() == 0) {
                printIndented("There are no tasks in your list.");
            } else {
                printIndented("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    printIndented(i + 1 + "." + taskList.get(i).toString());
                }
            }
        } else {
            String[] line = input.split(" ");
            if (line.length == 2 && line[0].equals("done") && isNumeric(line[1])) {
                int i = Integer.parseInt(line[1]);
                if (i > 0 && i <= taskList.size()) {
                    printIndented("Nice! I've marked this task as done:");
                    taskList.get(i - 1).markAsDone();
                    printIndented(taskList.get(i - 1).toString());
                } else {
                    printIndented("Task not found. Please give a valid index.");
                }
            } else if (line[0].equals("find") && line.length > 1) {
                List<Task> findList = new ArrayList<>();
                String keyword = input.substring(5).toLowerCase();
                for (Task task : taskList) {
                    if (task.getDescription().toLowerCase().contains(keyword)) {
                        findList.add(task);
                    }
                }
                if (findList.size() == 0) {
                    printIndented("There are no matching tasks in your list.");
                } else {
                    printIndented("Here are the matching tasks in your list:");
                    for (int i = 0; i < findList.size(); i++) {
                        printIndented(i + 1 + "." + findList.get(i).toString());
                    }
                }

            }
            else {
                try {
                    List<String> wordsList = Arrays.asList(line).stream().map(i -> i.trim()).collect(Collectors.toList());
                    Task task = null;
                    String s;
                    if (line[0].equals("todo")) {
                        if (line.length == 1) {
                            throw new DukeException(INVALID_TODO_ERROR_MESSAGE);
                        }
                        s = String.join(" ", wordsList.subList(1, wordsList.size()));
                        task = new Todo(s);

                    } else if (line[0].equals("deadline")) {
                        if (line.length == 1) {
                            throw new DukeException(INVALID_DEADLINE_ERROR_MESSAGE);
                        }
                        int index = wordsList.indexOf("/by");
                        if (index == -1) {
                            printIndented("Please give a deadline");
                            printLine();
                            return;
                        }
                        String s2 = String.join(" ", wordsList.subList(index + 1, wordsList.size()));
                        String s1 = String.join(" ", wordsList.subList(1, index));

                        task = new Deadline(s1, s2);
                    } else if (line[0].equals("event")) {
                        if (line.length == 1) {
                            throw new DukeException(INVALID_EVENT_ERROR_MESSAGE);
                        }
                        int index = wordsList.indexOf("/at");
                        if (index == -1) {
                            printIndented("Please give a location");
                            printLine();
                            return;
                        }
                        String s2 = String.join(" ", wordsList.subList(index + 1, wordsList.size()));
                        String s1 = String.join(" ", wordsList.subList(1, index));
                        task = new Event(s1, s2);
                    } else {
                        throw new DukeException(INVALID_COMMAND_ERROR_MESSAGE);
                    }
                    taskList.add(task);
                    printIndented("Got it. I've added this task:");
                    printIndented("  " + task.toString());
                    printIndented("Now you have " + taskList.size() + " tasks in the list.");
                } catch (DateTimeParseException e) {
                    printIndented("Please give a valid date and time in dd/MM/yyyy HHmm format.");
                }
            }
        }
        printLine();

    }

    private static boolean isNumeric(String s) {
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    private static List<Task> readData() {
        try {
            List<Task> tasks = new ArrayList<>();
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/duke.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" \\| ");
                boolean isDone = (line[1].equals("1"));
                if (line[0].equals("D")) {
                    tasks.add(new Deadline(line[2], line[3], isDone));
                }
                else if (line[0].equals("E")) {
                    tasks.add(new Event(line[2], line[3], isDone));
                }
                else if (line[0].equals("T")){
                    tasks.add(new Todo(line[2], isDone));
                }
            }
            return tasks;
        }
        catch (FileNotFoundException e){
            printIndented("Unable to locate file");
            printLine();
            return new ArrayList<>();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            printIndented("Invalid file contents");
            printLine();
            return new ArrayList<>();
        }
    }

    private static void writeData() {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/src/main/resources/duke.txt");
            for (Task task : taskList) {
                fileWriter.write(task.toText() + "\n");
            }
            fileWriter.close();
        }
        catch (IOException e) {
            printIndented("Error occurred while writing to file");
            printLine();
        }
    }




}

