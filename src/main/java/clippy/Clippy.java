package clippy;

import java.util.Scanner;

import clippy.command.Command;
import clippy.parser.Parser;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.UI;

/**
 * Runs the Clippy application.
 * Clippy is a task management assistant that allows users to manage tasks via a command-line interface.
 * The application starts by loading stored tasks and then processes user commands in a loop
 * until an exit command is given.
 */
public class Clippy {
    private final Storage storage;
    private TaskList tasks;
    private String commandType;

    public Clippy() {
        this.storage = new Storage();
        this.tasks = storage.load();
    }

    public String getResponse(String input) {
        Command command;
        try {
            command = Parser.parse(input);
            commandType = command.getClass().getSimpleName();
            return command.execute(tasks);
        } catch (ClippyException e) {
            return UI.encloseText((e.getMessage()));
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
