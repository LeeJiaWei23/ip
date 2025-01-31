package clippy.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import clippy.ClippyException;
import clippy.task.TaskList;
import clippy.ui.UI;

public class FindCommand implements Command {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String dateStr;

    public FindCommand(String dateStr) {
        this.dateStr = dateStr;
    }

    public void execute(TaskList tasks) throws ClippyException {
        LocalDate target = parseDate(dateStr);
        System.out.print(UI.filteredTaskString(tasks.displayFilteredList(target), target));
    }

    private LocalDate parseDate(String input) throws ClippyException {
        try {
            return LocalDate.parse(input, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw ClippyException.invalidDate(input);
        }
    }

    public boolean isExit() {
        return false;
    }
}
