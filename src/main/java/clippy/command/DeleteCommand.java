package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

/**
 * Represents a command to delete a task from the task list.
 * The command removes the task at the specified index and displays a confirmed message.
 */
public class DeleteCommand implements Command {
    private final String indexStr;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param indexStr The index of the task to be deleted, provided as a string.
     */
    public DeleteCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    /**
     * Executes the command to delete a task from the task list.
     * Displays a confirmed message after successful deletion.
     *
     * @param tasks The task list from which the task will be deleted.
     * @throws ClippyException If the index is invalid (e.g., out of bounds or not a number).
     */
    public void execute(TaskList tasks) throws ClippyException {
        String description = tasks.deleteTask(indexStr);
        System.out.print(UI.deleteTaskString(description, tasks.getTaskNum()));
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return false, since deleting a task does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
