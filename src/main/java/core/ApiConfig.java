package core;


import io.restassured.RestAssured;
import utils.ConfigReader;

public class ApiConfig {
    public static void init() {
        String env = ConfigReader.get("env");
        RestAssured.baseURI = ConfigReader.get("api.baseUrl." + env);
    }
}
