package entity;

import java.util.ArrayList;
import java.util.HashMap;

import dataaccess.Constants;

/**
 * The league entity.
 */
public class League {
    private final String id;
    private final ArrayList<String> users;
    private HashMap<String, String[]> data;
    private ArrayList<User> rankings;
    private ArrayList<User> liveRankings;
    private ArrayList<User> historicalRankings;
    private ArrayList<User> userObjArr = new ArrayList<>();

    public League(String id, ArrayList<String> users, HashMap<String, String[]> data) {
        this.id = id;
        this.users = users;
        this.data = data;
        this.userObjArr = makeUserObjArray(data);
    }

    public League() {
        this.id = "meow";
        this.users = new ArrayList<>();
        users.add("hiii");
        this.data = new HashMap<>();
        for (String user : users) {
            data.put(user, Constants.DEFAULT_WORDS);
        }
        this.userObjArr = makeUserObjArray(data);
    }

    public League(String id, ArrayList<String> users) {
        this.id = id;
        this.users = users;
        this.data = new HashMap<>();
        for (String user : users) {
            data.put(user, Constants.DEFAULT_WORDS);
        }
        this.userObjArr = makeUserObjArray(data);
    }

    /**
     * Return an arraylist of String representing users.
     * @return users.
     */
    public ArrayList<String> getUsers() {
        return users;
    }

    /**
     * Sets the data field to user and words pairs, and sets the userObjArr.
     * @param user String of username
     * @param words String array of words
     */
    public void setWords(String user, String[] words) {
        this.data.put(user, words);
        this.userObjArr = makeUserObjArray(data);
    }

    /**
     * Return an arraylist of Users.
     * @return userObjArr.
     */
    public ArrayList<User> getUserObjArr() {
        return userObjArr;
    }

    private ArrayList<User> makeUserObjArray(HashMap<String, String[]> leagueData) {
        ArrayList<User> leagueUsers = new ArrayList<>();
        for (String user : leagueData.keySet()) {
            User user1 = new CommonUser(user, "", leagueData.get(user));
            leagueUsers.add(user1);
        }
        return leagueUsers;
    }

    /**
     * Return a HashMap of data.
     * @return data.
     */
    public HashMap<String, String[]> getData() {
        return data;
    }

    /**
     * Sets the value for the data field.
     * @param data data.
     */
    public void setData(HashMap<String, String[]> data) {
        this.data = data;
    }

    /**
     * Return the id field.
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the rankings field to the parameter's value.
     * @param rankings rankings.
     */
    public void setRankings(ArrayList<User> rankings) {
        this.rankings = rankings;
    }

    /**
     * Sets the liveRankings field to the parameter's value.
     * @param liveRankings live rankings.
     */
    public void setLiveRankings(ArrayList<User> liveRankings) {
        this.liveRankings = liveRankings;
    }

    /**
     * Sets the historicalRankings field to the parameter's value.
     * @param historicalRankings historical rankings.
     */
    public void setHistoricalRankings(ArrayList<User> historicalRankings) {
        this.historicalRankings = historicalRankings;
    }

    /**
     * Returns the value of rankings.
     * @return rankings.
     */
    public ArrayList<User> getRankings() {
        return rankings;
    }

    /**
     * Returns the value of live rankings.
     * @return liveRankings.
     */
    public ArrayList<User> getLiveRankings() {
        return liveRankings;
    }

    /**
     * Returns the value of historical rankings.
     * @return historicalRankings.
     */
    public ArrayList<User> getHistoricalRankings() {
        return historicalRankings;
    }
}
