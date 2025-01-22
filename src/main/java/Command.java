public interface Command {
    void execute(TaskList tasks) throws ClippyException;
    boolean isExit();
}