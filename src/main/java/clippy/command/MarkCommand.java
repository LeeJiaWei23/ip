package clippy.command;

import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

public class MarkCommand implements Command {
    private final String indexStr;

    public MarkCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    public void execute(TaskList tasks) throws ClippyException {
        String description = tasks.markTask(indexStr);
        System.out.print(UI.markTaskString(description));
    }

    public boolean isExit() {
        return false;
    }
}