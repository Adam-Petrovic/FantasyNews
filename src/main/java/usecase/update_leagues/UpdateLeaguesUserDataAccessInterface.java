package usecase.update_leagues;

import java.util.ArrayList;

import entity.User;

/**
 * User Data Access Interface.
 */
public interface UpdateLeaguesUserDataAccessInterface {
    /**
     * Add league.
     * @param username username.
     * @param leagueID leagueID.
     * @return updated array league of list.
     */
    ArrayList<String> addLeague(String username, String leagueID);

    /**
     * Sees if user is in a league.
     * @param username username.
     * @param leagueID leagueID.
     * @return user in league.
     */
    boolean userInLeague(String username, String leagueID);

    /**
     * Gets list of users.
     * @param usernames usernames.
     * @return list of users.
     */
    ArrayList<User> getUsers(ArrayList<String> usernames);

    /**
     * Saves user.
     * @param user user.
     */
    void save(User user);
}

