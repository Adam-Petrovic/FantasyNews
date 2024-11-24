package interface_adapter.create_league;

import use_case.create_league.CreateLeagueInputBoundary;
import use_case.create_league.CreateLeagueInputData;

public class CreateLeagueController {
    private CreateLeagueInputBoundary createLeagueInteractor;

    public CreateLeagueController(CreateLeagueInputBoundary createLeagueInteractor) {
        this.createLeagueInteractor = createLeagueInteractor;
    }

    public void execute(CreateLeagueInputData createLeagueInputData) {
        createLeagueInteractor.execute(createLeagueInputData);
    }
}
