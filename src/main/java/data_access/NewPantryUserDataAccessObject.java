package data_access;

import entity.League;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.update_leagues.UpdateLeaguesUserDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewPantryUserDataAccessObject implements UpdateLeaguesUserDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";

    private static final String BASKET_NAME = "users";

    private static final String API_URL = "https://getpantry.cloud/apiv1/pantry/";
    private static String key;

    public NewPantryUserDataAccessObject() {
        try {
            // if you run into an issue here, it means that you don't have your pantry API key, text Evelyn to get it
            this.key = new Scanner(new File("userKey.txt")).nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<League> addLeague(String username, String leagueID) {
        JSONObject userData = get();
        //update user leagues in json & get updates list of leagues
        save(userData);
        return new ArrayList<League>();
    }

    //gets all the basket data
    public JSONObject get() {
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
}
