package interface_adapter.to_league_actions;

import use_case.to_league_actions.ToLeagueActionsInputBoundary;
import use_case.to_league_actions.ToLeagueActionsInputData;
import use_case.update_leagues.UpdateLeaguesInputData;

public class ToLeagueActionsController{
    private ToLeagueActionsInputBoundary toLeagueActionsInteractor;

    public ToLeagueActionsController(ToLeagueActionsInputBoundary toLeagueActionsInteractor) {
        this.toLeagueActionsInteractor = toLeagueActionsInteractor;
    }

    public void execute(String username, String leagueID){
        final ToLeagueActionsInputData toLeagueActionsInputData = new ToLeagueActionsInputData(username, leagueID)
        toLeagueActionsInteractor.execute(toLeagueActionsInputData);
    }
}
