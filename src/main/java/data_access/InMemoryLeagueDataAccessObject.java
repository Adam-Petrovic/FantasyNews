package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.League;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsLeagueDataAccessInterface;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesLeagueDataAccessInterface;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsLeagueDataAccessInterface;
import usecase.selectwordsuserstory.draft_words.DraftWordsLeagueDataAccessInterface;
import usecase.selectwordsuserstory.to_draft.ToDraftLeagueDataAccessInterface;

/**
 * The InMemoryLeagueDataAccessObject class provides an in-memory implementation of
 * several league-related data access interfaces. It manages league information,
 * and facilitates conversion of league data to JSON format.
 */
public class InMemoryLeagueDataAccessObject implements UpdateRankingsLeagueDataAccessInterface,
        DraftWordsLeagueDataAccessInterface, UpdateLeaguesLeagueDataAccessInterface, ToDraftLeagueDataAccessInterface,
                                                       ToLeagueActionsLeagueDataAccessInterface {

    private final Map<String, League> leagues = new HashMap<>();

    @Override
    public void save(League league) {
        leagues.put(league.getId(), league);
    }

    @Override
    public void save(JSONObject jsonObject) {

    }

    @Override
    public void saveNewLeague(String leagueID, String username) {
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(username);
        League league = new League(leagueID, usernames);
        leagues.put(leagueID, league);
    }

    @Override
    public boolean leagueExists(String leagueID) {
        return leagues.containsKey(leagueID);
    }

    @Override
    public void addUserToLeague(String leagueID, String username) {
        leagues.get(leagueID).getUsers().add(username);
    }

    @Override
    public ArrayList<League> getLeagues(ArrayList<String> leagueIds) {
        ArrayList<League> league = new ArrayList<>();
        for (String leagueID : leagueIds) {
            league.add(leagues.get(leagueID));
        }
        return league;
    }

    /**
     * Retrieves a league by its unique ID.
     *
     * @param leagueID the ID of the league to retrieve.
     * @return the League object corresponding to the provided ID, or {@code null}
     *         if no league exists with the given ID.
     */
    public League getLeague(String leagueID) {
        return leagues.get(leagueID);
    }

    @Override
    public JSONObject get() {
        ArrayList<League> leaguesArray = new ArrayList<>(leagues.values());
        return leaguesToJSON(leaguesArray);
    }

    @Override
    public String[] draftWord(String username, Integer categoryNum, String newWord, String leagueID) {
        League league = this.leagues.get(leagueID);
        HashMap<String, String[]> data = league.getData();
        String[] words = data.get(username);
        words[categoryNum] = newWord;
        data.put(username, words);
        return words;
    }

    /**
     * Converts a list of League objects into a JSON representation.
     *
     * @param leagues the list of League objects to convert.
     * @return a JSONObject representing the league data, where each league is
     *         mapped by its ID and includes associated users and data.
     */
    public static JSONObject leaguesToJSON(ArrayList<League> leagues) {
        JSONObject leaguesJSON = new JSONObject();
        for (League league : leagues) {
            HashMap<String, String[]> data = league.getData();

            JSONObject dataJSON = new JSONObject();

            for (String key : data.keySet()) {
                dataJSON.put(key, data.get(key));
            }

            JSONObject leagueJSON = new JSONObject();
            leagueJSON.put("data", dataJSON);
            leagueJSON.put("users", league.getUsers());

            leaguesJSON.put(league.getId(), leagueJSON);
        }
        System.out.println(leaguesJSON.toString());
        return leaguesJSON;
    }

    @Override
    public String[] getWords(String username, String leagueID) {
        League league = this.leagues.get(leagueID);
        HashMap<String, String[]> data = league.getData();
        String[] words = data.get(username);
        return words;
    }

    @Override
    public ArrayList<String> getAllWords(String leagueID) {
        League league = this.leagues.get(leagueID);
        HashMap<String, String[]> data = league.getData();

        ArrayList<String> allWords = new ArrayList<>();
        for (String user : data.keySet()) {
            String[] wordData = data.get(user);
            for (String word : wordData) {
                allWords.add(word);
            }
        }
        return allWords;
    }
}
