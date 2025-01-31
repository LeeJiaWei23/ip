package clippy;

import java.util.Scanner;

import clippy.command.Command;
import clippy.parser.Parser;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.UI;

public class Clippy {
    public static void main(String[] args) {
        System.out.print(UI.getGreeting());
        Scanner reader = new Scanner(System.in);

        Storage storage = new Storage();
        TaskList taskList = storage.load();

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
