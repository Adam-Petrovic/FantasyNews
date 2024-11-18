package entity;

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

    String getWordFromCategory(String category);

    void swapWords(String category, String word);

    String[] getWords();

    void setWords(String[] words);

    void setLeaguePoints(int leaguePoints);

    int getLeaguePoints();

    int getPointsForCategory(String category);

    Integer[] getAllPoints();

    void setPoints(int[] points);

    void addFriend(User user);

}
