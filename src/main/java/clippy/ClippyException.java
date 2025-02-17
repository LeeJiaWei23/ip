package clippy;

/**
 * Represents exceptions specific to the Clippy application.
 * This exception is thrown when an invalid operation or input is encountered.
 */
public class ClippyException extends Exception {
    public ClippyException(String message) {
        super(message);
    }

    public static ClippyException emptyDescription(String task) {
        return new ClippyException("Oh no. Please add a description for " + task + " task :(");
    }

    public static ClippyException invalidIndex(int index) {
        return new ClippyException("Oh no. Task " + index + " does not exist :(");
    }

    public static ClippyException unknownCommand() {
        return new ClippyException("Oh no. I do not understand this command :(");
    }

    public static ClippyException emptyTime() {
        return new ClippyException("Oh no. Please add a time / date to the task :(");
    }

    public static ClippyException invalidInteger(String indexStr) {
        return new ClippyException("Oh no. " + indexStr + " is not a valid integer :(");
    }

    /**
     * Creates an exception indicating an invalid date/time format.
     * This exception is thrown when a user provides a date/time that does not match
     * the expected format.
     *
     * @param input The invalid date/time string provided by the user.
     * @return A {@code ClippyException} with a message explaining the correct format.
     */
    public static ClippyException invalidDateTime(String input) {
        return new ClippyException("Invalid date/time format: " + input
                + ".\n     " + "Expected: DD/MM/YYYY 2000 E.g. 01/01/2025 2000");
    }

    public static ClippyException invalidDate(String input) {
        return new ClippyException("Invalid date format: " + input
                + ".\n     " + "Expected: DD/MM/YYYY E.g. 01/01/2025 2000");
    }
}
