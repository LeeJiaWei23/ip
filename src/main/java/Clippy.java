import java.util.Scanner;

public class Clippy {

    static String encloseText(String input) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + input + "\n" + horizontalLine;
    }

    public static void main(String[] args) {
        String greeting = """
                Hey! I'm Clippy. Your personal assistant chatbot ┌|∵|┘
                How can I help?""";
        String goodbye = "Goodbye. Look forward to our next chat!";
        System.out.print(encloseText(greeting));
        Scanner reader = new Scanner(System.in);
        String input = reader.next();
        while (!input.equals("bye")) {
            System.out.print(encloseText(input));
            input = reader.next();
        }
        System.out.print(encloseText(goodbye));
    }
}
