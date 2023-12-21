package PLSBackOffice.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Credentials {
    public static Map<String, String> userCredentials(String userType) {
      Map<String, String> userCredential = new HashMap<>();
        switch (userType){
            case "standard_user":
                userCredential.put("Username","standard_user");
                userCredential.put("Password","secret_sauce");
                break;

            case "locked_out_user":
                userCredential.put("Username","locked_out_user");
                userCredential.put("Password","secret_sauce");
                break;

            case "problem_user":
                userCredential.put("Username","problem_user");
                userCredential.put("Password","secret_sauce");
                break;

            case "performance_glitch_user":
                userCredential.put("Username","performance_glitch_user");
                userCredential.put("Password","secret_sauce");

        }

      return userCredential;
    }
    public static String environment(String env) {
        if (env.equalsIgnoreCase("QA")) {
            return "https://www.saucedemo.com/";
        }

        throw new NoSuchElementException();
    }
}
