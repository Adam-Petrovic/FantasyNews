package entity;

import java.util.ArrayList;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new CommonUser(name, password);
    }

    public User create(String name, String password, String[] terms) {
        return new CommonUser(name, password, terms);
    }

    public User create(String name, String password, String[] terms, ArrayList<String> leagueIDs){
        return new CommonUser(name, password, terms, leagueIDs);
    }
}
