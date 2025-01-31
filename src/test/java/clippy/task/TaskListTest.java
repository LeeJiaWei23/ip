package clippy.task;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clippy.command.CommandType;
import clippy.ClippyException;
import clippy.storage.Storage;

import static org.junit.jupiter.api.Assertions.*;

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
}
