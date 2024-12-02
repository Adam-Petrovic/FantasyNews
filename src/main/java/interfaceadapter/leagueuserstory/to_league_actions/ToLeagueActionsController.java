package interfaceadapter.leagueuserstory.to_league_actions;

import usecase.leagueuserstory.to_league_actions.ToLeagueActionsInputBoundary;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsInputData;

/**
 * Controller for switching to league actions.
 */
public class ToLeagueActionsController {
    private ToLeagueActionsInputBoundary toLeagueActionsInteractor;

    public ToLeagueActionsController(ToLeagueActionsInputBoundary toLeagueActionsInteractor) {
        this.toLeagueActionsInteractor = toLeagueActionsInteractor;
    }

    /**
     * Invokes the interactor for switching to league actions.
     * @param username user's name.
     * @param leagueID league's ID.
     */
    public void execute(String username, String leagueID) {
        final ToLeagueActionsInputData toLeagueActionsInputData = new ToLeagueActionsInputData(username, leagueID);
        toLeagueActionsInteractor.execute(toLeagueActionsInputData);
    }
}
