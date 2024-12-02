package entity.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the word from given category.
     * @param category allowed category of words.
     * @return wordFromCategory.
     */
    String getWordFromCategory(String category);

    /**
     * Swaps the word parameter with existing content in the category.
     * @param category allowed category of words.
     * @param word new word.
     */
    void swapWords(String category, String word);

    /**
     * Returns a list of words.
     * @return a list of words.
     */
    String[] getWords();

    /**
     * Sets words to the parameter's value.
     * @param words a list of words.
     */
    void setWords(String[] words);

    /**
     * Sets leaguePoints to the parameter's value.
     * @param leaguePoints total points for the league.
     */
    void setLeaguePoints(int leaguePoints);

    /**
     * Returns the league's total points.
     * @return the league's total points.
     */
    int getLeaguePoints();

    /**
     * Returns the points for the given category.
     * @param category allowed category of words.
     * @return the points for the given category.
     */
    int getPointsForCategory(String category);

    /**
     * Sets liveLeaguePoints to the parameter's value.
     * @param liveLeaguePoints liveLeaguePoints.
     */
    void setLiveLeaguePoints(int liveLeaguePoints);

    /**
     * Returns the league's live points.
     * @return the league's live points.
     */
    int getLiveLeaguePoints();

    /**
     * Returns a list of Integers representing points.
     * @return a list of Integers representing points.
     */
    Integer[] getAllPoints();

    /**
     * Sets points to the parameter's value.
     * @param points points.
     */
    void setPoints(int[] points);

    /**
     * Add a new friend to the user's friends list.
     * @param friend user's new friend.
     */
    void addFriend(User friend);

    /**
     * Returns the user's list of friends.
     * @return the user's list of friends.
     */
    List<User> getFriends();

    /**
     * Return an arraylist of league IDs.
     * @return an arraylist of league IDs.
     */
    ArrayList<String> getLeagueIDs();

    /**
     * Sets leagueIDs to the parameter's value.
     * @param leagueIDs leagueIDs.
     */
    void setLeagueIDs(ArrayList<String> leagueIDs);

    /**
     * Returns the league's words list.
     * @return the league's words list.
     */
    String[] getLeagueWords();
}
