package usecase.leagueuserstory.update_leagues;

/**
 * Update Leagues Input Data.
 */
public class UpdateLeaguesInputData {

    private String username;
    private String leagueID;
    private boolean join;

    public UpdateLeaguesInputData(String username, String leagueID, boolean join) {
        this.username = username;
        this.leagueID = leagueID;
        this.join = join;
    }

    public String getUsername() {
        return username;
    }

    public String getLeagueID() {
        return leagueID;
    }

    public boolean isJoin() {
        return join;
    }
}
