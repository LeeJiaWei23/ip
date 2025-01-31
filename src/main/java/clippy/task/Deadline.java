package clippy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import clippy.ClippyException;

public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final LocalDateTime by;

    public Deadline(String description, String by) throws ClippyException {
        super(description);
        this.by = parseDate(by);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + super.description + " | " + by.format(INPUT_FORMAT);
    }

    public LocalDateTime getByDate() {
        return by;
    }
}
