public class ClippyException extends Exception {
    public ClippyException(String message) {
        super(message);
    }

    public static ClippyException emptyDescription(String task) throws ClippyException {
        return new ClippyException("Oh no. Please add a description for " + task + " task :(");
    }

    public static ClippyException invalidIndex(int index) throws ClippyException {
        return new ClippyException("Oh no. Task " + index + " does not exist :(");
    }

    public static ClippyException unknownCommand() throws ClippyException {
        return new ClippyException("Oh no. I do not understand this command :(");
    }

    public static ClippyException emptyTime() throws ClippyException {
        return new ClippyException("Oh no. Please add a time / date to the task :(");
    }

    public static ClippyException invalidInteger(String indexStr) throws ClippyException {
        return new ClippyException("Oh no. " + indexStr + " is not a valid integer :(");
    }

    public static ClippyException invalidDateTime(String input) {
        return new ClippyException("Invalid date/time format: " + input + ". Expected: 07/03/2020 2000.");
    }
}