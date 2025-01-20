public class List {
    private final String[] lists;
    private static final int MAX_ITEMS = 100;
    private int currentIndex = 0;

    public List() {
        lists = new String[MAX_ITEMS];
    }

    public void addItem(String item) {
        lists[currentIndex] = item;
        currentIndex++;
    }

    public String displayList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < currentIndex; i++) {
            result.append(i + 1).append(". ").append(lists[i]).append("\n");
        }
        return result.toString();
    }
}
