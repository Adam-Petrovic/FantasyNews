package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.League;
import entity.User;
import usecase.to_friends.FriendsUserDataAccessInterface;
import usecase.change_password.ChangePasswordUserDataAccessInterface;
import usecase.login.LoginUserDataAccessInterface;
import usecase.logout.LogoutUserDataAccessInterface;
import usecase.signup.SignupUserDataAccessInterface;
import usecase.solo_play.SoloPlayUserDataAccessInterface;
import usecase.update_rankings.UpdateRankingsLeagueDataAccessInterface;
import usecase.update_rankings.UpdateRankingsUserDataAccessInterface;
import usecase.update_leagues.UpdateLeaguesUserDataAccessInterface;

import usecase.to_league_actions.ToLeagueActionsUserDataAccessInterface;

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
        UpdateLeaguesUserDataAccessInterface,
        ToLeagueActionsUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();


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
    public ArrayList<String> addLeague(String username, String leagueID) {
        users.get(username).getLeagueIDs().add(leagueID);
        return get(username).getLeagueIDs();
    }

    @Override
    public boolean userInLeague(String username, String leagueID) {
        return get(username).getLeagueIDs().contains(leagueID);
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

}
