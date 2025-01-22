public class Parser {
    public static Command parse(String input) throws ClippyException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            String taskIndex = input.split(" ")[1];
            return new MarkCommand(taskIndex);
        } else if (input.startsWith("unmark ")) {
            String taskIndex = input.split(" ")[1];
            return new UnmarkCommand(taskIndex);
        } else if (input.startsWith("delete ")) {
            String taskIndex = input.split(" ")[1];
            return new DeleteCommand(taskIndex);
        } else {
            return new AddCommand(input);
        }
    }
}
