package usecase.selectwordsuserstory.draft_words;

/**
 * Output data for draft use case.
 */
public class DraftWordsOutputData {
    private String[] words;

    public DraftWordsOutputData(String[] words) {
        this.words = words;
    }

    /**
     * Getter for drafted words.
     * @return Drafted words.
     */
    public String[] getWords() {
        return words;
    }
}
