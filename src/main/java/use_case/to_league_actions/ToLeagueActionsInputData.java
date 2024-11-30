package use_case.to_league_actions;

public class ToLeagueActionsInputData {
    private String leagueID;
    private String username;

    public ToLeagueActionsInputData(String username, String leagueID) {
        this.leagueID = leagueID;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getLeagueID() {
        return leagueID;
    }
}
