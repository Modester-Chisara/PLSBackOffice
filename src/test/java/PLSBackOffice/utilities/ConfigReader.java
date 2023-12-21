package PLSBackOffice.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("config.properties");
            properties.load(file);
            file.close();
        }catch (IOException e){
            e.fillInStackTrace();
        }
    }
    public static String readProperty(String keyWord){
        return properties.getProperty(keyWord);

    }
    public static Integer readPropertyInt(String keyWord){
        return Integer.parseInt(properties.getProperty(keyWord));
    }

}
