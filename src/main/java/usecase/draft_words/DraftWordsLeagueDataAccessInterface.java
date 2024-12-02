package usecase.draft_words;

import org.json.JSONObject;

/**
 * Contains the methods that draft words use case needs in DAO.
 */
public interface DraftWordsLeagueDataAccessInterface {
    /**
     * Gets the current state of all leagues.
     * @return JSON object with all the leagues.
     */
    JSONObject get();

    /**
     * Save the edited leagues back into DAO.
     * @param jsonObject Edited league info.
     */
    void save(JSONObject jsonObject);

    /**
     * Drafts the new word into the user which is playing in the given league.
     * @param username User who is drafting a word.
     * @param categoryNum Category for word that the user is drafting.
     * @param newWord The new word that the user is drafting.
     * @param leagueID The league that the user is in.
     * @return Returns the list of words that the user now has drafted.
     */
    String[] draftWord(String username, Integer categoryNum, String newWord, String leagueID);

}
