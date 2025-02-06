package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

/**
 * Represents a command to unmark a task as not done in the task list.
 * The command updates the specified task's status and displays a confirmed message.
 */
public class UnmarkCommand implements Command {
    private final String indexStr;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param indexStr The index of the task to be unmarked as not done, provided as a string.
     */
    public UnmarkCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    /**
     * Executes the command to unmark a task as not done in the task list.
     * Displays a confirmation message after successful unmarking.
     *
     * @param tasks The task list containing the task to be unmarked.
     * @throws ClippyException If the index is invalid (e.g., out of bounds or not a number).
     */
    public String execute(TaskList tasks) throws ClippyException {
        String description = tasks.unmarkTask(indexStr);
        return UI.unmarkTaskString(description);
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return false, since unmarking a task does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
