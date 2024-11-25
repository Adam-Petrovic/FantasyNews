package use_case.create_league;

public class CreateLeagueInputData {
    private final String username;
    private final String leagueID;

    public CreateLeagueInputData(String username, String leagueID) {
        this.username = username;
        this.leagueID = leagueID;
    }

    public String getUsername() {
        return username;
    }

    public String getLeagueID() {
        return leagueID;
    }
}
