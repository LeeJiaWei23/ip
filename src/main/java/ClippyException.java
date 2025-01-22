public class ClippyException extends Exception {
    public ClippyException(String message) {
        super(message);
    }

    public static void throwEmptyDescription(String task) throws ClippyException {
        throw new ClippyException("Oh no. Please add a description for " + task + " task :(");
    }

    public static void throwInvalidIndex(int index) throws ClippyException {
        throw new ClippyException("Oh no. Task " + index + " does not exist :(");
    }

    public static void throwUnknownCommand() throws ClippyException {
        throw new ClippyException("Oh no. I do not understand this command :(");
    }

    public static void throwEmptyTime() throws ClippyException {
        throw new ClippyException("Oh no. Please add a time / date to the task :(");
    }
}