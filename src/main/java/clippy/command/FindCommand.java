package clippy.command;

import java.util.ArrayList;

import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.UI;

/**
 * Represents a command that searches for tasks containing a specified keyword in their description.
 * The search is case-insensitive and matches partial words.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find and display tasks that match the given keyword.
     *
     * @param tasks The task list to search within.
     */
    public String execute(TaskList tasks) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return UI.findTaskString(matchingTasks);
    }

    /**
     * Determines whether this command should cause the program to exit.
     *
     * @return false, since finding a task does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
