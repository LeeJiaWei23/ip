public class ByeCommand  implements Command {
    public void execute(TaskList tasks) {
        System.out.print(UI.getGoodbye());
    }

    public boolean isExit() {
        return true;
    }
}