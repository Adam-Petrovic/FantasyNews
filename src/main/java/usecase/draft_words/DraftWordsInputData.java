package usecase.draft_words;

/**
 * Input data for draft words.
 */
public class DraftWordsInputData {
    private final String username;
    private final Integer categoryNum;
    private final String newWord;
    private final String leagueID;

    public DraftWordsInputData(String username, Integer categoryNum, String newWord, String leagueID) {
        this.username = username;
        this.categoryNum = categoryNum;
        this.newWord = newWord;
        this.leagueID = leagueID;
    }

    /**
     * Getter for username.
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for category number.
     * @return Category number.
     */
    public Integer getCategoryNum() {
        return categoryNum;
    }

    /**
     * Getter for new word.
     * @return New word.
     */
    public String getNewWord() {
        return newWord;
    }

    /**
     * Getter for LeagueID.
     * @return LeagueID.
     */
    public String getLeagueID() {
        return leagueID;
    }
}
