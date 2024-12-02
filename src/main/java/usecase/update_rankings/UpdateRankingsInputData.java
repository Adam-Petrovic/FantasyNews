package usecase.update_rankings;

public class UpdateRankingsInputData {
    private final String leagueID;

    public UpdateRankingsInputData(String leagueID) {
        this.leagueID = leagueID;
    }

    public String getLeague() {
        return leagueID;
    }
}



