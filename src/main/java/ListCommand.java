public class ListCommand implements Command {
    public void execute(TaskList tasks) {
        System.out.print(UI.displayListString(tasks.getTasks()));
    }

    public boolean isExit() {
        return false;
    }
}