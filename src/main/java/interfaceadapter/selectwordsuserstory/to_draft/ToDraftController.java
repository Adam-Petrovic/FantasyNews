package interfaceadapter.selectwordsuserstory.to_draft;

import usecase.selectwordsuserstory.to_draft.ToDraftInputBoundary;
import usecase.selectwordsuserstory.to_draft.ToDraftInputData;

/**
 * Controller for draft.
 */
public class ToDraftController {
    private final ToDraftInputBoundary toDraftInteractor;

    public ToDraftController(ToDraftInputBoundary toDraftInteractor) {
        this.toDraftInteractor = toDraftInteractor;
    }

    /**
     * Execute method for ToDraftController.
     *
     * @param username name.
     * @param leagueID leagueID.
     */
    public void execute(String username, String leagueID) {
        final ToDraftInputData toDraftInputData = new ToDraftInputData(username, leagueID);
        toDraftInteractor.execute(toDraftInputData);
    }
}
