package data_access;

import entity.League;
import entity.User;
import use_case.to_league_actions.ToLeagueActionsLeagueDataAccessInterface;
import use_case.update_leagues.UpdateLeaguesLeagueDataAccessInterface;
import use_case.update_rankings.UpdateRankingsLeagueDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryLeagueDataAccessObject implements UpdateRankingsLeagueDataAccessInterface,
                                                       UpdateLeaguesLeagueDataAccessInterface,
                                                       ToLeagueActionsLeagueDataAccessInterface {

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
    public void saveNewLeague(String leagueID, String username) {
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(username);
        League league = new League(leagueID, usernames);
        leagues.put(leagueID, league);
    }

    @Override
    public boolean LeagueExists(String leagueID) {
        return leagues.containsKey(leagueID);
    }

    @Override
    public void addUserToLeague(String leagueID, String username) {
        leagues.get(leagueID).getUsers().add(username);
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
