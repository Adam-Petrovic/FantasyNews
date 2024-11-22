package interface_adapter.to_rankings;

import entity.League;
import entity.User;

public class RankingsState {
    private League league;
    private User user;

    public League getLeague() {
        return league;
    }

    public User getUser() {return user;}
}
