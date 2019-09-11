package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    /**
     * Constructor for TaskList
     *
     * @param tasks a list of Tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for TaskList
     */
    public TaskList() {
    }

    /**
     * Gets a list of tasks from TaskList
     *
     * @return a list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the size of the task list
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a Task to the task list
     *
     * @param task the Task to be added
     */
    public void add(Task task) {
        ui.showAdded(task.toString());
        tasks.add(task);
    }

    /**
     * Deletes a Task from the task list
     *
     * @param i the index of the Task to be deleted
     */
    public void delete(int i) {
        ui.showDeleted(tasks.get(i).toString());
        tasks.remove(i);

    }

    /**
     * Marks a Task as completed
     *
     * @param i the index of the Task to be marked
     */
    public void markAsDone(int i) {
        tasks.get(i).markAsDone();
    }




}
