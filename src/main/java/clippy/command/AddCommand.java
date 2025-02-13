package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

/**
 * Represents a command to add a task to the task list.
 * Supports adding different tasks such as ToDo, Deadline, and Event.
 */
public class AddCommand implements Command {
    private final String item;
    private final CommandType command;

    /**
     * Constructs an AddCommand with that command type and task description.
     *
     * @param command The type of command, determining the type of task to add.
     * @param item The description of the task to be added.
     */
    public AddCommand(CommandType command, String item) {
        this.command = command;
        this.item = item;
    }

    /**
     * Executes the command to add a task to the provided task list.
     * Displays a confirmed message to the user.
     *
     * @param tasks The task list to which the task should be added.
     * @throws ClippyException If the task description is invalid or improperly formatted.
     */
    public String execute(TaskList tasks) throws ClippyException {

        String description = tasks.addItem(command, item);
        return UI.addTaskString(description, tasks.getTaskNum());
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return false, since adding a task does not terminate the program.
     */
    public boolean isExit() {
        return false;
    }
}
