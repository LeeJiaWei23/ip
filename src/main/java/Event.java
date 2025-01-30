import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event  extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    Event(String description, String start, String end) throws ClippyException {
        super(description);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: "
                + end.format(OUTPUT_FORMAT) + ")";
    }

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
}