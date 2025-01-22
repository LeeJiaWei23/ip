public class AddCommand implements Command {
    private final String item;
    private final CommandType command;

    public AddCommand(CommandType command, String item) {
        this.command = command;
        this.item = item;
    }

    public void execute(TaskList tasks) throws ClippyException {
        String description = tasks.addItem(command, item);
        System.out.print(UI.addTaskString(description, tasks.getTaskNum()));
    }

    public boolean isExit() {
        return false;
    }
}
