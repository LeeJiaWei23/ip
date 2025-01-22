public class AddCommand implements Command {
    private final String item;

    public AddCommand(String item) {
        this.item = item;
    }

    public void execute(TaskList tasks) throws ClippyException {
        String description = tasks.addItem(item);
        System.out.print(UI.addTaskString(description, tasks.getTaskNum()));
    }

    public boolean isExit() {
        return false;
    }
}
