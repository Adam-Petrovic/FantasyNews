package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class League {
    private final String id;
    private final ArrayList<User> users;
    private final HashMap<User, String[]> data;

    public ArrayList<User> getUsers() {
        return users;
    }

    //for testing
    public League(){
        this.id = "meow";
        this.users = new ArrayList<>();
        users.add(new CommonUser("meow", "meow"));
        this.data = new HashMap<>();
        for (User user : users) {
            data.put(user, new String[] {"hi", "hello"});
        }
    }

    public League(String id, ArrayList<User> users) {
        this.id = id;
        this.users = users;
        this.data = new HashMap<>();
        for (User user : users) {
            data.put(user, new String[] {"hi", "hello"});
        }
    }

    public League(String id, ArrayList<User> users, HashMap<User, String[]> data) {
        this.id = id;
        this.users = users;
        this.data = data;
    }

    public HashMap<User, String[]> getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public void setRankings(HashMap<String, ArrayList<User>> rankings){

    }

}
