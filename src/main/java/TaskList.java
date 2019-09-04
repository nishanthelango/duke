import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();


    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {

    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void add(Task task) {
        ui.showAdded(task.toString());
        tasks.add(task);
    }


    public void delete(int i) {
        ui.showDeleted(tasks.get(i).toString());
        tasks.remove(i);

    }

    public void markAsDone(int i) {
        tasks.get(i).markAsDone();
    }




}
