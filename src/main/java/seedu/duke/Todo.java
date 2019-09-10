package seedu.duke;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toText() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }



}
