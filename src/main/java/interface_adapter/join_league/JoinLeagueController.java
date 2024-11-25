package interface_adapter.join_league;

import use_case.join_league.JoinLeagueInputBoundary;
import use_case.join_league.JoinLeagueInputData;

public class JoinLeagueController {
    private JoinLeagueInputBoundary joinLeagueInteractor;

    public JoinLeagueController(JoinLeagueInputBoundary joinLeagueInteractor) {
        this.joinLeagueInteractor = joinLeagueInteractor;
    }

    public void execute(String username, String leagueID){
        JoinLeagueInputData joinLeagueInputData = new JoinLeagueInputData(username, leagueID);
        joinLeagueInteractor.execute(joinLeagueInputData);
    }
}
