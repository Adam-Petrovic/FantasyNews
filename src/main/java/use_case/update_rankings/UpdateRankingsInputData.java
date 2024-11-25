package use_case.update_rankings;

import entity.League;

public class UpdateRankingsInputData {
    private final String leagueID;

    public UpdateRankingsInputData(String leagueID) {
        this.leagueID = leagueID;
    }

    public String getLeague() {
        return leagueID;
    }
}




