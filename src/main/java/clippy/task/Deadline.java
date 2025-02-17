package clippy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import clippy.ClippyException;

/**
 * Represents a task with a deadline.
 * A deadline task has a description and a due date and time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date and time in the format "dd/MM/yyyy HHmm".
     * @throws ClippyException If the date format is invalid.
     */
    public Deadline(String description, String by) throws ClippyException {
        super(description);
        this.by = parseDate(by);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the string representation of the task in file format.
     *
     * @return A formatted string suitable for saving the task to a file.
     */
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + super.description + " | " + by.format(INPUT_FORMAT);
    }

    public LocalDateTime getByDate() {
        return by;
    }

    @Override
    public Task copy() {
        Deadline copy = new Deadline(this.description, this.by);
        if (this.isDone) {
            copy.markAsDone();
        }
        return copy;
    }
}
