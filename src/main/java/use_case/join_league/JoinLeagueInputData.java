package use_case.join_league;

public class JoinLeagueInputData {
    private String username;
    private String leagueID;

    public JoinLeagueInputData(String username, String leagueID) {
        this.username = username;
        this.leagueID = leagueID;
    }

    public String getUsername() {
        return username;
    }

    public String getLeagueID(){
        return leagueID;
    }
}
