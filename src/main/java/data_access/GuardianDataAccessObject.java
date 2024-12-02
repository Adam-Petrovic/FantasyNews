package data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import org.json.JSONObject;

import usecase.updatePointsForLeague.UpdatePointsForLeagueDataAccessObject;
import usecase.update_solo_points.UpdateSoloPlayPointsDataAccessInterface;

public class GuardianDataAccessObject implements UpdateSoloPlayPointsDataAccessInterface,
        UpdatePointsForLeagueDataAccessObject {

    private final String apiKey;
    private final HttpClient client;

    public GuardianDataAccessObject(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
    }

    private int countApperances(String word) {
        try {
            JSONObject response = fetchArticles(word).getJSONObject("response");
            return response.getInt("total");
        }

        catch (IOException | InterruptedException exception) {
            return 0;
        }
    }

    private JSONObject fetchArticles(String query) throws IOException, InterruptedException {
        // Construct the URL with parameters
        String searchTerm = query.replace(" ", "%20");
        String url = Constants.GUARDIAN_API_URL
                + getPreviousDay()
                + "&q=" + searchTerm
                + "&api-key=" + apiKey;

        // Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Send the request and handle the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == Constants.SUCCESS_CODE) {
            // Parse and return the JSON response
            return new JSONObject(response.body());
        }
        else {
            // Print error message and return null if the request fails
            System.err.println("Error " + response.statusCode() + ": " + response.body());
            return null;
        }
    }

    private String getPreviousDay() {
        return LocalDate.now().minusDays(1).toString();
    }

    @Override
    public int getPointsForCategory(String word) {
        return countApperances(word);
    }
}
