package use_case.to_league_actions;

import entity.User;

public interface ToLeagueActionsUserDataAccessInterface {
    public boolean userInLeague(String username, String leagueID);
    public void save(User user);
}
