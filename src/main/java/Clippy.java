import java.util.Scanner;

public class Clippy {
    public static void main(String[] args) {
        System.out.print(UI.getGreeting());
        Scanner reader = new Scanner(System.in);
        String input = reader.next();
        while (!input.equals("bye")) {
            System.out.print(UI.encloseText(input));
            input = reader.next();
        }
        System.out.print(UI.getGoodbye());
    }
}