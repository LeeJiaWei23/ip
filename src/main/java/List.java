import java.util.ArrayList;

public class List {
    private final ArrayList<Task> tasks;

    public List() {
        tasks = new ArrayList<>();
    }

    public void addItem(String item) throws ClippyException {
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
    }

    public void markTask(int index) throws ClippyException {
        if (index < 1 || index > tasks.size()) {
            ClippyException.throwInvalidIndex(index);
        }
        tasks.get(index - 1).markAsDone();
    }

    public void unmarkTask(int index) throws ClippyException {
        if (index < 1 || index > tasks.size()) {
            ClippyException.throwInvalidIndex(index);
        }
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