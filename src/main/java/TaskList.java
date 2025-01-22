import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String addItem(String item) throws ClippyException {
        if (item.startsWith("todo")) {
            String description = item.substring(4).trim();
            if (description.isEmpty()) {
                ClippyException.throwEmptyDescription("ToDo");
            }
            tasks.add(new ToDo(description));
        } else if (item.startsWith("deadline")) {
            String text = item.substring(8);
            String[] parts = text.split("/by");
            String description = parts[0].trim();
            if (description.isEmpty()){
                ClippyException.throwEmptyDescription("Deadline");
            }
            String date = parts[1].trim();
            if (date.isEmpty()) {
                ClippyException.throwEmptyTime();
            }
            tasks.add(new Deadline(description, date));
        } else if (item.startsWith("event")) {
           String text = item.substring(5);
           String[] parts = text.split(" /from | /to ");
           String description = parts[0].trim();
           if (description.isEmpty()) {
               ClippyException.throwEmptyDescription("Event");
           }
           String start = parts[1].trim();
           String end = parts[2].trim();
           if (start.isEmpty() || end.isEmpty()) {
               ClippyException.throwEmptyTime();
           }
           tasks.add(new Event(description, start, end));
        } else {
            ClippyException.throwUnknownCommand();
        }
        return tasks.get(tasks.size() - 1).toString();
    }

    private int validateIndex(String indexStr, int size) throws ClippyException {
        int index;
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            ClippyException.throwInvalidInteger(indexStr);
            return -1;
        }

        if (index < 1 || index > size) {
            ClippyException.throwInvalidIndex(index);
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