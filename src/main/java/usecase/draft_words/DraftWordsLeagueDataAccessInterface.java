package usecase.draft_words;

import org.json.JSONObject;

public interface DraftWordsLeagueDataAccessInterface {
    public JSONObject get();
    public void save(JSONObject jsonObject);
    public String[] draftWord(String username, Integer categoryNum, String newWord, String leagueID);

}
