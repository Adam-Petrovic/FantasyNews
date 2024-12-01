package usecase.to_draft;

public class ToDraftOutputData {
    private final String username;
    private final String leagueID;
    private final String[] words;
    public ToDraftOutputData(String username, String leagueID, String[] words) {
        this.username = username;
        this.leagueID = leagueID;
        this.words = words;
    }

    public String getUsername() {
        return username;
    }
    public String getLeagueID() {return leagueID;}

    public String[] getWords() {
        return words;
    }
}
