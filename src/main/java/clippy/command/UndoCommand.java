package clippy.command;

import clippy.Clippy;
import clippy.ClippyException;
import clippy.task.TaskList;

public class UndoCommand implements Command {
    private final Clippy clippy;

    public UndoCommand(Clippy clippy) {
        this.clippy = clippy;
    }

    /**
     * Executes the undo command by reverting the last action performed in Clippy.
     * This restores the previous state of the task list before the last modification.
     *
     * @param tasks The current task list, though it is not directly modified by this command.
     * @return A string message indicating the success undo operation.
     * @throws ClippyException If there is no previous action to undo.
     */
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
