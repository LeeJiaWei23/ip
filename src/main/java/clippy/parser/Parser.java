package clippy.parser;

import clippy.Clippy;
import clippy.ClippyException;
import clippy.command.*;

/**
 * Handles the parsing of user input and converts it to its corresponding command objects.
 * The parser identifies the command type and provides arguments before creating the command.
 */
public class Parser {
    private final Clippy clippy;

    public Parser(Clippy clippy) {
        this.clippy = clippy;
    }

     /* Parses the user input and returns the corresponding command.
     *
     * @param input The raw input string entered by the user.
     * @return A Command object representing the parsed user input.
     * @throws ClippyException If the input does not match any valid command format.
     */
    public Command parse(String input) throws ClippyException {
        String[] words = input.split(" ");
        String arguments = (words.length < 2 ? "" : words[1]);
        CommandType commandType = getCommandType(words[0]);

        return switch (commandType) {
        case LIST -> new ListCommand();
        case MARK -> new MarkCommand(arguments);
        case UNMARK -> new UnmarkCommand(arguments);
        case DELETE -> new DeleteCommand(arguments);
        case TODO, DEADLINE, EVENT -> new AddCommand(commandType, input);
        case BYE -> new ByeCommand();
        case FILTER -> new FilterCommand(arguments);
        case FIND -> new FindCommand(arguments);
        case UNDO -> new UndoCommand(clippy);
        };
    }

    /**
     * Identifies the CommandType from the given command string.
     *
     * @param command The command keyword extracted from the user's input.
     * @return The corresponding CommandType enum value.
     * @throws ClippyException If the command is not recognized.
     */
    private static CommandType getCommandType(String command) throws ClippyException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw ClippyException.unknownCommand();
        }
    }
}
