package seedu.duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private static final String INVALID_COMMAND_ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String INVALID_TODO_ERROR_MESSAGE = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String INVALID_DEADLINE_ERROR_MESSAGE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    private static final String INVALID_EVENT_ERROR_MESSAGE = "☹ OOPS!!! The description of an event cannot be empty.";

    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }



    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = scanner.nextLine();
                ui.printLine();
                String[] parsedInput = Parser.parseInput(input);
                String command = parsedInput[0];
                List<String> parsedList = Arrays.asList(parsedInput).stream().map(i -> i.trim()).collect(Collectors.toList());

                switch (command) {
                    case "bye":
                        isExit = true;
                        storage.save(tasks);
                        ui.showExit();
                        break;

                    case "list":
                        ui.list(tasks);
                        break;

                    case "done":
                        if (parsedInput.length == 2 && Parser.isNumeric(parsedInput[1])) {
                            int i = Integer.parseInt(parsedInput[1]);
                            try {
                                tasks.markAsDone(i - 1);
                                ui.showDone(tasks.getTasks().get(i - 1).toString());
                            } catch (IndexOutOfBoundsException e) {
                                ui.showTaskNotFoundError();
                            }
                        }
                        else {
                            throw new DukeException(INVALID_COMMAND_ERROR_MESSAGE);
                        }
                        break;

                    case "delete":
                        if (parsedInput.length == 2 && Parser.isNumeric(parsedInput[1])) {
                            int i = Integer.parseInt(parsedInput[1]);
                            try {
                                tasks.delete(i - 1);
                                ui.showSize(tasks);
                            } catch (IndexOutOfBoundsException e) {
                                ui.showTaskNotFoundError();
                            }
                        }
                        else {
                            throw new DukeException(INVALID_COMMAND_ERROR_MESSAGE);
                        }
                        break;

                    case "find":
                        if (parsedInput.length > 1) {
                            TaskList findList = new TaskList();
                            String keyword = ui.getKeyword(input);
                            for (Task task : tasks.getTasks()) {
                                if (task.getDescription().toLowerCase().contains(keyword)) {
                                    findList.getTasks().add(task);
                                }
                            }
                            if (findList.getSize() == 0) {
                                ui.showNoMatchingTasksError();
                            } else {
                                ui.showMatchingTasks(findList);
                            }
                        }
                        else {
                            throw new DukeException(INVALID_COMMAND_ERROR_MESSAGE);
                        }
                        break;

                    case "todo":
                        if (parsedInput.length == 1) {
                            throw new DukeException(INVALID_TODO_ERROR_MESSAGE);
                        }
                        String toDoDescription = Parser.getToDoDescription(parsedList);
                        tasks.add(new Todo(toDoDescription));
                        ui.showSize(tasks);
                        break;

                    case "deadline":
                        if (parsedInput.length == 1) {
                            throw new DukeException(INVALID_DEADLINE_ERROR_MESSAGE);
                        }
                        String deadlineDescription = Parser.getDeadlineDescription(parsedList);
                        String by = Parser.getBy(parsedList);
                        tasks.add(new Deadline(deadlineDescription, by));
                        ui.showSize(tasks);
                        break;

                    case "event":
                        if (parsedInput.length == 1) {
                            throw new DukeException(INVALID_EVENT_ERROR_MESSAGE);
                        }
                        String eventDescription = Parser.getEventDescription(parsedList);
                        String at = Parser.getAt(parsedList);
                        tasks.add(new Event(eventDescription,at));
                        ui.showSize(tasks);
                        break;

                    default:
                        throw new DukeException(INVALID_COMMAND_ERROR_MESSAGE);

                }
            } catch (DukeException de) {
                ui.printIndented(de.getMessage());
            } catch (DateTimeParseException dtpe) {
                ui.printIndented("Please give a valid date and time in dd/MM/yyyy HHmm format.");
            } finally {
                ui.printLine();
            }
        }
    }




}

