package usecase.to_league_actions;

import entity.User;

import java.util.ArrayList;

public interface ToLeagueActionsUserDataAccessInterface {
    public boolean userInLeague(String username, String leagueID);
    public void save(User user);
    public ArrayList<String> addLeague(String username, String leagueID);
}
