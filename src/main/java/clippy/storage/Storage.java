package clippy.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import clippy.ClippyException;
import clippy.task.Deadline;
import clippy.task.Event;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.task.ToDo;
import clippy.ui.UI;

/**
 * Handles the reading and writing of task data to a file for storage.
 * This class manages loading tasks from a file at startup and saving tasks upon modification.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object and initializes the file path.
     * If the file does not exist, it is created.
     */
    public Storage() {
        this.filePath = Paths.get(".", "data", "tasks.txt");
        checkFileExist();
    }

    /**
     * Checks whether the task storage file exists.
     * If the file or parent directory does not exist, they are created.
     * Displays a message indicating whether a new file was created or an existing file was loaded.
     */
    public void checkFileExist() {
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.print(UI.createFileString());
                return;
            }
            System.out.print(UI.loadedFileString());
        } catch (IOException e) {
            System.err.println("Error checking file exist: " + e.getMessage());
        }
    }

    /**
     * Parses task entries from the file and adds it to the task list.
     *
     * @param tasks The list of tasks where the parsed task will be added.
     * @param input The raw string input read from the file.
     * @throws ClippyException If the file contains an invalid task format.
     */
    private void addTaskFromFile(ArrayList<Task> tasks, String input) throws ClippyException {
        String[] data = input.split("\\|");
        String type = data[0].trim();
        String isDone = data[1].trim();
        String description = data[2].trim();

        switch (type) {
        case "T" -> tasks.add(new ToDo(description));
        case "D" -> tasks.add(new Deadline(description, data[3].trim()));
        case "E" -> {
            String[] time = data[3].split("-");
            tasks.add(new Event(description, time[0].trim(), time[1].trim()));
        }
        }

        if (isDone.equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Loads tasks from the storage file into memory.
     *
     * @return A TaskList containing all tasks loaded from the file.
     */
    public TaskList load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            List<String> inputStrings = Files.readAllLines(filePath);
            for (String input : inputStrings) {
                addTaskFromFile(tasks, input);
            }
        } catch (IOException | ClippyException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return new TaskList(tasks, this);
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be written to the file.
     */
    public void update(ArrayList<Task> tasks) {
        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }

        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.err.println("Error updating tasks: " + e.getMessage());
        }
    }
}
