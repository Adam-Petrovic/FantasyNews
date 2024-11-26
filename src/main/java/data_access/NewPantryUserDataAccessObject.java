package data_access;

import entity.League;
import entity.User;
import entity.UserFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.add_friends.AddFriendsUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.solo_play.SoloPlayUserDataAccessInterface;
import use_case.update_leagues.UpdateLeaguesLeagueDataAccessInterface;
import use_case.update_leagues.UpdateLeaguesUserDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NewPantryUserDataAccessObject implements SignupUserDataAccessInterface,
                                                        LoginUserDataAccessInterface,
                                                        ChangePasswordUserDataAccessInterface,
                                                        LogoutUserDataAccessInterface,
                                                        SoloPlayUserDataAccessInterface,
                                                        AddFriendsUserDataAccessInterface,
                                                        UpdateLeaguesUserDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";

    private static final String PASSWORD = "password";
    private static final String WORDS = "words";
    private static final String POINTS = "points";
    private static final String LEAGUES = "leagues";
    private static final String BASKET_NAME = "users";

    private static final String API_URL = "https://getpantry.cloud/apiv1/pantry/";
    private static String key;

    private String currentUsername;
    private UserFactory userFactory;

    public NewPantryUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;

        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.key = new Scanner(new File("userKey.txt")).nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<String> addLeague(String username, String leagueID) {
        JSONObject userData = get().getJSONObject(username);
        JSONArray jsonLeagues = userData.getJSONArray(LEAGUES);
        ArrayList<String> leagueIDs = new ArrayList<>();
        for(int i = 0; i < jsonLeagues.length(); i++) {
            leagueIDs.add(jsonLeagues.getString(i));
        }
        userData.put(LEAGUES, leagueIDs);
        save(userData);
        return leagueIDs;
    }

    @Override
    public void changePassword(User user) {
        JSONObject userData = get().getJSONObject(user.getName());
        userData.put(PASSWORD, user.getPassword());
        save(userData);
    }

    @Override
    public User get(String username) {
        JSONObject userData = get().getJSONObject(username);
        //gets leagueIDs
        final JSONArray leaguesArray = userData.getJSONArray(LEAGUES);
        final ArrayList<String> leagues = new ArrayList<String>();
        for(int i = 0; i < leaguesArray.length(); i++) {
            leagues.add(leaguesArray.getString(i));
        }

        final String password = userData.getString(PASSWORD);
        final JSONObject wordsDict = userData.getJSONObject(WORDS);
        final String[] words = new String[Constants.NUM_CATEGORIES];
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words[index] = wordsDict.getString(Constants.CATEGORIES[index]);
        }
        return userFactory.create(username, password, words, leagues);
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public boolean existsByName(String username) {
        JSONObject userData = get();
        return userData.has(username);
    }

    @Override
    public void save(User user) {
        JSONObject userData = get();

        final JSONObject userInfo = new JSONObject();
        userInfo.put(PASSWORD, user.getPassword());
        userInfo.put(LEAGUES, user.getLeagueIDs());
        HashMap<String, String> words = new HashMap<String, String>();
        HashMap<String, Integer> wordPointsForCategory = new HashMap<String, Integer>();

        String[] terms = user.getWords();
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words.put(Constants.CATEGORIES[index], terms[index]);
            wordPointsForCategory.put(Constants.CATEGORIES[index], 0);
        }
        userInfo.put(WORDS, words);
        userInfo.put(POINTS, wordPointsForCategory);

        userData.put(user.getName(), userInfo);
        save(userData);
    }

    @Override
    public void setWord(String category, String word) {
    }

    //gets all the basket data
    public JSONObject get() {
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

    //saves updates basket data
    public void save(JSONObject jsonObject) {
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
                throw new RuntimeException("Failed to update leagues: " + response.message());
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error", ex);
        }
    }

    public void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
