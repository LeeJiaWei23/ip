import java.util.Scanner;

public class Clippy {
    public static void main(String[] args) {
        System.out.print(UI.getGreeting());
        Scanner reader = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            if (!reader.hasNextLine()) {
                break;
            }
            String input = reader.nextLine();
            Command command;
            try {
                command = Parser.parse(input);
                command.execute(taskList);
                if (command.isExit()) {
                    break;
                }
            } catch (ClippyException e) {
                System.out.print(UI.encloseText(e.getMessage()));
            }
        }
        reader.close();
    }
}