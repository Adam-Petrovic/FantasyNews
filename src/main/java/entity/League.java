package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class League {
    private final String id;
    private final ArrayList<String> users;
    private final HashMap<String, String[]> data;
    private ArrayList<User> userObjArr = new ArrayList<>();

    public ArrayList<String> getUsers() {
        return users;
    }

    //for testing
    public League(){
        this.id = "meow";
        this.users = new ArrayList<>();
        users.add("hiii");
        this.data = new HashMap<>();
        for (String user : users) {
            data.put(user, new String[] {"hi", "hello"});
        }
    }

    public League(String id, ArrayList<String> users) {
        this.id = id;
        this.users = users;
        this.data = new HashMap<>();
        for (String user : users) {
            data.put(user, new String[] {"hi", "hello"});
        }
    }

    public ArrayList<User> getUserObjArr() {
        return userObjArr;
    }

    public League(String id, ArrayList<String> users, HashMap<String, String[]> data) {
        this.id = id;
        this.users = users;
        this.data = data;
        this.userObjArr = makeUserObjArray(data);
    }

    private ArrayList<User> makeUserObjArray(HashMap<String, String[]> data) {
        ArrayList<User> users = new ArrayList<>();
         for (String user : data.keySet()) {
             User user1 = new CommonUser(user, "", data.get(user));
             users.add(user1);
         }
         return users;
    }

    public HashMap<String, String[]> getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public void setRankings(HashMap<String, ArrayList<User>> rankings){

    }

}
