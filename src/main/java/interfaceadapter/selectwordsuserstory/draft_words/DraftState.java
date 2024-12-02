package interfaceadapter.selectwordsuserstory.draft_words;

import entity.User;

/**
 * State for draft view.
 */
public class DraftState {
    private String username = "";
    private User user;
    private String leagueID;
    private String[] words;
    private String inputtedWord;
    private String draftError;

    /**
     * Getter for user.
     * @return User in draft state.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for user.
     * @param user Sets new user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for state's username.
     * @return Username in the state.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     * @param username The username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for leagueID.
     * @param leagueID The new leagueID.
     */
    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
    }

    /**
     * Getter for leagueID.
     * @return Returns the leagueID.
     */
    public String getLeagueID() {
        return leagueID;
    }

    /**
     * Getter for words.
     * @return The words in the state.
     */
    public String[] getWords() {
        return words;
    }

    /**
     * Setter for words.
     * @param words Words to be set.
     */
    public void setWords(String[] words) {
        this.words = words;
    }

    /**
     * Setter for inputted word.
     * @param inputtedWord The word to be inputted.
     */
    public void setInputtedWord(String inputtedWord) {
        this.inputtedWord = inputtedWord;
    }

    /**
     * Getter for inputted word.
     * @return The inputted word.
     */
    public String getInputtedWord() {
        return inputtedWord;
    }

    /**
     * Setter for errorMessage.
     * @param errorMessage The error message to set.
     */
    public void setDraftError(String errorMessage) {
        this.draftError = errorMessage;
    }

    /**
     * Getter for error message.
     * @return The error message to get.
     */
    public String getDraftError() {
        return draftError;
    }
}
