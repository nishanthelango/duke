package seedu.duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor For Task
     * Used when reading from local storage
     * @param description the description of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status of the task
     * @return "Y" if task is completed and "N" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y or N
    }

    /**
     * Marks task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toText() {
        return "";
    }

}
