import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UI {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    public static String encloseText(String input) {
        return HORIZONTAL_LINE + "     " + input.trim() + "\n" + HORIZONTAL_LINE;
    }

    public static String getGreeting() {
        return encloseText("Hey! I'm Clippy. Your personal assistant chatbot\n" +
                "     " + "How can I help?");
    }

    public static String getGoodbye() {
        return encloseText("Goodbye. Look forward to our next chat!");
    }

    public static String markTaskString(String input) {
        return encloseText("Well Done! This task is mark as done:\n"
                + "       " + input);
    }

    public static String unmarkTaskString(String input) {
        return encloseText("OK. This task is mark as not done:\n"
                + "       "  + input);
    }

    public static String addTaskString(String input, int taskNum) {
        String template = "Got it. This task will be added to the list:\n"
                + "       %s\n"
                + "     Now you have %d %s in the list.";
        String taskOrTasks = (taskNum == 1 ? "task" : "tasks");
        return encloseText(String.format(template, input, taskNum, taskOrTasks));
    }

    public static String deleteTaskString(String input, int taskNum) {
        String template = "Got it. This task will be removed from the list:\n"
                + "       %s\n"
                + "     Now you have %d %s in the list.";
        String taskOrTasks = (taskNum == 1 ? "task" : "tasks");
        return encloseText(String.format(template, input, taskNum, taskOrTasks));
    }

    public static String loadedFileString() {
        return encloseText("Loaded saved list from ./data/tasks.txt");
    }

    public static String createFileString() {
        return encloseText("Created new saved list at ./data/tasks.txt");
    }

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
}