package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.League;
import entity.User;
import use_case.to_friends.FriendsUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.solo_play.SoloPlayUserDataAccessInterface;
import use_case.update_rankings.UpdateRankingsLeagueDataAccessInterface;
import use_case.update_rankings.UpdateRankingsUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SoloPlayUserDataAccessInterface,
        FriendsUserDataAccessInterface,
        UpdateRankingsUserDataAccessInterface,
        UpdateRankingsLeagueDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private final Map<String, League> leagues = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public void setWord(String category, String word) {

    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public ArrayList<User> getUsers(ArrayList<String> usernames) {
        return new ArrayList(this.users.values());
    }


    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

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
    public ArrayList<League> getLeagues(ArrayList<String> leagueID) {
        return null;
    }
}
