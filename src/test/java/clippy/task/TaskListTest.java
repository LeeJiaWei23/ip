package clippy.task;

import java.util.ArrayList;

import clippy.command.CommandType;
import clippy.ClippyException;
import clippy.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    void setUp() {
        tasks = new TaskList(new ArrayList<>(), new Storage());
    }

    @Test
    void addItem_validToDo_success() throws ClippyException {
        tasks.addItem(CommandType.TODO, "todo Read book");
        assertEquals(1, tasks.getTaskNum());
        assertInstanceOf(ToDo.class, tasks.getTasks().get(0));
    }

    @Test
    void addItem_emptyToDoDescription_throwsException() {
        assertThrows(ClippyException.class, () -> tasks.addItem(CommandType.TODO, "todo"));
    }

    @Test
    void addItem_validDeadline_success() throws ClippyException {
        tasks.addItem(CommandType.DEADLINE, "deadline Submit assignment /by 11/11/2024 1400");
        assertEquals(1, tasks.getTaskNum());
        assertInstanceOf(Deadline.class, tasks.getTasks().get(0));
    }

    @Test
    void addItem_invalidDeadlineFormat_throwsException() {
        assertThrows(ClippyException.class, () -> tasks.addItem(CommandType.DEADLINE,
                "deadline Submit assignment"));
    }

    @Test
    void addItem_validEvent_success() throws ClippyException {
        tasks.addItem(CommandType.EVENT, "event Project meeting /from 11/11/2024 1300 /to 12/11/2024 0900");
        assertEquals(1, tasks.getTaskNum());
        assertInstanceOf(Event.class, tasks.getTasks().get(0));
    }

    @Test
    void addItem_invalidEventFormat_throwsException() {
        assertThrows(ClippyException.class, () -> tasks.addItem(CommandType.EVENT,
                "event Team meeting /from 11/11/2024 1300 /to 11-11-2024 1300"));
    }

    @Test
    void deleteTask_validIndex_success() throws ClippyException {
        tasks.addItem(CommandType.TODO, "todo Buy groceries");
        String deletedTask = tasks.deleteTask("1");
        assertEquals(0, tasks.getTaskNum());
        assertTrue(deletedTask.contains("Buy groceries"));
    }

    @Test
    void updateTaskStatus_markAsDone_success() throws ClippyException {
        tasks.addItem(CommandType.TODO, "todo Exercise");
        String updatedTask = tasks.updateTaskStatus("1", true);
        assertTrue(updatedTask.contains("[X]"));
    }

    @Test
    void updateTaskStatus_markAsUndone_success() throws ClippyException {
        tasks.addItem(CommandType.TODO, "todo Exercise");
        tasks.updateTaskStatus("1", true);
        String updatedTask = tasks.updateTaskStatus("1", false);
        assertTrue(updatedTask.contains("[ ]"));
    }
}
