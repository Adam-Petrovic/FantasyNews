package usecase.to_draft;

/**
 * Output data of to draft use case.
 */
public class ToDraftOutputData {
    private final String username;
    private final String leagueID;
    private final String[] words;

    public ToDraftOutputData(String username, String leagueID, String[] words) {
        this.username = username;
        this.leagueID = leagueID;
        this.words = words;
    }

    /**
     * Getter for username of output data.
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for leagueID.
     * @return leagueID.
     */
    public String getLeagueID() {
        return leagueID;
    }

    /**
     * Getter for words of user.
     * @return words of user.
     */
    public String[] getWords() {
        return words;
    }
}
