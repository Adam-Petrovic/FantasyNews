package usecase.update_rankings;

import entity.League;
import entity.User;

import java.util.ArrayList;

public class UpdateRankingsOutputData {
    private ArrayList<User> rankings;
    private ArrayList<User> liveRankings;
    private ArrayList<User> historicalRankings;
    private League league;

    public UpdateRankingsOutputData(ArrayList<User> liveRankings, League league, ArrayList<User> historicalRankings) {
        this.liveRankings = liveRankings;
        this.league = league;
        this.historicalRankings = historicalRankings;
    }

    public ArrayList<User> getRankings() {
        return this.rankings;
    }

    public League getLeague() {
        return league;
    }

    public ArrayList<User> getLiveRankings() {
        return liveRankings;
    }

    public ArrayList<User> getHistoricalRankings() {
        return historicalRankings;
    }
}
