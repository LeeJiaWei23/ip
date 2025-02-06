package clippy.command;

import clippy.task.TaskList;
import clippy.ui.UI;

/**
 * Represents a command to display all tasks in the task list.
 * The command retrieves the list of tasks and prints them to the user.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to display all tasks currently stored in the task list.
     * The tasks are formatted and displayed using the UI component.
     *
     * @param tasks The task list containing the tasks to be displayed.
     */
    public String execute(TaskList tasks) {
        return UI.displayListString(tasks.getTasks());
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return false, since listing tasks does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
