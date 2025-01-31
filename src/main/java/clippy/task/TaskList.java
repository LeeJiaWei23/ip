package clippy.task;

import java.time.LocalDate;
import java.util.ArrayList;

import clippy.ClippyException;
import clippy.command.CommandType;
import clippy.storage.Storage;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
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

            if (parts.length < 2) {
                throw ClippyException.emptyTime();
            }
            String date = parts[1].trim();
            tasks.add(new Deadline(description, date));
        }
        case EVENT -> {
            String text = item.substring(5);
            String[] parts = text.split(" /from | /to ");
            String description = parts[0].trim();
            if (description.isEmpty()) {
                throw ClippyException.emptyDescription("Event");
            }

            if (parts.length < 2) {
                throw ClippyException.emptyTime();
            }
            String start = parts[1].trim();
            String end = parts[2].trim();
            tasks.add(new Event(description, start, end));
        }
        }
        storage.update(tasks);
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
        storage.update(tasks);
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

    public int getTaskNum() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> displayFilteredList(LocalDate target) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task instanceof Deadline deadlineTask) {
                if (deadlineTask.getByDate().toLocalDate().equals(target)) {
                    matchedTasks.add(deadlineTask);
                }
            } else if (task instanceof Event eventTask) {
                if (eventTask.getStart().toLocalDate().equals(target)
                        || eventTask.getEnd().toLocalDate().equals(target)) {
                    matchedTasks.add(eventTask);
                }
            }
        }
        return matchedTasks;
    }
}
