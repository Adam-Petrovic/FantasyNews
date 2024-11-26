package data_access;

import entity.LeagueFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.update_leagues.UpdateLeaguesLeagueDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class NewPantryLeagueDataAccessObject implements UpdateLeaguesLeagueDataAccessInterface {


    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";

    private static final String BASKET_NAME_LEAGUE = "leagues";
    private static final String BASKET_NAME_USER = "users";

    private static final String API_URL = "https://getpantry.cloud/apiv1/pantry/";
    private static String leagueKey;
    private static String userKey;

    public NewPantryLeagueDataAccessObject() {
        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.leagueKey = new Scanner(new File("leagueKey.txt")).nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.userKey = new Scanner(new File("userKey.txt")).nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public JSONObject get(String type) {
        String KEY;
        String BASKET_NAME;
        if(type.equals("league")){
            KEY = leagueKey;
            BASKET_NAME = BASKET_NAME_LEAGUE;
        }
        else{
            KEY = userKey;
            BASKET_NAME = BASKET_NAME_USER;
        }
        // Make an API call to get the user object.
        final String fullURL = API_URL + KEY + "/basket/" + BASKET_NAME;
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(fullURL)
                .get()
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (response.isSuccessful()) {
                //gets data, turn back into HashMap
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

    @Override
    public void save(JSONObject jsonObject, String type) {
        String KEY;
        String BASKET_NAME;
        if(type.equals("league")){
            KEY = leagueKey;
            BASKET_NAME = BASKET_NAME_LEAGUE;
        }
        else{
            KEY = userKey;
            BASKET_NAME = BASKET_NAME_USER;
        }

        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse("application/json");

        try {
            final JSONObject requestBody = jsonObject;

            // Make the POST request to update the basket
            final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
            final Request request = new Request.Builder()
                    .url(API_URL + KEY + "/basket/" + BASKET_NAME)
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
}
