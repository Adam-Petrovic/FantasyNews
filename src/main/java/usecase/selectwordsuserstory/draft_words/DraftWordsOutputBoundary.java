package usecase.selectwordsuserstory.draft_words;

/**
 * Output boundary for draft words use case.
 */
public interface DraftWordsOutputBoundary {
    /**
     * This method shows the drafted words to the screen.
     * @param draftWordsOutputData The drafted words.
     */
    void showDraftedWords(DraftWordsOutputData draftWordsOutputData);
}
