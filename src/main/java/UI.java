public class UI {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    public static String encloseText(String input) {
        return HORIZONTAL_LINE + "     " + input.trim() + "\n" + HORIZONTAL_LINE;
    }

    public static String getGreeting() {
        return encloseText("Hey! I'm Clippy. Your personal assistant chatbot ┌|∵|┘\n" +
                "     " + "How can I help?");
    }

    public static String getGoodbye() {
        return encloseText("Goodbye. Look forward to our next chat!");
    }

    public static String markTaskString(String input) {
        return encloseText("Well Done! This task is mark as done:\n"
                + "       " + input);
    }

    public static String unmarkTaskString(String input) {
        return encloseText("OK. This task is mark as not done:\n"
                + "       "  + input);
    }
}