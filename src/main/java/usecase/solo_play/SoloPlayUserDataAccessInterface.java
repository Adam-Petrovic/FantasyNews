package usecase.solo_play;

import entity.User;

public interface SoloPlayUserDataAccessInterface {
    /**
     * Gets the user based on the username.
     * @param username username to get
     * @return user object
     */
    User get(String username);

    /**
     * Saves the user in the database.
     * @param user the user to be saved
     */
    void save(User user);

    /**
     * To set the words for the user.
     * @param category category to set the word
     * @param word the word to put in
     */
    void setWord(String category, String word);
}
