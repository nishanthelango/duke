package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void addWorks() throws DukeException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("borrow book");
        Task task2 = new Event("meeting", "12/12/2019 1800");
        Task task3 = new Deadline("read book", "12/12/2019 2100");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        assertEquals("[T][N] borrow book", taskList.getTasks().get(0).toString());
        assertEquals("[E][N] meeting (at: 12/12/2019 1800)", taskList.getTasks().get(1).toString());
        assertEquals("[D][N] read book (by: 12/12/2019 2100)", taskList.getTasks().get(2).toString());
    }

    @Test
    public void deleteWorks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("borrow book");
        Task task2 = new Event("meeting", "12/12/2019 1800");
        Task task3 = new Deadline("read book", "12/12/2019 2100");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.delete(0);
        assertEquals("[E][N] meeting (at: 12/12/2019 1800)", taskList.getTasks().get(0).toString());
        assertEquals("[D][N] read book (by: 12/12/2019 2100)", taskList.getTasks().get(1).toString());
    }
}
