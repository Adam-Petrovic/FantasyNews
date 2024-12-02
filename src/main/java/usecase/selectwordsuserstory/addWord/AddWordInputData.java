package usecase.selectwordsuserstory.addWord;

/**
 * Input data for add word.
 */
public class AddWordInputData {

    private final String username;
    private final String category;
    private final String newWord;

    public AddWordInputData(String username, String category, String newWord) {
        this.username = username;
        this.category = category;
        this.newWord = newWord;
    }

    /**
     * Getter for username.
     * @return username.
     */
    String getUsername() {
        return username;
    }

    /**
     * Getter for category.
     * @return category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Getter for newWord.
     * @return The new word.
     */
    public String getNewWord() {
        return newWord;
    }
}
