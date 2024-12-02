package interfaceadapter.leagueuserstory.update_leagues;

import usecase.leagueuserstory.update_leagues.UpdateLeaguesInputBoundary;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesInputData;

/**
 * Controller.
 */
public class UpdateLeaguesController {
    private UpdateLeaguesInputBoundary updateLeaguesInteractor;

    public UpdateLeaguesController(UpdateLeaguesInputBoundary updateLeaguesInteractor) {
        this.updateLeaguesInteractor = updateLeaguesInteractor;
    }

    /**
     * Controller execute.
     * @param join Joining or creating.
     * @param username username.
     * @param leagueID leagueID.
     */
    public void execute(String username, String leagueID, boolean join) {
        final UpdateLeaguesInputData updateLeaguesInputData = new UpdateLeaguesInputData(username, leagueID, join);
        updateLeaguesInteractor.execute(updateLeaguesInputData);
    }

    /**
     * Controller execute.
     * @param username username.
     */
    public void execute(String username) {
        final UpdateLeaguesInputData updateLeaguesInputData = new UpdateLeaguesInputData(username, "", false);
        updateLeaguesInteractor.execute(updateLeaguesInputData);
    }
}
