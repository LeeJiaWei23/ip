package clippy.task;

import java.time.LocalDate;
import java.util.ArrayList;

import clippy.ClippyException;
import clippy.command.Command;
import clippy.command.CommandType;
import clippy.storage.Storage;

import javax.sound.sampled.Clip;

/**
 * Manages the list of tasks, including adding, deleting, marking, unmarking,
 * and filtering tasks by date. The task list is updated in storage after modifications.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList with the given tasks and storage.
     *
     * @param tasks The list of tasks to manage.
     * @param storage The storage handler for saving task changes.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds a task to the list based on the specified command type and task description.
     * The task list is updated in storage after the addition.
     *
     * @param command The type of task to add (ToDo, Deadline, Event).
     * @param item The full task description, including any necessary dates or times.
     * @return A string representation of the added task.
     * @throws ClippyException If the task description is empty or incorrectly formatted.
     */
    public String addItem(CommandType command, String item) throws ClippyException {
        Task newTask = createTask(command, item);
        tasks.add(newTask);
        storage.update(tasks);
        return tasks.get(tasks.size() - 1).toString();
    }

    private Task createTask(CommandType command, String item) throws ClippyException {
        return switch (command) {
            case TODO -> createToDoTask(item);
            case DEADLINE -> createDeadlineTask(item);
            case EVENT -> createEventTask(item);
            default -> throw ClippyException.unknownCommand();
        };
    }

    private ToDo createToDoTask(String item) throws ClippyException {
        String description = item.substring(4).trim();
        if (description.isEmpty()) {
            throw ClippyException.emptyDescription("ToDo");
        }
        return new ToDo(description);
    }

    private Deadline createDeadlineTask(String item) throws ClippyException {
        String[] parts = item.substring(8).split("/by");
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw ClippyException.emptyDescription("Deadline");
        }

        if (parts.length < 2) {
            throw ClippyException.emptyTime();
        }
        String date = parts[1].trim();
        return new Deadline(description, date);
    }

    private Event createEventTask(String item) throws ClippyException {
        String[] parts = item.substring(5).split(" /from | /to ");
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw ClippyException.emptyDescription("Event");
        }

        if (parts.length < 2) {
            throw ClippyException.emptyTime();
        }
        String start = parts[1].trim();
        String end = parts[2].trim();
        return new Event(description, start, end);
    }

    private int validateIndex(String indexStr, int size) throws ClippyException {
        int index;
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw ClippyException.invalidInteger(indexStr);
        }

        if (index < 1 || index > size) {
            throw ClippyException.invalidIndex(index);
        }

        return index;
    }

    /**
     * Deletes a task from the list at the specified index.
     * The task list is updated in storage after the deletion.
     *
     * @param indexStr The index of the task to be deleted, provided as a string.
     * @return A string representation of the deleted task.
     * @throws ClippyException If the index is invalid (out of range or not a number).
     */
    public String deleteTask(String indexStr) throws ClippyException {
        int index = validateIndex(indexStr, tasks.size());
        String description = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        storage.update(tasks);
        return description;
    }

    /**
     * Updates the completion status of a task at the specified index.
     * If `isDone` is true, the task is marked as done; otherwise, it is marked as not done.
     * The task list is updated in storage after the modification.
     *
     * @param indexStr The index of the task to be updated, provided as a string.
     * @param isDone A boolean indicating whether to mark the task as done (true) or not done (false).
     * @return A string representation of the updated task.
     * @throws ClippyException If the index is invalid (out of range or not a number).
     */
    public String updateTaskStatus(String indexStr, boolean isDone) throws ClippyException {
        int index = validateIndex(indexStr, tasks.size());
        Task task = tasks.get(index - 1);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        storage.update(tasks);
        return task.toString();
    }

    public int getTaskNum() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Filters and returns a list of tasks that match the specified date.
     * The method checks deadlines and events to see if they occur on the given date.
     *
     * @param target The date to filter tasks by.
     * @return An ArrayList of tasks that match the specified date.
     */
    public ArrayList<Task> displayFilteredList(LocalDate target) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task instanceof Deadline deadlineTask) {
                if (deadlineTask.getByDate().toLocalDate().equals(target)) {
                    matchedTasks.add(deadlineTask);
                }
            } else if (task instanceof Event eventTask) {
                if (eventTask.getStart().toLocalDate().equals(target)
                        || eventTask.getEnd().toLocalDate().equals(target)) {
                    matchedTasks.add(eventTask);
                }
            }
        }
        return matchedTasks;
    }
}
