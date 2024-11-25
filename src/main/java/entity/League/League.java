package entity.League;

import entity.Users.User;

import java.util.ArrayList;

public class League {
    private final String id;
    private final ArrayList<User> users;


    public League(String id, ArrayList<User> users) {
        this.id = id;
        this.users = users;
    }
}
