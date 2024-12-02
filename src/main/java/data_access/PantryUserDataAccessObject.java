package data_access;

import entity.User;
import entity.UserFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import usecase.frienduserstory.to_friends.FriendsUserDataAccessInterface;
import usecase.navigation.login.LoginUserDataAccessInterface;
import usecase.navigation.logout.LogoutUserDataAccessInterface;
import usecase.navigation.signup.SignupUserDataAccessInterface;
import usecase.navigation.solo_play.SoloPlayUserDataAccessInterface;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsUserDataAccessInterface;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesUserDataAccessInterface;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsUserDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Data Access Object which uses the Pantry API to store users in the cloud.
 */
public class PantryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SoloPlayUserDataAccessInterface,
        FriendsUserDataAccessInterface,
        UpdateLeaguesUserDataAccessInterface,
        UpdateRankingsUserDataAccessInterface,
        ToLeagueActionsUserDataAccessInterface {

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

    public PantryUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;

        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.key = new Scanner(new File("src/main/resources/apikeys/userKey.txt")).nextLine();
        }
        catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * Get users.
     * @param usernames usernames.
     * @return users.
     */
    public ArrayList<User> getUsers(ArrayList<String> usernames) {
        JSONObject allUserData = get();
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < usernames.size(); i++) {
            JSONObject userData = allUserData.getJSONObject(usernames.get(i));
            final JSONArray leaguesArray = userData.getJSONArray(LEAGUES);
            final ArrayList<String> leagues = new ArrayList<String>();
            for (int j = 0; i < leaguesArray.length(); j++) {
                leagues.add(leaguesArray.getString(j));
            }

            final String password = userData.getString(PASSWORD);
            final JSONObject wordsDict = userData.getJSONObject(WORDS);
            final String[] words = new String[Constants.NUM_CATEGORIES];
            for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
                words[index] = wordsDict.getString(Constants.CATEGORIES[index]);
            }
            users.add(userFactory.create(usernames.get(i), password, words, leagues));
        }
        return users;
    }

    @Override
    public ArrayList<String> addLeague(String username, String leagueID) {
        JSONObject allUserData = get();
        JSONObject userData = allUserData.getJSONObject(username);
        JSONArray jsonLeagues = userData.getJSONArray(LEAGUES);
        ArrayList<String> leagueIds = new ArrayList<>();
        for (int i = 0; i < jsonLeagues.length(); i++) {
            leagueIds.add(jsonLeagues.getString(i));
        }
        if ("".equals(leagueID)) {
            return leagueIds;
        }
        leagueIds.add(leagueID);
        userData.put(LEAGUES, leagueIds);
        save(allUserData);
        return leagueIds;
    }

    /**
     * Get method for API.
     * @return User repository.
     * @throws RuntimeException exception.
     */
    public JSONObject get() {
        sleep(Constants.TIME);
        final String fullUrl = API_URL + key + "/basket/" + BASKET_NAME;
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(fullUrl)
                .get()
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (response.isSuccessful()) {
                return responseBody;
            }
            else {
                System.out.println("couldn't get user data");
                throw new RuntimeException("couldn't get user data");
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User get(String username) {
        JSONObject userData = get().getJSONObject(username);
        final JSONArray leaguesArray = userData.getJSONArray(LEAGUES);
        final ArrayList<String> leagues = new ArrayList<String>();
        for (int i = 0; i < leaguesArray.length(); i++) {
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
    public boolean userInLeague(String username, String leagueID) {
        JSONObject allUserData = get();
        JSONObject userData = allUserData.getJSONObject(username);
        JSONArray jsonLeagues = userData.getJSONArray(LEAGUES);
        if (toArrayList(jsonLeagues).contains(leagueID)) {
            return true;
        }
        return false;
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

    /**
     * Saves the jsonObject to pantry.
     * @param jsonObject users.
     * @throws RuntimeException exception.
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
                System.out.println("saved user data");
            }
            else {
                System.out.println("failed to save user data");
                throw new RuntimeException("failed to save user: " + response.message());
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException("Error", ex);
        }
    }

    @Override
    public void save(User user) {

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
        JSONObject userData = get();
        userData.put(user.getName(), userInfo);
        save(userData);
    }

    @Override
    public void setWord(String category, String word) {
    }

    /**
     * Sleep function we use for API calls.
     * @param seconds seconds to sleep.
     * @throws RuntimeException exception.
     */
    public void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Converts jsonArray to array list.
     * @param jsonArray jsonArray.
     * @param <T> generic.
     * @return array list.
     */
    public <T> ArrayList<T> toArrayList(JSONArray jsonArray) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add((T) jsonArray.get(i));
        }
        return arrayList;
    }
}
