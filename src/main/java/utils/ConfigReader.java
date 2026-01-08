package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;
    public static Properties loadProperties() throws IOException {
        try {
            prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/config.properties");
            prop.load(fileInputStream);
            return prop;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties"+e);
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key, prop.getProperty(key));
        if (value == null) {
            throw new RuntimeException("Missing required config key: " + key);
        }
        return value;
    }

    public static String getOptional(String key) {
        return System.getProperty(key, prop.getProperty(key));
    }

}
