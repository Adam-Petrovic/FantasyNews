package usecase.leagueuserstory.to_league;

public interface LeagueOutputBoundary {
    void execute();
    void execute(String username);
}
