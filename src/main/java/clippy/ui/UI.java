package clippy.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import clippy.task.Task;

/**
 * Handles user interface-related operations in the Clippy application.
 * This class provides methods to format and display messages in a consistent format.
 */
public class UI {
    private static final String HORIZONTAL_LINE = "    _______________________________________________________\n";

    /**
     * Encloses a given text message within a horizontal border.
     *
     * @param input The text to be enclosed.
     * @return The formatted string with borders.
     */
    public static String encloseText(String input) {
        return HORIZONTAL_LINE + "     " + input.trim() + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Returns the greeting message displayed when the application starts.
     *
     * @return The formatted greeting message.
     */
    public static String getGreeting() {
        return encloseText("Hey! I'm Clippy. Your personal assistant chatbot\n"
                + "     " + "How can I help?");
    }

    /**
     * Returns the goodbye message displayed when the application exits.
     *
     * @return The formatted goodbye message.
     */
    public static String getGoodbye() {
        return encloseText("Goodbye. Look forward to our next chat!");
    }

    /**
     * Returns a formatted string displaying all tasks in the task list.
     *
     * @param tasks The list of tasks to display.
     * @return The formatted task list string.
     */
    public static String displayListString(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder("Current Tasks in Your List:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("     ")
                    .append(i + 1).append(".")
                    .append(tasks.get(i).toString()).append("\n");
        }
        return encloseText(result.toString());
    }

    /**
     * Returns a formatted message indicating a task has been marked as done.
     *
     * @param input The task description.
     * @return The formatted confirmation message.
     */
    public static String markTaskString(String input) {
        return encloseText("Well Done! This task is mark as done:\n"
                + "       " + input);
    }

    /**
     * Returns a formatted message indicating a task has been unmarked as done.
     *
     * @param input The task description.
     * @return The formatted confirmation message.
     */
    public static String unmarkTaskString(String input) {
        return encloseText("OK. This task is mark as not done:\n"
                + "       " + input);
    }

    /**
     * Returns a formatted message indicating a task has been added.
     *
     * @param input   The task description.
     * @param taskNum The total number of tasks in the list after addition.
     * @return The formatted task addition confirmation message.
     */
    public static String addTaskString(String input, int taskNum) {
        String template = "Got it. This task will be added to the list:\n"
                + "       %s\n"
                + "     Now you have %d %s in the list.";
        String taskOrTasks = (taskNum == 1 ? "task" : "tasks");
        return encloseText(String.format(template, input, taskNum, taskOrTasks));
    }

    /**
     * Returns a formatted message indicating a task has been deleted.
     *
     * @param input   The task description.
     * @param taskNum The total number of tasks remaining in the list.
     * @return The formatted task deletion confirmation message.
     */
    public static String deleteTaskString(String input, int taskNum) {
        String template = "Got it. This task will be removed from the list:\n"
                + "       %s\n"
                + "     Now you have %d %s in the list.";
        String taskOrTasks = (taskNum == 1 ? "task" : "tasks");
        return encloseText(String.format(template, input, taskNum, taskOrTasks));
    }

    /**
     * Returns a formatted message indicating that a saved task list has been loaded.
     *
     * @return The formatted message confirming file loading.
     */
    public static String loadedFileString() {
        return encloseText("Loaded saved list from ./data/tasks.txt");
    }

    /**
     * Returns a formatted message indicating that a new task list has been created.
     *
     * @return The formatted message confirming file creation.
     */
    public static String createFileString() {
        return encloseText("Created new saved list at ./data/tasks.txt");
    }

    /**
     * Returns a formatted message displaying tasks filtered by a specific date.
     *
     * @param tasks  The list of tasks that match the specified date.
     * @param target The date used for filtering.
     * @return The formatted filtered task list.
     */
    public static String filteredTaskString(ArrayList<Task> tasks, LocalDate target) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        if (tasks.isEmpty()) {
            return encloseText("No tasks found for " + target.format(outputFormat));
        }

        StringBuilder result = new StringBuilder("Tasks on " + target.format(outputFormat) + "\n");

        for (int i = 0; i < tasks.size(); i++) {
            result.append("     ")
                    .append(i + 1).append(".")
                    .append(tasks.get(i).toString()).append("\n");
        }
        return encloseText(result.toString());
    }

    /**
     * Returns a formatted message displaying tasks that match a search query.
     *
     * @param tasks The list of tasks that match the search.
     * @return The formatted search result message.
     */
    public static String findTaskString(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return encloseText("No task matching task in list");
        }

        StringBuilder result = new StringBuilder("Tasks that match your search in your list\n");

        for (int i = 0; i < tasks.size(); i++) {
            result.append("     ")
                    .append(i + 1).append(".")
                    .append(tasks.get(i).toString()).append("\n");
        }
        return encloseText(result.toString());
    }

    public static String undoTaskString(String description) {
        String template = "The previous command with task has been undo:\n"
                + "       %s\n";
        return encloseText(String.format(template, description));
    }
}
