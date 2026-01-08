package utils;

import java.util.List;

public class ConfigPropertiesValidator {
    public static void validateRequiredKeys(List<String> requiredKeys) {
        for (String key : requiredKeys) {
            String value = ConfigReader.getOptional(key);
            if (value == null || value.trim().isEmpty()) {
                throw new RuntimeException("Missing required config key: " + key);
            }
        }
    }
}
