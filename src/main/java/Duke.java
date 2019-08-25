import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetUser();
        reply();
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

    private static void reply() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            printLine();
            if (input.equals("bye")) {
                printIndented("Bye. Hope to see you again soon!");
                printLine();
                return;
            }
            if (input.equals("list")) {
                printIndented("Here are the tasks in your list:");
                for (int i = 1; i <= taskList.size(); i++) {
                    printIndented(i + ".[" + taskList.get(i-1).getStatusIcon()
                            + "] "+ taskList.get(i-1).getDescription());
                }
            }

            else {
                String[] line = input.split(" ");
                if (line.length == 2 && line[0].equals("done") && isNumeric(line[1])) {
                    int i = Integer.parseInt(line[1]);
                    if (i > 0 && i <= taskList.size()) {
                        printIndented("Nice! I've marked this task as done:");
                        taskList.get(i-1).markAsDone();
                        printIndented("[✓] "+ taskList.get(i-1).getDescription());
                    }
                    else {
                        printIndented("Task not found");
                    }
                }
                else {
                    taskList.add(new Task(input));
                    printIndented("added: " + input);
                }
            }
            printLine();
        }
    }

    private static boolean isNumeric(String s) {
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }





}


