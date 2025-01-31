package clippy.command;

import clippy.task.TaskList;
import clippy.ui.UI;

public class ByeCommand implements Command {
    public void execute(TaskList tasks) {
        System.out.print(UI.getGoodbye());
    }

    public boolean isExit() {
        return true;
    }
}
