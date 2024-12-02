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

    /**
     * Returns user's name.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the league's ID.
     * @return leagueID.
     */
    public String getLeagueID() {
        return leagueID;
    }

    /**
     * Returns a boolean indicating if the action is join.
     * @return join.
     */
    public boolean isJoin() {
        return join;
    }
}
