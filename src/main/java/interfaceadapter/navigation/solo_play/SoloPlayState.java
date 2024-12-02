package interfaceadapter.navigation.solo_play;

import entity.User;

/**
 * A state showing the solo play status.
 */
public class SoloPlayState {
    private User user;
    private String inputtedWord;
    private Integer[] selectedCell = {-1, -1};

    /**
     * Gets the username of the current user.
     * @return the username of the current user
     */
    public String getUsername() {
        return user.getName();
    }

    /**
     * Gets the words of the user.
     * @return the words of the user
     */
    public String[] getWords() {
        return user.getWords();
    }

    /**
     * Gets the word from the user's category.
     * @param category category in Constants.CATEGORIES
     * @return the word from the respective category
     */
    public String getWordFromCategory(String category) {
        return user.getWordFromCategory(category);
    }

    /**
     * Sets the inputted word in the add word textbox.
     * @param inputtedWord the inputted word in the add word textbox
     */
    public void setInputtedWord(String inputtedWord) {
        this.inputtedWord = inputtedWord;
    }

    /**
     * Sets the selcted cell.
     * @param selectedCell  the selcted cell, a tuple of [x, y]
     */
    public void setSelectedCell(Integer[] selectedCell) {
        this.selectedCell = selectedCell;
    }

    /**
     * Swaps a word in a category.
     * @param category category to swap
     * @param word the word which will be put into the user
     */
    public void swapWord(String category, String word) {
        user.swapWords(category, word);
    }

    /**
     * Sets the user.
     * @param user the user to be set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the current user.
     * @return the current user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the inputted word in the textbox.
     * @return the inputted word in the textbox
     */
    public String getInputtedWord() {
        return this.inputtedWord;
    }

    /**
     * Get selected cell.
     * @return the selected cell- a tuple of [x, y]
     */
    public Integer[] getSelectedCell() {
        return this.selectedCell;
    }
}
