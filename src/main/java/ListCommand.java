public class ListCommand implements Command {
    public void execute(TaskList tasks) {
        String result = tasks.displayList();
        System.out.print(UI.encloseText(result));
    }

    public boolean isExit() {
        return false;
    }
}