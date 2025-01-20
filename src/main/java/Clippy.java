import java.util.Scanner;

public class Clippy {
    public static void main(String[] args) {
        System.out.print(UI.getGreeting());
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        List list = new List();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.print(UI.encloseText(list.displayList()));
            } else {
                list.addItem(input);
                String res = "added: " +input;
                System.out.print(UI.encloseText(res));
            }
            input = reader.nextLine();
        }
        System.out.print(UI.getGoodbye());
    }
}