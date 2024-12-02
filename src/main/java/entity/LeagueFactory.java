package entity;

import java.util.ArrayList;

/**
 * The factory class for the league.
 */
public class LeagueFactory {
    /**
     * Create a league with no parameters.
     * @return new League object.
     */
    public League create() {
        return new League();
    }

    /**
     * Create a league with given parameters.
     * @param leagueName the league's name.
     * @param usernames for all users who belong in the league.
     * @return new League object.
     */
    public League create(String leagueName, ArrayList<String> usernames) {
        return new League(leagueName, usernames);
    }

}
