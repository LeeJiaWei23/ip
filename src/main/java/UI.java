public class UI {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private static final String GREETING = "Hey! I'm Clippy. Your personal assistant chatbot ┌|∵|┘\n" +
            "     " + "How can I help?";
    private static final String GOODBYE = "Goodbye. Look forward to our next chat!";

    public static String encloseText(String input) {
        return HORIZONTAL_LINE + "     " + input + "\n" + HORIZONTAL_LINE;
    }

    public static String getGreeting() {
        return encloseText(GREETING);
    }

    public static String getGoodbye() {
        return encloseText(GOODBYE);
    }
}