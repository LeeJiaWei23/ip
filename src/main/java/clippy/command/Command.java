package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;

public interface Command {
    void execute(TaskList tasks) throws ClippyException;
    boolean isExit();
}
