package interface_adapter.create_league;

import use_case.create_league.CreateLeagueInputBoundary;
import use_case.create_league.CreateLeagueInputData;

public class CreateLeagueController {
    private CreateLeagueInputBoundary createLeagueInteractor;

    public CreateLeagueController(CreateLeagueInputBoundary createLeagueInteractor) {
        this.createLeagueInteractor = createLeagueInteractor;
    }

    public void execute(String username, String leagueID) {
        CreateLeagueInputData createLeagueInputData = new CreateLeagueInputData(username, leagueID);
        createLeagueInteractor.execute(createLeagueInputData);
    }

    //execute for when switching into view
    public void execute(String username){
        CreateLeagueInputData createLeagueInputData = new CreateLeagueInputData(username, null);
        createLeagueInteractor.execute(createLeagueInputData);
    }
}
