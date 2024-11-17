package data_access;
import okhttp3.*;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;


public class PantryAPIClient {
    private static final String API_URL = "https://getpantry.cloud/apiv1/pantry/";
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String BASKET_NAME = "a";

    public static void main(String[] args) {
        String pantryId = ""; // Replace with your Pantry ID
        String url = API_URL + pantryId + "/basket/"+ BASKET_NAME;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            final Response response = client.newCall(request).execute();
            System.out.println(response.isSuccessful());
            final JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody.getJSONObject("words"));
            System.out.println(responseBody.has("words1"));
                // System.out.println(responseBody.toString());
//                final JSONObject userJSONObject = responseBody.getJSONObject("user");
//                final String name = userJSONObject.getString(USERNAME);
//                final String password = userJSONObject.getString(PASSWORD);

//                return userFactory.create(name, password);
            }

        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n\t\"derp\": \"flerp123\",\n\t\"testPayload\": true,\n\t\"keysLength\": 3\n}");
//        Request request = new Request.Builder()
//                .url("https://getpantry.cloud/apiv1/pantry/"+pantryId+"/basket/" + "username1")
//                .method("POST", body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
