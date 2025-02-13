package clippy;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import clippy.command.Command;
import clippy.parser.Parser;
import clippy.storage.Storage;
import clippy.task.Task;
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
    private final Deque<TaskList> history;

    public Clippy() {
        this.storage = new Storage();
        this.tasks = storage.load();
        this.history = new ArrayDeque<>();
        assert this.tasks != null : "Task list should not be null after loading from storage";
    }

    public String getResponse(String input) {
        Command command;
        try {
            Parser parser = new Parser(this);
            command = parser.parse(input);
            commandType = command.getClass().getSimpleName();

            // Do not save state for non-modifying commands
            if (!commandType.equals("ListCommand") && !commandType.equals("UndoCommand")) {
                saveState();
            }

            return command.execute(tasks);
        } catch (ClippyException e) {
            return UI.encloseText((e.getMessage()));
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public void saveState() {
       ArrayList<Task> copied = new ArrayList<>();
       //  need to make a deep copy since modifying a task in the original TaskList, those changes are reflected
        // in all the "copies" in your history
       for (Task task : tasks.getTasks()) {
           copied.add(task.copy());
       }
       TaskList copy = new TaskList(copied, storage);
        history.push(copy);
        System.out.println(history);
    }

    public String undo() {
        if (!history.isEmpty()) {
            tasks = history.pop();
            storage.update(tasks.getTasks());
            return "Undo successful! Task list restored";
        }
        return "Nothing to undo";
    }
}
