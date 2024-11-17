package data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.solo_play.SoloPlayUserDataAccessInterface;

import data_access.Constants;

/**
 * The DAO for user data.
 */
public class PantryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SoloPlayUserDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String WORDS = "words";
    private static final String POINTS = "points";
    private static final String MESSAGE = "message";
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
        System.out.println(username);
        final String fullURL = API_URL + pantryID + "/basket/" + username;
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(fullURL)
                .get()
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
            if (response.isSuccessful()) {

                final String password = responseBody.getString(PASSWORD);
                return userFactory.create(username, password);
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
}
