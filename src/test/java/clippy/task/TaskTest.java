package clippy.task;

import java.time.LocalDateTime;

import clippy.ClippyException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void markAsUndone_taskBecomesNotDone() {
        task.markAsDone();
        task.markAsUndone();
        assertTrue(task.toString().contains("[ ]"));
    }

    @Test
    void parseDate_validDateTime_success() throws ClippyException {
        LocalDateTime expected = LocalDateTime.of(2025, 2, 1, 14, 30);
        assertEquals(expected, task.parseDate("01/02/2025 1430"));
    }

    @Test
    void parseDate_invalidDateFormat_throwsException() {
        assertThrows(ClippyException.class, () -> task.parseDate("2025-02-01 14:30"));
    }

    @Test
    void parseDate_emptyString_throwsException() {
        assertThrows(ClippyException.class, () -> task.parseDate(""));
    }
}
