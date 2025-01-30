public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + super.description;
    }
}