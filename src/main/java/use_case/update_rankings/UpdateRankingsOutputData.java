package use_case.update_rankings;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateRankingsOutputData {
    private HashMap<String, ArrayList<User>> rankings;

    public UpdateRankingsOutputData(HashMap<String, ArrayList<User>> rankings) {
        this.rankings = rankings;
    }

    public HashMap<String, ArrayList<User>> getRankings() {return rankings;}
}
