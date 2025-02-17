package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

/**
 * Represents a command to mark a task as done in the task list.
 * The command updates the specified task's status and displays a confirmed message.
 */
public class MarkCommand implements Command {
    private final String indexStr;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param indexStr The index of the task to be marked as done, provided as a string.
     */
    public MarkCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     * Displays a confirmation message after successful marking.
     *
     * @param tasks The task list containing the task to be marked as done.
     * @throws ClippyException If the index is invalid (e.g., out of bounds or not a number).
     */
    public String execute(TaskList tasks) throws ClippyException {
        String description = tasks.updateTaskStatus(indexStr, true);
        return UI.markTaskString(description);
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return false, since marking a task does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
