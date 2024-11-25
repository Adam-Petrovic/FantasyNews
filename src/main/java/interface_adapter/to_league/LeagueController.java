package interface_adapter.to_league;
import use_case.to_league.LeagueOutputBoundary;

public class LeagueController {
    private final LeagueOutputBoundary leaguePresenter;

    public LeagueController(LeagueOutputBoundary leaguePresenter) {
        this.leaguePresenter = leaguePresenter;
    }

    public void execute(String username){
        leaguePresenter.execute(username);
    }

    public void execute(){
        leaguePresenter.execute();
    }
}
