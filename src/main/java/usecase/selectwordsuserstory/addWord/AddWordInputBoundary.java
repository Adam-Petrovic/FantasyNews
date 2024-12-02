package usecase.selectwordsuserstory.addWord;

/**
 * Input boundary for add word use case.
 */
public interface AddWordInputBoundary {
    /**
     * Input boundary for add word use case.
     * @param addWordInputData Contains username, word category, and new word data.
     */
    void execute(AddWordInputData addWordInputData);

}
