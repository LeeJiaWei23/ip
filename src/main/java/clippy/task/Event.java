package clippy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import clippy.ClippyException;

/**
 * Represents a task that is an event.
 * An event task has a description, a start time, and an end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start date and time in the format "dd/MM/yyyy HHmm".
     * @param end The end date and time in the format "dd/MM/yyyy HHmm".
     * @throws ClippyException If the date format is invalid.
     */
    public Event(String description, String start, String end) throws ClippyException {
        super(description);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: "
                + end.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the string representation of the event in file format.
     *
     * @return A formatted string suitable for saving the task to a file.
     */
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + super.description + " | "
                + start.format(INPUT_FORMAT) + " - " + end.format(INPUT_FORMAT);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Task copy() {
        Event copy = new Event(this.description, this.start, this.end);
        if (this.isDone) {
            copy.markAsDone();
        }
        return copy;
    }
}
