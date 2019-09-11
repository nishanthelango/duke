package seedu.duke;

public class Ui {

    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    /**
     * prints a String with indentation
     *
     * @param s the string to be indented
     */
    public void printIndented(String s) {
        System.out.println(INDENTATION + s);
    }

    /**
     * prints a horizontal line
     */
    public void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * prints a list of Tasks
     *
     * @param tasks the TaskList to be printed
     */
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

    /**
     * Greets the user
     */

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

    /**
     * prints a message to show that a task has been marked as completed
     *
     * @param task the Task that has been marked
     */
    public void showDone(String task) {
        printIndented("Nice! I've marked this task as done:");
        printIndented("  " + task);
    }

    /**
     * prints a message to show that a task has been removed
     *
     * @param task the Task that has been removed
     */
    public void showDeleted(String task) {
        printLine();
        printIndented("Noted. I've removed this task:");
        printIndented("  " + task);
    }

    /**
     * prints an exit message to signal the termination of Duke
     */
    public void showExit() {
        printLine();
        printIndented("Bye. Hope to see you again soon!");
    }

    /**
     * Prints error when loading from file
     */
    public void showLoadingError() {
        printIndented("Error loading from file.");
    }

    /**
     * prints error when task cannot be found
     */
    public void showTaskNotFoundError() {
        printIndented("Task not found. Please give a valid index.");
    }

    /**
     * prints a list of tasks with a matching Tasks message
     *
     * @param findList the list of matching Tasks
     */
    public void showMatchingTasks(TaskList findList) {
        printIndented("Here are the matching tasks in your list:");
        for (int i = 0; i < findList.getTasks().size(); i++) {
            printIndented(i + 1 + "." + findList.getTasks().get(i).toString());
        }
    }

    /**
     * prints error when there are no matching tasks found
     */
    public void showNoMatchingTasksError() {
        printIndented("There are no matching tasks in your list.");
    }

    /**
     * Gets a keyword from the user input, to be searched for
     *
     * @param input the user input
     * @return the keyword to be searched for
     */
    public String getKeyword(String input) {
        return input.substring(5).toLowerCase();
    }

    /**
     * Shows a Task that has been added
     *
     * @param task the Task that has been added
     */
    public void showAdded(String task) {
        printIndented("Got it. I've added this task:");
        printIndented("  " + task);
    }

    /**
     * Shows the size of the TaskList
     *
     * @param tasks the TaskList
     */

    public void showSize(TaskList tasks) {
        printIndented("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
