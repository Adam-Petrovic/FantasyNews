package entity;

import data_access.Constants;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final String[] words = new String[Constants.NUM_CATEGORIES];

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String[] getWords() {
        return new String[0];
    }

}
