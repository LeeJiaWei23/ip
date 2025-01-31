package clippy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import clippy.ClippyException;

/**
 * Represents an abstract task with a description and completion status.
 * This class provides common functionality for different types of tasks.
 */
public abstract class Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task in file format.
     *
     * @return A formatted string suitable for saving the task to a file.
     */
    public abstract String toFileFormat();

    /**
     * Parses a date string into a LocalDateTime object.
     * The expected input format is "dd/MM/yyyy HHmm".
     *
     * @param input The date string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws ClippyException If the date format is invalid.
     */
    public LocalDateTime parseDate(String input) throws ClippyException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw ClippyException.invalidDateTime(input);
        }
    }
}
