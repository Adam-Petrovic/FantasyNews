package use_case.update_rankings;

import entity.League;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateRankingsOutputData {
    private ArrayList<User> rankings;
    private League league;

    public UpdateRankingsOutputData(ArrayList<User> rankings, League league) {
        this.rankings = rankings;
        this.league = league;
    }

    public ArrayList<User> getRankings() {
        return this.rankings;
    }

    public League getLeague() {
        return league;
    }
}
