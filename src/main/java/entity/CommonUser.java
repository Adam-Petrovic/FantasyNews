package entity;

import java.util.*;

import dataaccess.Constants;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private int leaguePoints;
    private int liveLeaguePoints;
    private final Map<String, String> words = new HashMap<>();
    private final Map<String, Integer> wordPointsForCategory = new HashMap<>();
    private final String[] leagueWords;
    private final List<User> friends = new ArrayList<>();
    private ArrayList<String> leagueIDs;

    public CommonUser(String name, String password, String[] terms, ArrayList<String> leagueIDs) {
        createWordMap(terms);
        this.name = name;
        this.password = password;
        this.leagueIDs = leagueIDs;
        this.leagueWords = Constants.DEFAULT_WORDS;
    }

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.leagueIDs = new ArrayList<>();

        this.leagueWords = Constants.DEFAULT_WORDS;

        createWordMap();
    }

    public CommonUser(String name, String password, String[] terms) {
        this.name = name;
        this.password = password;
        this.leagueIDs = new ArrayList<>();
        this.leagueWords = terms;
        createWordMap(terms);
    }

    private void createWordMap() {

        String[] terms = Constants.DEFAULT_WORDS;
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            this.words.put(Constants.CATEGORIES[index], terms[index]);
            this.wordPointsForCategory.put(Constants.CATEGORIES[index], 0);
        }
    }

    private void createWordMap(String[] terms) {
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            this.words.put(Constants.CATEGORIES[index], terms[index]);
            this.wordPointsForCategory.put(Constants.CATEGORIES[index], 0);
        }
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
    public String getWordFromCategory(String category) {
        return this.words.get(category);
    }

    @Override
    public void swapWords(String category, String word) {
        this.words.replace(category, word);
    }

    @Override
    public String[] getWords() {
        String[] userWords = new String[Constants.NUM_CATEGORIES];
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            userWords[index] = this.words.get(Constants.CATEGORIES[index]);
        }
        return userWords;
    }

    /**
     * Sets the field words, given the input array of new words.
     * @param newWords a String array of new words.
     */
    public void setWords(String[] newWords) {
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words.replace(Constants.CATEGORIES[index], newWords[index]);
        }
    }

    @Override
    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    @Override
    public int getLeaguePoints() {
        return leaguePoints;
    }

    @Override
    public int getPointsForCategory(String category) {
        return this.wordPointsForCategory.get(category);
    }

    @Override
    public Integer[] getAllPoints() {
        Integer[] wordPoints = new Integer[Constants.NUM_CATEGORIES];
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            wordPoints[index] = (Integer) this.wordPointsForCategory.get(Constants.CATEGORIES[index]);
        }
        return wordPoints;
    }

    @Override
    public void setPoints(int[] points) {
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            this.wordPointsForCategory.replace(Constants.CATEGORIES[index], points[index]);
        }
    }

    @Override
    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    @Override
    public List<User> getFriends() {
        return this.friends;
    }

    @Override
    public ArrayList<String> getLeagueIDs() {
        return this.leagueIDs;
    }

    @Override
    public void setLeagueIDs(ArrayList<String> leagueIDs) {
        this.leagueIDs = leagueIDs;
    }

    @Override
    public String[] getLeagueWords() {
        return this.leagueWords;
    }

    @Override
    public int getLiveLeaguePoints() {
        return liveLeaguePoints;
    }

    @Override
    public void setLiveLeaguePoints(int liveLeaguePoints) {
        this.liveLeaguePoints = liveLeaguePoints;
    }
}
