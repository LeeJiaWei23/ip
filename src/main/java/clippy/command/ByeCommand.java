package clippy.command;

import clippy.task.TaskList;
import clippy.ui.UI;

import javafx.application.Platform;

/**
 * Represents a command to terminate the program.
 * Displays a goodbye message before exiting.
 */
public class ByeCommand implements Command {

    /**
     * Executes the command to display a goodbye message.
     *
     * @param tasks The task list, which remains unchanged.
     */
    public String execute(TaskList tasks) {
        String goodbyeMessage = UI.getGoodbye();

        Platform.exit();
        System.exit(0);
        return goodbyeMessage;
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return true, since this command signals the end of the program.
     */
    public boolean isExit() {
        return true;
    }
}
