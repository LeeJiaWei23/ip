package clippy.task;

/**
 * Represents a simple ToDo task without any date or time constraints.
 * A ToDo task only contains a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the ToDo task in file format.
     *
     * @return A formatted string suitable for saving the task to a file.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + super.description;
    }

    @Override
    public Task copy() {
        ToDo copy = new ToDo(this.description);
        if (this.isDone) {
            copy.markAsDone();
        }
        return copy;
    }
}
