package interfaceadapter.leagueuserstory.to_league;

import usecase.leagueuserstory.to_league.LeagueOutputBoundary;

/**
 * The LeagueController class is responsible for handling user actions
 * related to leagues and delegating these actions to the appropriate
 * output boundary for further processing.
 */
public class LeagueController {
    private final LeagueOutputBoundary leaguePresenter;

    public LeagueController(LeagueOutputBoundary leaguePresenter) {
        this.leaguePresenter = leaguePresenter;
    }

    /**
     * Executes a league-related action by delegating it to the output boundary.
     *
     * @param username the username associated with the league action.
     */
    public void execute(String username) {
        leaguePresenter.execute(username);
    }
}
