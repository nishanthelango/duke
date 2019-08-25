import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

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
            if (input.equals("bye")) {
                printLine();
                printIndented("Bye. Hope to see you again soon!");
                printLine();
                return;
            }
            else {
                printLine();
                printIndented(input);
                printLine();
            }
        }
    }

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
}
