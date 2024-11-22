package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class League {
    private final String id;
    private final ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }



    public League(String id, ArrayList<User> users) {
        this.id = id;
        this.users = users;
    }

    public void setRankings(HashMap<String, ArrayList<User>> rankings){

    }

}
