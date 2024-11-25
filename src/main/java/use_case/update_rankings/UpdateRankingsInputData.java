package use_case.update_rankings;

import entity.League;

public class UpdateRankingsInputData {
    private final League league;

    public UpdateRankingsInputData(League league) {
        this.league = league;
    }

    public League getLeague() {
        return league;
    }
}




