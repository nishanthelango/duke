package seedu.duke;

public class Todo extends Task {

    /**
     * Constructor for Todo
     *
     * @param description the description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo
     *
     * @param description the description of the Todo
     * @param isDone true if the ToDo is completed, false otherwise
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Gets a formatted String containing information about the Todo
     * to be stored in a .txt file
     * @return the formatted String
     */
    public String toText() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }



}
