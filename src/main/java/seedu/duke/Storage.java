import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {

    private static String filePath;
    private static Ui ui;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }


    public List<Task> load() throws DukeException {
        try {
            List<Task> tasks = new ArrayList<>();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            if (!scanner.hasNext()) {
                throw new DukeException("Your task list is empty");
            }
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" \\| ");
                boolean isDone = (line[1].equals("1"));
                if (line[0].equals("D")) {
                    tasks.add(new Deadline(line[2], line[3], isDone));
                }
                else if (line[0].equals("E")) {
                    tasks.add(new Event(line[2], line[3], isDone));
                }
                else if (line[0].equals("T")){
                    tasks.add(new Todo(line[2], isDone));
                }
            }
            return tasks;
        }
        catch (FileNotFoundException e){
            ui.printIndented("Unable to locate file");
            return new ArrayList<>();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.printIndented("Invalid file contents");
            return new ArrayList<>();
        }
    }

    public static void save(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.toText() + "\n");
            }
            fileWriter.close();
        }
        catch (IOException e) {
            ui.printIndented("Error occurred while writing to file");
        }
    }
}
