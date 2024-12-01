package usecase.addWord;

public class AddWordInputData {


    private final String username;
    private final String category;
    private final String newWord;

    public AddWordInputData(String username, String category, String newWord) {
        this.username = username;
        this.category = category;
        this.newWord = newWord;
    }

    String getUsername() {
        return username;
    }

    public String getCategory() {
        return category;
    }

    public String getNewWord() {
        return newWord;
    }
}
