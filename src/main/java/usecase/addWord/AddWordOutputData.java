package usecase.addWord;

/**
 * Output data for add word.
 */
public class AddWordOutputData {
    private String[] words;

    public AddWordOutputData(String[] words) {
        this.words = words;
    }

    /**
     * Getter for words of output data.
     * @return word array.
     */
    public String[] getWords() {
        return words;
    }
}
