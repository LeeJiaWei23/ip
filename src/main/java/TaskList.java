import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String addItem(CommandType command, String item) throws ClippyException {
        switch (command) {
            case TODO -> {
                String description = item.substring(4).trim();
                if (description.isEmpty()) {
                    throw ClippyException.emptyDescription("ToDo");
                }
                tasks.add(new ToDo(description));
            }
            case DEADLINE -> {
                String text = item.substring(8);
                String[] parts = text.split("/by");
                String description = parts[0].trim();
                if (description.isEmpty()) {
                    throw ClippyException.emptyDescription("Deadline");
                }
                String date = parts[1].trim();
                if (date.isEmpty()) {
                    throw ClippyException.emptyTime();
                }
                tasks.add(new Deadline(description, date));
            }
            case EVENT -> {
                String text = item.substring(5);
                String[] parts = text.split(" /from | /to ");
                String description = parts[0].trim();
                if (description.isEmpty()) {
                    throw ClippyException.emptyDescription("Event");
                }
                String start = parts[1].trim();
                String end = parts[2].trim();
                if (start.isEmpty() || end.isEmpty()) {
                    throw ClippyException.emptyTime();
                }
                tasks.add(new Event(description, start, end));
            }
        }
        return tasks.get(tasks.size() - 1).toString();
    }

    private int validateIndex(String indexStr, int size) throws ClippyException {
        int index;
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw ClippyException.invalidInteger(indexStr);
        }

        if (index < 1 || index > size) {
            throw ClippyException.invalidIndex(index);
        }

        return index;
    }

    public String deleteTask(String indexStr) throws ClippyException {
        int index = validateIndex(indexStr, tasks.size());
        String description = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        return description;
    }

    public String markTask(String indexStr) throws ClippyException {
        int index = validateIndex(indexStr, tasks.size());
        tasks.get(index - 1).markAsDone();
        return tasks.get(index - 1).toString();
    }

    public String unmarkTask(String indexStr) throws ClippyException {
        int index = validateIndex(indexStr, tasks.size());
        tasks.get(index - 1).markAsUndone();
        return tasks.get(index - 1).toString();
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

    public int getTaskNum() {
        return tasks.size();
    }
}