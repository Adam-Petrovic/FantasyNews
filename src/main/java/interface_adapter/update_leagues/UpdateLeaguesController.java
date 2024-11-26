package interface_adapter.update_leagues;

import use_case.update_leagues.UpdateLeaguesInputBoundary;
import use_case.update_leagues.UpdateLeaguesInputData;

public class UpdateLeaguesController {
    private UpdateLeaguesInputBoundary updateLeaguesInteractor;

    public UpdateLeaguesController(UpdateLeaguesInputBoundary updateLeaguesInteractor) {
        this.updateLeaguesInteractor = updateLeaguesInteractor;
    }

    public void execute(String username, String leagueID, boolean join){
        final UpdateLeaguesInputData updateLeaguesInputData = new UpdateLeaguesInputData(username, leagueID, join);
        updateLeaguesInteractor.execute(updateLeaguesInputData);
    }

    public void execute(String username) {
        final UpdateLeaguesInputData updateLeaguesInputData = new UpdateLeaguesInputData(username, "", false);
        updateLeaguesInteractor.execute(updateLeaguesInputData);
    }
}
