package clippy.parser;

import clippy.ClippyException;
import clippy.command.AddCommand;
import clippy.command.ListCommand;
import clippy.command.Command;
import clippy.command.MarkCommand;
import clippy.command.UnmarkCommand;
import clippy.command.DeleteCommand;
import clippy.command.CommandType;
import clippy.command.ByeCommand;
import clippy.command.FindCommand;

public class Parser {
    public static Command parse(String input) throws ClippyException {
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
            case FIND -> new FindCommand(arguments);
        };
    }

    private static CommandType getCommandType(String command) throws ClippyException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw ClippyException.unknownCommand();
        }
    }
}