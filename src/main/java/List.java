public class List {
    private final Task[] tasks;
    private static final int MAX_ITEMS = 100;
    private int currentIndex = 0;

    public List() {
        tasks = new Task[MAX_ITEMS];
    }

    public void addItem(String item) {
        if (item.startsWith("todo ")) {
            String description = item.substring(5);
            tasks[currentIndex] = new ToDo(description);
        } else if (item.startsWith("deadline ")) {
            String text = item.substring(9);
            String[] parts = text.split("/by");
            String description = parts[0];
            String date = parts[1].trim();
            tasks[currentIndex] = new Deadline(description, date);
        } else if (item.startsWith("event ")) {
           String text = item.substring(6);
           String[] parts = text.split(" /from | /to ");
           String description = parts[0];
           String start = parts[1];
           String end = parts[2];
           tasks[currentIndex] = new Event(description, start, end);
        }
        currentIndex++;
    }

    public void markTask(int index) {
        tasks[index - 1].markAsDone();
    }

    public void unmarkTask(int index) {
        tasks[index - 1].markAsUndone();
    }

    public String displayList() {
        StringBuilder result = new StringBuilder("Current Tasks in Your List:\n");
        for (int i = 0; i < currentIndex; i++) {
            result.append("     ")
                  .append(i + 1).append(".")
                  .append(tasks[i].toString()).append("\n");
        }
        return result.toString();
    }

    public String getTaskDescription(int index) {
        return tasks[index - 1].toString();
    }

    public int getTaskNum() {
        return currentIndex;
    }
}