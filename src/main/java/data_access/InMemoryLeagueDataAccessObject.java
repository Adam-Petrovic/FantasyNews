package data_access;

import entity.League;
import use_case.update_rankings.UpdateRankingsLeagueDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryLeagueDataAccessObject implements UpdateRankingsLeagueDataAccessInterface {

    private final Map<String, League> leagues = new HashMap<>();


    @Override
    public ArrayList<String> getLeagueUsers(String leagueID) {
        return null;
    }


    @Override
    public HashMap<String, String[]> getData(String leagueID) {
        return null;
    }

    @Override
    public void save(League league) {
        leagues.put(league.getId(), league);
    }

    @Override
    public ArrayList<League> getLeagues(ArrayList<String> leagueIDs) {
        ArrayList<League> league = new ArrayList<>();
        for (String leagueID : leagueIDs){
            league.add(leagues.get(leagueID));
        }
        return league;
    }




}
