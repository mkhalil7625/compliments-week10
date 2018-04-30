package compliments;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

public class ComplimentClient {
    private static final String SERVER_URL= "https://glacial-retreat-45363.herokuapp.com/";
    private static final String TEXT="text";//key for reading json

    private static final String USER_ERROR_MSG="<html>error happened</html>";
    public static void getCompliment(final ComplimentGUI gui){
         Unirest.get(SERVER_URL + "random")
                 .header("accept","application/json")
                 .asJsonAsync(new Callback<JsonNode>() {
                     public void completed(com.mashape.unirest.http.HttpResponse<JsonNode> httpResponse) {
                         JSONObject json =httpResponse.getBody().getObject();
                         gui.complimentMessage(json.getString(json.getString(TEXT)));
                     }

                     public void failed(UnirestException e) {
                         gui.complimentMessage(USER_ERROR_MSG);

                     }

                     public void cancelled() {
                         System.out.println("Request cancelled");

                     }
                 });


    }
}
