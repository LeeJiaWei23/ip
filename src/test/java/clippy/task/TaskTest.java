package clippy.task;

import clippy.ClippyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new ToDo("Sell Books");
    }

    @Test
    void getStatusIcon_notDone_returnsEmptyBracket() {
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    void getStatusIcon_markAsDone_returnsX() {
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void parseDate_validDateTime_success() throws ClippyException {
        LocalDateTime expected = LocalDateTime.of(2025, 2, 1, 14, 30);
        assertEquals(expected, task.parseDate("01/02/2025 1430"));
    }
}
