package data_access;

import entity.League;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import usecase.draft_words.DraftWordsLeagueDataAccessInterface;
import usecase.update_rankings.UpdateRankingsLeagueDataAccessInterface;
import entity.User;
import usecase.update_leagues.UpdateLeaguesLeagueDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import data_access.Constants;

public class InMemoryLeagueDataAccessObject implements UpdateRankingsLeagueDataAccessInterface,
        DraftWordsLeagueDataAccessInterface, UpdateLeaguesLeagueDataAccessInterface {


    private final Map<String, League> leagues = new HashMap<>();


    @Override
    public ArrayList<String> getLeagueUsers(String leagueID) {
        return null;
    }


    @Override
    public HashMap<String, String[]> getData(String leagueID) {
        return null;
    }

    @Override
    public void save(League league) {
        leagues.put(league.getId(), league);
    }

    @Override
    public void saveNewLeague(String leagueID, String username) {
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(username);
        League league = new League(leagueID, usernames);
        leagues.put(leagueID, league);
    }

    @Override
    public boolean LeagueExists(String leagueID) {
        return leagues.containsKey(leagueID);
    }

    @Override
    public void addUserToLeague(String leagueID, String username) {
        leagues.get(leagueID).getUsers().add(username);
    }

    @Override
    public ArrayList<League> getLeagues(ArrayList<String> leagueIDs) {
        ArrayList<League> league = new ArrayList<>();
        for (String leagueID : leagueIDs){
            league.add(leagues.get(leagueID));
        }
        return league;
    }

    public League getLeague(String leagueID) {
        return leagues.get(leagueID);
    }

    @Override
    public JSONObject get() {
        ArrayList<League> leaguesArray = new ArrayList<>(leagues.values());
        return leaguesToJSON(leaguesArray);
    }

    @Override
    public void save(JSONObject jsonObject) {

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

//    public ArrayList<League> JSONtoLeagues(JSONObject allLeagueData) {
//        for(String leagueID : allLeagueData.keySet()) {
//            //gets league data
//            JSONObject leagueData = allLeagueData.getJSONObject(leagueID);
//            ArrayList<String> usernames = toArrayList(leagueData.getJSONArray(USERS));
//            JSONObject jsonData = leagueData.getJSONObject(DATA);
//            HashMap<String, String[]> finalData = new HashMap<>();
//
//            //creates data hashmap
//            for(int i = 0; i < usernames.size(); i++) {
//                String[] words = toStringArray(jsonData.getJSONArray(usernames.get(i)));
//                finalData.put(usernames.get(i), words);
//            }
//            leagues.add(new League(leagueID, usernames, finalData));
//        }
//    }
//    public <T> ArrayList<T> toArrayList(JSONArray jsonArray){
//        ArrayList<T> arrayList = new ArrayList<>();
//        for(int i = 0; i < jsonArray.length(); i++){
//            arrayList.add((T) jsonArray.get(i));
//        }
//        return arrayList;
//    }
}
