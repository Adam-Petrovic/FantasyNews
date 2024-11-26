package use_case.update_leagues;

public interface UpdateLeaguesLeagueDataAccessInterface {
    void addUserToLeague(String leagueID, String username);
    void saveNewLeague(String leagueID, String username);
    boolean leagueExists(String leagueID);
}
