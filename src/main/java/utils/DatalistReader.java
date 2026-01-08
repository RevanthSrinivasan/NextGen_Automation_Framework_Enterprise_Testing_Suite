package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatalistReader {

    private static JsonNode rootNode;

    public static void init(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read datalist.json: " + e.getMessage(), e);
        }
    }

    /**
     * Get all items for a module and key
     */
    public static List<JsonNode> get(String module, String key) {
        if (rootNode == null) throw new RuntimeException("DatalistReader not initialized!");
        JsonNode moduleNode = rootNode.path("modules").path(module).path(key);
        List<JsonNode> results = new ArrayList<>();
        if (moduleNode.isArray()) {
            moduleNode.forEach(results::add);
        }
        return results;
    }

    /**
     * Get items filtered by environment (optional)
     */
    public static List<JsonNode> getByEnv(String module, String key, String env) {
        if (!env.equals(rootNode.path("env").asText())) {
            return new ArrayList<>(); // empty if env mismatch
        }
        return get(module, key);
    }
}
