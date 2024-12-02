package usecase.draft_words;

public class DraftWordsInteractor implements DraftWordsInputBoundary{
    private final DraftWordsOutputBoundary draftWordsPresenter;
    private final DraftWordsLeagueDataAccessInterface draftWordsLeagueDataAccessInterface;
    public DraftWordsInteractor(DraftWordsOutputBoundary presenter, DraftWordsLeagueDataAccessInterface DAOInterface) {
        this.draftWordsPresenter = presenter;
        this.draftWordsLeagueDataAccessInterface = DAOInterface;
    }

    public void execute(DraftWordsInputData draftWordsInputData) {
        String name = draftWordsInputData.getUsername();
        Integer num = draftWordsInputData.getCategoryNum();
        String newWord = draftWordsInputData.getNewWord();
        String leagueID = draftWordsInputData.getLeagueID();
        String words[] = draftWordsLeagueDataAccessInterface.draftWord(name, num, newWord, leagueID);
        final DraftWordsOutputData outputData = new DraftWordsOutputData(words);
        draftWordsPresenter.showDraftedWords(outputData);
    }
}

