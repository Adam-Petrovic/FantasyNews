package usecase.selectwordsuserstory.draft_words;

/**
 * Interactor for Draft Words Use Case.
 */
public class DraftWordsInteractor implements DraftWordsInputBoundary {
    private final DraftWordsOutputBoundary draftWordsPresenter;
    private final DraftWordsLeagueDataAccessInterface draftWordsLeagueDataAccessInterface;

    public DraftWordsInteractor(DraftWordsOutputBoundary presenter, DraftWordsLeagueDataAccessInterface DaoInterface) {
        this.draftWordsPresenter = presenter;
        this.draftWordsLeagueDataAccessInterface = DaoInterface;
    }

    /**
     * This execute method passes the input data to the leagueDAO where the DAO edits the user's drafted words.
     * @param draftWordsInputData Input data to draft words interactor.
     */
    public void execute(DraftWordsInputData draftWordsInputData) {
        String name = draftWordsInputData.getUsername();
        Integer num = draftWordsInputData.getCategoryNum();
        String newWord = draftWordsInputData.getNewWord();
        String leagueID = draftWordsInputData.getLeagueID();
        String[] words = draftWordsLeagueDataAccessInterface.draftWord(name, num, newWord, leagueID);
        final DraftWordsOutputData outputData = new DraftWordsOutputData(words);
        draftWordsPresenter.showDraftedWords(outputData);
    }
}
