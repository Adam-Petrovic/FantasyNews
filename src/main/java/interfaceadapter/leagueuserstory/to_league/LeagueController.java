package interfaceadapter.leagueuserstory.to_league;
import usecase.leagueuserstory.to_league.LeagueOutputBoundary;

public class LeagueController {
    private final LeagueOutputBoundary leaguePresenter;

    public LeagueController(LeagueOutputBoundary leaguePresenter) {
        this.leaguePresenter = leaguePresenter;
    }

    public void execute(String username){
        leaguePresenter.execute(username);
    }
}
