package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class SetupListReader {
    private static JsonNode rootNode;

    public static void init(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read setuplist.json: " + e.getMessage(), e);
        }
    }

    public static JsonNode getAsset(String assetName) {
        if (rootNode == null) throw new RuntimeException("SetupListReader not initialized!");
        return rootNode.path("assets").path(assetName);
    }
}
