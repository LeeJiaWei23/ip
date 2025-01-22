public class DeleteCommand implements Command {
    private final String indexStr;

    public DeleteCommand(String indexStr) {
        this.indexStr = indexStr;
    }

    public void execute(TaskList tasks) throws ClippyException {
        String description = tasks.deleteTask(indexStr);
        System.out.print(UI.deleteTaskString(description, tasks.getTaskNum()));
    }

    public boolean isExit() {
        return false;
    }
}