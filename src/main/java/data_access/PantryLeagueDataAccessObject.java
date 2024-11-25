package data_access;

import entity.League;
import entity.LeagueFactory;
import entity.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.create_league.CreateLeagueLeagueDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PantryLeagueDataAccessObject implements CreateLeagueLeagueDataAccessInterface {
    private final LeagueFactory leagueFactory;

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERS = "users";
    private static final String API_URL = "https://getpantry.cloud/apiv1/pantry/";
    private static String pantryID;

    public PantryLeagueDataAccessObject(LeagueFactory leagueFactory){
        this.leagueFactory = leagueFactory;
        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.pantryID = new Scanner(new File("pantryAPIkeyLeague.txt")).nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    //returns list of usernames in league
    @Override
    public ArrayList<String> getLeagueUsers(String leagueID) {
        // Make an API call to get the user object.
        final String fullURL = API_URL + pantryID + "/basket/" + leagueID;
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(fullURL)
                .get()
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (response.isSuccessful()) {
                //gets usernames
                final JSONArray userArray = responseBody.getJSONArray(USERS);
                final ArrayList<String> usernames = new ArrayList<String>();
                for(int i = 0; i < userArray.length(); i++) {
                    usernames.add(userArray.getString(i));
                }
                return usernames;
            }
            else {
                System.out.println("league does not exist");
                throw new RuntimeException("league does not exist");
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    //returns whether leagueID is in use
    @Override
    public boolean leagueExist(String leagueID) {
        final String fullURL = API_URL + pantryID + "/basket/" + leagueID;
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

    //saves league
    @Override
    public void saveNewLeague(User user, String leagueID) {
        if(leagueID == null){
            return;
        }

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(user.getName());
        requestBody.put(USERS, usernames);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url(API_URL + pantryID + "/basket/" + leagueID)
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("created league basket!");
            }
            else {
                System.out.println("couldn't create league basket!");
            }
        }
        catch (IOException | JSONException ex) {
            System.out.println("error in creation");
            throw new RuntimeException(ex);
        }
    }
}