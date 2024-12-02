package usecase.selectwordsuserstory.to_draft;

/**
 * Input data for draft use case.
 */
public class ToDraftInputData {
    private final String username;
    private final String leagueID;

    public ToDraftInputData(String username, String leagueID) {
        this.username = username;
        this.leagueID = leagueID;
    }

    /**
     * Getter for username.
     * @return username.
     */
    String getUsername() {
        return username;
    }

    /**
     * Getter for leagueID.
     * @return leagueID.
     */
    String getLeagueID() {
        return leagueID;
    }
}
