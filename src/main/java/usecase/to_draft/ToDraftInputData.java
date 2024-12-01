package usecase.to_draft;

public class ToDraftInputData {
    private final String username;
    private final String leagueID;
    public ToDraftInputData(String username, String leagueID) {
        this.username = username;
        this.leagueID = leagueID;
    }

    String getUsername() {
        return username;
    }
    String getLeagueID() {return leagueID;}
}
