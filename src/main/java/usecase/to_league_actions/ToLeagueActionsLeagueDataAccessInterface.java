package usecase.to_league_actions;

import entity.League;

import java.util.ArrayList;

public interface ToLeagueActionsLeagueDataAccessInterface {
    ArrayList<League> getLeagues(ArrayList<String> leagueID);
    void addUserToLeague(String leagueID, String username);
    public void save(League league);
}
