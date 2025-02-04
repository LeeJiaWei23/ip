package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;

/**
 * Represents a command that can be executed within the Clippy application.
 * Implementing classes define specific actions to be performed on a task list.
 */
public interface Command {
    void execute(TaskList tasks) throws ClippyException;
    boolean isExit();
}
