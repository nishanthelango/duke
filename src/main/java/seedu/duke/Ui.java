public class Ui {

    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    public void printIndented(String s) {
        System.out.println(INDENTATION + s);
    }

    public void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void list(TaskList tasks) {

        if (tasks.getTasks().size() == 0) {
            printIndented("There are no tasks in your list.");
        } else {
            printIndented("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                printIndented(i + 1 + "." + tasks.getTasks().get(i).toString());
            }
        }
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printLine();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printLine();
    }

    public void showDone(String task) {
        printIndented("Nice! I've marked this task as done:");
        printIndented("  " + task);
    }

    public void showDeleted(String task) {
        printLine();
        printIndented("Noted. I've removed this task:");
        printIndented("  " + task);
    }

    public void showExit() {
        printLine();
        printIndented("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        printIndented("");
    }

    public void showTaskNotFoundError() {
        printIndented("Task not found. Please give a valid index.");
    }

    public void showMatchingTasks(TaskList findList) {
        printIndented("Here are the matching tasks in your list:");
        for (int i = 0; i < findList.getTasks().size(); i++) {
            printIndented(i + 1 + "." + findList.getTasks().get(i).toString());
        }
    }

    public void showNoMatchingTasksError() {
        printIndented("There are no matching tasks in your list.");
    }

    public String getKeyword(String input) {
        return input.substring(5).toLowerCase();
    }

    public void showAdded(String task) {
        printIndented("Got it. I've added this task:");
        printIndented("  " + task);
    }

    public void showSize(TaskList tasks) {
        printIndented("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
