package PLSBackOffice.Utilities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static PLSBackOffice.Utilities.ConfigReader.readProperty;


public class Credentials {
    /**
     * @param userType requires string userType
     *                 returns users credentials as LinkedHashMap
     * @return Map<String, String> userCredentials
     */
    public static Map<String, String> userCredentials(String userType) {
        Map<String, String> userCredential = new LinkedHashMap<>();
        switch (userType) {
            case "Client1":
                userCredential.put("COMPID", "HENDR-PILOT");
                userCredential.put("Username", "Client1");
                userCredential.put("Password", "Password11*");
                break;
            case "Client2":
                userCredential.put("COMPID", "HENDR-PILOT");
                userCredential.put("Username", "Client2");
                userCredential.put("Password", "Password11*");
                break;
            case "Client3":
                userCredential.put("COMPID", "INSIGHT-PROD");
                userCredential.put("Username", "Client3");
                userCredential.put("Password", "Password11*");
                break;

            case "BulkClient1":
                userCredential.put("COMPID", "AXAIM-PILOT");
                userCredential.put("Username", "BulkClient1");
                userCredential.put("Password", "Password11*");
                break;
            case "BulkClient2":
                userCredential.put("COMPID", "BAILLIE-PILOT");
                userCredential.put("Username", "BulkClient2");
                userCredential.put("Password", "Password11*");
                break;
            case "test":
                userCredential.put("COMPID", "HENDR-PILOT");
                userCredential.put("Username", "test@test.com");
                userCredential.put("Password", "Password11*");
                break;
        }
        return userCredential;
    }

    /**
     * @param env
     * @return String environment details
     */
    public static String environment(String env) {
        if (env.equalsIgnoreCase("uat2")) {
            return "https://neptx.genesislab.global/";
        }
        if (env.equalsIgnoreCase("dev3")) {
            return "https://dev-neptune-axes3/";
        }
        if (env.equalsIgnoreCase("dev6")) {
            return "https://dev-neptune-axes6/";
        }
        if (env.equalsIgnoreCase("prod")) {
            return "https://live.neptunefi.com/";
        }
        throw new NoSuchElementException();
    }

    /**
     * @param env
     * @return String database environment details
     */
    public static String db_environment(String env) {
        if (env.equalsIgnoreCase("uat2")) {
            return readProperty("database_uat2");
        } else if (env.equalsIgnoreCase("dev")) {
            return readProperty("database_dev");
        }
        throw new NoSuchElementException();
    }
}