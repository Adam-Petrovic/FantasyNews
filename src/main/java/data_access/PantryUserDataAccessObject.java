package data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entity.League;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.add_friends.AddFriendsUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.create_league.CreateLeagueUserDataAccessInterface;
import use_case.draft.DraftUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.solo_play.SoloPlayUserDataAccessInterface;


/**
 * The DAO for user data.
 */
public class PantryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SoloPlayUserDataAccessInterface,
        AddFriendsUserDataAccessInterface,
        DraftUserDataAccessInterface,
        CreateLeagueUserDataAccessInterface{
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String WORDS = "words";
    private static final String POINTS = "points";
    private static final String MESSAGE = "message";
    private static final String LEAGUES = "leagues";
    private final UserFactory userFactory;
    private static final String API_URL = "https://getpantry.cloud/apiv1/pantry/";
    private static String pantryID;

    private static String currentUsername;

    public PantryUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        try {
        // if you run into an issue here, it menas that you don't have your pantry API key, text Alex to get it
            this.pantryID = new Scanner(new File("pantryAPIkey.txt")).nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final String fullURL = API_URL + pantryID + "/basket/" + username;
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(fullURL)
                .get()
                .build();
        try {
            final Response response = client.newCall(request).execute();
            System.out.println("trying to use get(user)");
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (response.isSuccessful()) {
                //gets leagueIDs
                final JSONArray leaguesArray = responseBody.getJSONArray(LEAGUES);
                final ArrayList<String> leagues = new ArrayList<String>();
                for(int i = 0; i < leaguesArray.length(); i++) {
                    leagues.add(leaguesArray.getString(i));
                }

                final String password = responseBody.getString(PASSWORD);
                final JSONObject wordsDict = responseBody.getJSONObject(WORDS);
                final String[] words = new String[Constants.NUM_CATEGORIES];
                for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
                    words[index] = wordsDict.getString(Constants.CATEGORIES[index]);
                }
                return userFactory.create(username, password, words, leagues);
            }
            else {
                System.out.println("username does not exist");
                throw new RuntimeException("username does not exist");
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setWord(String category, String word) {

    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public boolean existsByName(String username) {
        final String fullURL = API_URL + pantryID + "/basket/" + username;
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(fullURL)
                .get()
                .build();
        try {
            final Response response = client.newCall(request).execute();

            return response.isSuccessful();
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(PASSWORD, user.getPassword());
        requestBody.put(LEAGUES, user.getLeagueIDs());
        HashMap<String, String> words = new HashMap<String, String>();
        HashMap<String, Integer> wordPointsForCategory = new HashMap<String, Integer>();

        String[] terms = user.getWords();
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words.put(Constants.CATEGORIES[index], terms[index]);
            wordPointsForCategory.put(Constants.CATEGORIES[index], 0);
        }
        requestBody.put(WORDS, words);
        requestBody.put(POINTS, wordPointsForCategory);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url(API_URL + pantryID + "/basket/" + user.getName())
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("successful update!");
            }
            else {
                System.out.println("unsuccessful signup!");
            }
        }
        catch (IOException | JSONException ex) {
            System.out.println("error in signup");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(PASSWORD, user.getPassword());
        HashMap<String, String> words = new HashMap<String, String>();
        HashMap<String, Integer> wordPointsForCategory = new HashMap<String, Integer>();

        String[] terms = user.getWords();
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words.put(Constants.CATEGORIES[index], terms[index]);
            wordPointsForCategory.put(Constants.CATEGORIES[index], 0);
        }
        requestBody.put(WORDS, words);
        requestBody.put(POINTS, wordPointsForCategory);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url(API_URL + pantryID + "/basket/" + user.getName())
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("successful signup!");
            }
            else {
                System.out.println("unsuccessful signup!");
            }
        }
        catch (IOException | JSONException ex) {
            System.out.println("error in signup");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    //league functions
    @Override
    public ArrayList<String> updateUserLeagues(User user, String leagueID) {
        //updates user object locally
        user.getLeagueIDs().add(leagueID);

        //updates user's league list in database
        addLeague(user, user.getLeagueIDs());

        return user.getLeagueIDs();
    }

    @Override
    //for testing
    public League getLeague(User user, String leagueID) {
        ArrayList<User> users = new ArrayList();
        users.add(user);
        return new League(leagueID, users);
    }

    @Override
    public boolean leagueExist(String leagueID) {
        return false;
    }

    public void addLeague(User user, ArrayList<String> updatedLeagues) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse("application/json");

        try {
            // Update the user's leagues in JSON
            final JSONObject requestBody = new JSONObject();
            requestBody.put(LEAGUES, updatedLeagues);

            // Preserve other fields
            requestBody.put(PASSWORD, user.getPassword());
            HashMap<String, String> words = new HashMap<>();
            String[] terms = user.getWords();
            for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
                words.put(Constants.CATEGORIES[index], terms[index]);
            }
            requestBody.put(WORDS, words);

            // Make the POST request to update the basket
            final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
            final Request request = new Request.Builder()
                    .url(API_URL + pantryID + "/basket/" + user.getName())
                    .method("POST", body)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            final Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("Successfully updated leagues for user: " + user.getName());
            } else {
                System.out.println("Failed to update leagues. Status code: " + response.code());
                throw new RuntimeException("Failed to update leagues: " + response.message());
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error updating leagues for user: " + user.getName(), ex);
        }

    }
}
