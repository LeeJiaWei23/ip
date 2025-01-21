import java.util.Scanner;

public class Clippy {
    public static void main(String[] args) {
        System.out.print(UI.getGreeting());
        Scanner reader = new Scanner(System.in);
        List list = new List();
        while (reader.hasNextLine()) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                System.out.print(UI.encloseText(list.displayList()));
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                list.markTask(taskIndex);
                System.out.print(UI.markTaskString(list.getTaskDescription(taskIndex)));
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                list.unmarkTask(taskIndex);
                System.out.print(UI.unmarkTaskString(list.getTaskDescription(taskIndex)));
            } else {
                list.addItem(input);
                int taskNum = list.getTaskNum();
                System.out.print(UI.addTaskString(list.getTaskDescription(taskNum), taskNum));
            }
        }
        System.out.print(UI.getGoodbye());
        reader.close();
    }
}