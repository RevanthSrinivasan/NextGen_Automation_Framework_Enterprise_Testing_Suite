package api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

 class BaseClient {
     protected Response get(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint);
    }

    protected Response post(String endpoint, Object body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ConfigReader.get("api.token"))
                .body(body)
                .when()
                .post(endpoint);
    }
}
