package data_access;

import entity.League;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.update_leagues.UpdateLeaguesLeagueDataAccessInterface;
import use_case.update_rankings.UpdateRankingsLeagueDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class NewPantryLeagueDataAccessObject implements UpdateLeaguesLeagueDataAccessInterface,
        UpdateRankingsLeagueDataAccessInterface {


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


    public NewPantryLeagueDataAccessObject() {
        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.key = new Scanner(new File("leagueKey.txt")).nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //should probably be combined into one: getLeague so less API calls?
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

    }

    @Override
    public void saveNewLeague(String leagueID, String username) {
        if(leagueID.equals("")){
            return;
        }

        JSONObject leagueData = get();
        JSONObject newLeague = new JSONObject();

        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(username);
        newLeague.put(USERS, usernames);
        HashMap<String, String[]> data = new HashMap<String, String[]>();
        data.put(username, Constants.DEFAULT_WORDS);
        newLeague.put(DATA, data);

        leagueData.put(leagueID, newLeague);
        save(leagueData);
    }

    @Override
    public boolean LeagueExists(String leagueID) {
        JSONObject leagueData = get();
        return leagueData.has(leagueID);
    }

    @Override
    public void addUserToLeague(String leagueID, String username) {
        JSONObject allLeagueData = get();
        JSONObject leagueData = allLeagueData.getJSONObject(leagueID);

        //updates user list
        JSONArray jsonUsers = leagueData.getJSONArray(USERS);
        ArrayList<String> usernames = new ArrayList<>();
        for(int i = 0; i < jsonUsers.length(); i++) {
            usernames.add(jsonUsers.getString(i));
        }

        //updates data hashmap
        JSONObject jsonData = leagueData.getJSONObject(DATA);
        HashMap<String, String[]> finalData = new HashMap<>();
        //creates data hashmap
        for(int i = 0; i < usernames.size(); i++) {
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
    public void updateUserPoints(String leagueID, String username) {
        JSONObject allLeagueData = get();
        JSONObject leagueData = allLeagueData.getJSONObject(leagueID);
        JSONObject data = leagueData.getJSONObject(DATA);
        JSONArray jsonWords = data.getJSONArray(username);
        String[] wordData = toStringArray(jsonWords);
        wordData[5] = "100";
        data.put(username, wordData);

        save(allLeagueData);
    }

    //prevents multiple calls to get
    @Override
    public ArrayList<League> getLeagues(ArrayList<String> userLeagueIDList) {
        ArrayList<League> leagues = new ArrayList<>();
        JSONObject allLeagueData = get();

        for(String leagueID : userLeagueIDList){
            //gets league data
            JSONObject leagueData = allLeagueData.getJSONObject(leagueID);
            ArrayList<String> usernames = toArrayList(leagueData.getJSONArray(USERS));
            JSONObject jsonData = leagueData.getJSONObject(DATA);
            HashMap<String, String[]> finalData = new HashMap<>();

            //creates data hashmap
            for(int i = 0; i < usernames.size(); i++) {
                String[] words = toStringArray(jsonData.getJSONArray(usernames.get(i)));
                finalData.put(usernames.get(i), words);
            }
            leagues.add(new League(leagueID, usernames, finalData));
        }
        return leagues;
    }

    //gets entire basket (all league data)
    public JSONObject get() {
        sleep(3);
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
                System.out.println("couldn't get league data");
                throw new RuntimeException("couldn't get league data");
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    //saves entire basket (all league data)
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
            } else {
                System.out.println("failed to save league data");
                throw new RuntimeException("failed to update league: " + response.message());
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error", ex);
        }

    }

    public <T> ArrayList<T> toArrayList(JSONArray jsonArray){
        ArrayList<T> arrayList = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            arrayList.add((T) jsonArray.get(i));
        }
        return arrayList;
    }

    public String[] toStringArray(JSONArray jsonArray){
        String[] array = new String[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++){
            array[i] = jsonArray.getString(i);
        }
        return array;
    }

    public void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
