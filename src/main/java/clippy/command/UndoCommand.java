package clippy.command;

import clippy.Clippy;
import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

public class UndoCommand implements Command {
    private final Clippy clippy;

    public UndoCommand(Clippy clippy) {
        this.clippy = clippy;
    }

    public String execute(TaskList tasks) throws ClippyException {
        return clippy.undo();
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return false, since undo does not end the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
