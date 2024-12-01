package interface_adapter.to_league_actions;

import usecase.to_league_actions.ToLeagueActionsInputBoundary;
import usecase.to_league_actions.ToLeagueActionsInputData;

public class ToLeagueActionsController{
    private ToLeagueActionsInputBoundary toLeagueActionsInteractor;

    public ToLeagueActionsController(ToLeagueActionsInputBoundary toLeagueActionsInteractor) {
        this.toLeagueActionsInteractor = toLeagueActionsInteractor;
    }

    public void execute(String username, String leagueID){
        final ToLeagueActionsInputData toLeagueActionsInputData = new ToLeagueActionsInputData(username, leagueID);
        toLeagueActionsInteractor.execute(toLeagueActionsInputData);
    }
}
