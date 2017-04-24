package sample;

import Rest.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class loginVerification {

    public static boolean verifyLogin(String login, String password) {
        String token = "";  //<-token do p�niejszej komunikacji, zamiast boola by�oby lepiej jakby metoda
                            //zwraca�a w�a�nie jego ale jak uznacie
        
        RestController restController = new RestController();
        String result = restController.login(login, password);
        if (!result.contains("token")) {
            return false;
        }

        try {
            JSONObject json = new JSONObject(result);
            token = json.getString("token");
        } catch (JSONException ex) {
            Logger.getLogger(loginVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println(token);
        return true;
    }

}
