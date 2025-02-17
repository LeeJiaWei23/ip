package clippy.parser;

import clippy.Clippy;
import clippy.ClippyException;
import clippy.command.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private Parser parser;
    private Clippy clippy;

    @BeforeEach
    void setUp() {
        clippy = new Clippy();
        parser = new Parser(clippy);
    }

    @Test
    void parse_listCommand_returnsListCommand() throws ClippyException {
        Command command = parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parse_undoCommand_returnsUndoCommand() throws ClippyException {
        Command command = parser.parse("undo");
        assertInstanceOf(UndoCommand.class, command);
    }

    @Test
    void parse_byeCommand_returnsByeCommand() throws ClippyException {
        Command command = parser.parse("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    void parse_markCommand_validArgument_returnsMarkCommand() throws ClippyException {
        Command command = parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parse_unmarkCommand_validArgument_returnsUnmarkCommand() throws ClippyException {
        Command command = parser.parse("unmark 3");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void parse_deleteCommand_validArgument_returnsDeleteCommand() throws ClippyException {
        Command command = parser.parse("delete 5");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parse_todoCommand_validDescription_returnsAddCommand() throws ClippyException {
        Command command = parser.parse("todo Read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_deadlineCommand_validDescription_returnsAddCommand() throws ClippyException {
        Command command = parser.parse("deadline Submit report /by 2024-03-10");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_eventCommand_validDescription_returnsAddCommand() throws ClippyException {
        Command command = parser.parse("event Meeting /from 2024-02-20 /to 2024-02-21");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_findCommand_validKeyword_returnsFindCommand() throws ClippyException {
        Command command = parser.parse("find books");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    void parse_filterCommand_validDate_returnsFilterCommand() throws ClippyException {
        Command command = parser.parse("filter 2024-03-10");
        assertInstanceOf(FilterCommand.class, command);
    }

    @Test
    void parse_invalidCommand_throwsException() {
        assertThrows(ClippyException.class, () -> parser.parse("invalidCommand"));
    }

    @Test
    void parse_emptyCommand_throwsException() {
        assertThrows(ClippyException.class, () -> parser.parse(""));
    }
}
