package dataaccess;

// Standard library imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.League;
import entity.User;
import okhttp3.*;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsDataAccessInterface;
import usecase.selectwordsuserstory.draft_words.DraftWordsLeagueDataAccessInterface;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsDataAccessObject;
import usecase.selectwordsuserstory.to_draft.ToDraftLeagueDataAccessInterface;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsLeagueDataAccessInterface;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesLeagueDataAccessInterface;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsLeagueDataAccessInterface;

/**
 * Pantry League Data Access Object.
 */
public class PantryLeagueDataAccessObject implements UpdateLeaguesLeagueDataAccessInterface,
        UpdateRankingsLeagueDataAccessInterface, ToDraftLeagueDataAccessInterface,
        DraftWordsLeagueDataAccessInterface, AwardLeaguePointsDataAccessInterface,
        RoundLeaguePointsDataAccessObject, ToLeagueActionsLeagueDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";

    private static final String PASSWORD = "password";
    private static final String WORDS = "words";
    private static final String POINTS = "points";
    private static final String LEAGUES = "leagues";
    private static final String USERS = "users";
    private static final String DATA = "data";

    private static final String BASKET_NAME = "leagues";

    private static final String API_URL = "https://getpantry.cloud/apiv1/pantry/";
    private static String key;

    public PantryLeagueDataAccessObject() {
        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.key = new Scanner(new File("src/main/resources/apikeys/leagueKey.txt")).nextLine();
        }
        catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String[] getWords(String username, String leagueID) {
        JSONObject obj = get();
        JSONObject league = obj.getJSONObject(leagueID);
        JSONObject data = league.getJSONObject(DATA);
        JSONArray words = data.getJSONArray(username);
        String[] wordData = toStringArray(words);
        return wordData;
    }

    @Override
    public ArrayList<String> getAllWords(String leagueID) {
        JSONObject obj = get();
        JSONObject league = obj.getJSONObject(leagueID);
        JSONObject data = league.getJSONObject(DATA);
        ArrayList<String> allWords = new ArrayList<>();
        for (String user : data.keySet()) {
            String[] wordData = toStringArray((JSONArray) data.get(user));
            for (String word : wordData) {
                allWords.add(word);
            }
        }
        return allWords;
    }

    @Override
    public String[] draftWord(String username, Integer categoryNum, String newWord, String leagueID) {
        JSONObject obj = get();
        JSONObject league = obj.getJSONObject(leagueID);
        JSONObject data = league.getJSONObject(DATA);
        JSONArray words = data.getJSONArray(username);
        String[] wordData = toStringArray(words);
        wordData[categoryNum] = newWord;
        data.put(username, wordData);
        save(obj);
        return wordData;
    }

    @Override
    public void saveNewLeague(String leagueID, String username) {
        JSONObject newLeague = new JSONObject();

        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(username);
        newLeague.put(USERS, usernames);
        HashMap<String, String[]> data = new HashMap<String, String[]>();
        data.put(username, Constants.DEFAULT_WORDS);
        newLeague.put(DATA, data);

        JSONObject leagueData = get();
        leagueData.put(leagueID, newLeague);
        save(leagueData);
    }

    @Override
    public boolean leagueExists(String leagueID) {
        JSONObject leagueData = get();
        return leagueData.has(leagueID);
    }

    @Override
    public void addUserToLeague(String leagueID, String username) {
        JSONObject allLeagueData = get();
        JSONObject leagueData = allLeagueData.getJSONObject(leagueID);

        // updates user list
        JSONArray jsonUsers = leagueData.getJSONArray(USERS);
        ArrayList<String> usernames = new ArrayList<>();
        for (int i = 0; i < jsonUsers.length(); i++) {
            usernames.add(jsonUsers.getString(i));
        }

        // updates data hashmap
        JSONObject jsonData = leagueData.getJSONObject(DATA);
        HashMap<String, String[]> finalData = new HashMap<>();
        // creates data hashmap
        for (int i = 0; i < usernames.size(); i++) {
            String[] words = toStringArray(jsonData.getJSONArray(usernames.get(i)));
            finalData.put(usernames.get(i), words);
        }
        finalData.put(username, Constants.DEFAULT_WORDS);

        leagueData.put(DATA, finalData);
        usernames.add(username);
        leagueData.put(USERS, usernames);

        save(allLeagueData);
    }

    @Override
    public void updatePointsForUser(String leagueID, String username, int numUsers) {
        JSONObject allLeagueData = get();
        JSONObject leagueData = allLeagueData.getJSONObject(leagueID);
        JSONObject data = leagueData.getJSONObject(DATA);
        JSONArray jsonWords = data.getJSONArray(username);
        String[] wordData = toStringArray(jsonWords);
        double newScore = Double.parseDouble(wordData[Constants.NUM_CATEGORIES]) + 1 / numUsers;
        wordData[Constants.NUM_CATEGORIES] = String.valueOf(newScore);
        data.put(username, wordData);
        save(allLeagueData);
    }

    // prevents multiple calls to get
    @Override
    public ArrayList<League> getLeagues(ArrayList<String> userLeagueIDList) {
        ArrayList<League> leagues = new ArrayList<>();
        JSONObject allLeagueData = get();

        for (String leagueID : userLeagueIDList) {
            // gets league data
            JSONObject leagueData = allLeagueData.getJSONObject(leagueID);
            ArrayList<String> usernames = toArrayList(leagueData.getJSONArray(USERS));
            JSONObject jsonData = leagueData.getJSONObject(DATA);
            HashMap<String, String[]> finalData = new HashMap<>();

            // creates data hashmap
            for (int i = 0; i < usernames.size(); i++) {
                String[] words = toStringArray(jsonData.getJSONArray(usernames.get(i)));
                finalData.put(usernames.get(i), words);
            }
            leagues.add(new League(leagueID, usernames, finalData));
        }
        return leagues;
    }

    // gets entire basket (all league data)

    /**
     * Gets all League JSON Data.
     * @return league JSON.
     * @throws RuntimeException ex.
     */
    public JSONObject get() {
        sleep(2);
        // Make an API call to get the user object.
        final String fullURL = API_URL + key + "/basket/" + BASKET_NAME;
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(fullURL)
                .get()
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (response.isSuccessful()) {
                return responseBody;
            }
            else {
                throw new RuntimeException("couldn't get league data");
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Save.
     * @param league league.
     */
    public void save(League league) {
    }

    /**
     * Saves JSON.
     * @param jsonObject json.
     * @throws RuntimeException ex.
     */
    public void save(JSONObject jsonObject) {
        sleep(2);
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse("application/json");

        try {
            final JSONObject requestBody = jsonObject;

            // Make the POST request to update the basket
            final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
            final Request request = new Request.Builder()
                    .url(API_URL + key + "/basket/" + BASKET_NAME)
                    .method("POST", body)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            final Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("saved league data");
            }
            else {
                throw new RuntimeException("failed to update league: " + response.message());
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException("Error", ex);
        }

    }

    /**
     * To array list.
     * @param jsonArray json array.
     * @param <T> type.
     * @return array list.
     */
    public <T> ArrayList<T> toArrayList(JSONArray jsonArray) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add((T) jsonArray.get(i));
        }
        return arrayList;
    }

    /**
     * To String [].
     * @param jsonArray jsonArray.
     * @return String[].
     */
    public String[] toStringArray(JSONArray jsonArray) {
        String[] array = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            array[i] = jsonArray.getString(i);
        }
        return array;
    }

    /**
     * Sleep.
     * @param seconds seconds.
     * @throws RuntimeException ex.
     */
    public void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void roundPoints(String leagueID, ArrayList<User> users) {
        JSONObject allLeagueData = get();
        JSONObject leagueData = allLeagueData.getJSONObject(leagueID);
        JSONObject data = leagueData.getJSONObject(DATA);
        for (User user : users) {
            String username = user.getName();
            JSONArray jsonWords = data.getJSONArray(username);
            String[] wordData = toStringArray(jsonWords);
            double newScore = Math.ceil(Double.parseDouble(wordData[Constants.NUM_CATEGORIES]));
            wordData[Constants.NUM_CATEGORIES] = String.valueOf(newScore);
            data.put(username, wordData);
        }
        save(allLeagueData);
    }
}
