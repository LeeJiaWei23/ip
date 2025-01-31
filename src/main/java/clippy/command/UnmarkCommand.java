package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

public class UnmarkCommand implements Command {
    private final String indexStr;

    public UnmarkCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    public void execute(TaskList tasks) throws ClippyException {
        String description = tasks.unmarkTask(indexStr);
        System.out.print(UI.unmarkTaskString(description));
    }

    public boolean isExit() {
        return false;
    }
}
