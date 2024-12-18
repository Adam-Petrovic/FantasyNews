package usecase.selectwordsuserstory.to_draft;

/**
 * Interactor for to draft use case.
 */
public class ToDraftInteractor implements ToDraftInputBoundary {

    private final ToDraftOutputBoundary draftPresenter;
    private final ToDraftLeagueDataAccessInterface toDraftDataAccessInterface;

    public ToDraftInteractor(ToDraftOutputBoundary draftPresenter, ToDraftLeagueDataAccessInterface draftDao) {
        this.draftPresenter = draftPresenter;
        this.toDraftDataAccessInterface = draftDao;
    }

    @Override
    public void execute(ToDraftInputData toDraftInputData) {
        final String username = toDraftInputData.getUsername();
        final String leagueID = toDraftInputData.getLeagueID();
        final String[] words = toDraftDataAccessInterface.getWords(username, leagueID);

        ToDraftOutputData outputData = new ToDraftOutputData(username, leagueID, words);
        draftPresenter.execute(outputData);
    }
}
