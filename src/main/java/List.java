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
        } else {
            tasks[currentIndex] = new Task(item);
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