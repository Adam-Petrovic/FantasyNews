package entity;
import data_access.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private int leaguePoints = 0;
    private final HashMap<String, String> words = new HashMap<>();
    private final HashMap<String,Integer> wordPointsForCategory = new HashMap<>();
    private final String[] leagueWords;
    private List<User> friends = new ArrayList<>();
    private ArrayList<String> leagueIDs;

    public CommonUser(String name, String password, String[] terms, ArrayList<String> leagueIDs){
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
//        if (draftedWords.length > Constants.NUM_CATEGORIES) {
//            throw new InstantiationError("Inputted Words are too long");
//        }
//
//        else if (hasDuplicates(draftedWords)) {
//            throw new InstantiationError("Duplicated words in drafted word list");
//        }

//        else {
//            createWordMap(draftedWords);
//        }
    }

    public CommonUser(String name, String password, String[] terms) {
        this.name = name;
        this.password = password;
        createWordMap(terms);
        this.leagueIDs = new ArrayList<>();
        this.leagueWords = Constants.DEFAULT_WORDS;
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

    private boolean hasDuplicates(String[] draftedWords) {
        Set<String> simplifiedWords = new HashSet<>(List.of(draftedWords));
        return draftedWords.length == simplifiedWords.size();
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
        String[] words = new String[Constants.NUM_CATEGORIES];
        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words[index] = this.words.get(Constants.CATEGORIES[index]);
        }
        return words;
    }

    public void setWords(String[] newWords) {
        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words.replace(Constants.CATEGORIES[index],  newWords[index]);
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
    public Integer[] getAllPoints(){
        Integer[] words = new Integer[Constants.NUM_CATEGORIES];
        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words[index] = (Integer) this.wordPointsForCategory.get(Constants.CATEGORIES[index]);
        }
        return words;
    }

    @Override
    public void setPoints(int[] points) {
        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            this.wordPointsForCategory.replace(Constants.CATEGORIES[index], points[index]);
        }
    }

    @Override
    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    public List<User> getFriends() {
        return friends;
    }

    @Override
    public ArrayList<String> getLeagueIDs(){
        return this.leagueIDs;
    }

    public void setLeagueIDs(ArrayList<String> leagueIDs) {
        this.leagueIDs = leagueIDs;
    }

    public String[] getLeagueWords(){
        return this.leagueWords;
    }
}
