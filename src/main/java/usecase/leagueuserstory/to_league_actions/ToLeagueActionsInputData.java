package usecase.leagueuserstory.to_league_actions;

/**
 * Input data.
 */
public class ToLeagueActionsInputData {
    private String leagueID;
    private String username;

    public ToLeagueActionsInputData(String username, String leagueID) {
        this.leagueID = leagueID;
        this.username = username;
    }

    /**
     * Returns user's name.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns league's ID.
     * @return leagueID.
     */
    public String getLeagueID() {
        return leagueID;
    }
}
