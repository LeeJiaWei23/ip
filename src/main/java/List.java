import java.util.ArrayList;

public class List {
    private final ArrayList<Task> tasks;

    public List() {
        tasks = new ArrayList<>();
    }

    public void addItem(String item) {
        if (item.startsWith("todo ")) {
            String description = item.substring(5);
            tasks.add(new ToDo(description));
        } else if (item.startsWith("deadline ")) {
            String text = item.substring(9);
            String[] parts = text.split("/by");
            String description = parts[0];
            String date = parts[1].trim();
            tasks.add(new Deadline(description, date));
        } else if (item.startsWith("event ")) {
           String text = item.substring(6);
           String[] parts = text.split(" /from | /to ");
           String description = parts[0];
           String start = parts[1];
           String end = parts[2];
           tasks.add(new Event(description, start, end));
        }
    }

    public void markTask(int index) {
        tasks.get(index - 1).markAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index - 1).markAsUndone();
    }

    public String displayList() {
        StringBuilder result = new StringBuilder("Current Tasks in Your List:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("     ")
                  .append(i + 1).append(".")
                  .append(tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }

    public String getTaskDescription(int index) {
        return tasks.get(index - 1).toString();
    }

    public int getTaskNum() {
        return tasks.size();
    }
}