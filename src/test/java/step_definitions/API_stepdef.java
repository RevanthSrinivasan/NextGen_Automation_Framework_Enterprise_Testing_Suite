package step_definitions;

import api.client.ApiClient;
import api.requests.ClientRequest;
import api.responses.ClientResponse;
import com.fasterxml.jackson.databind.JsonNode;
import core.ScenarioVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ConfigReader;
import utils.DatalistReader;
import utils.RunnerMapReader;

import java.util.List;

public class API_stepdef {
    private final ApiClient apiClient;
    private final ScenarioVariables scenarioVariables;
    private ClientResponse clientResponse;
    public API_stepdef(ScenarioVariables scenarioVariables) {
        this.apiClient = new ApiClient();
        this.scenarioVariables = scenarioVariables;
    }

    @Given("I create a user with name {string} and job {string}")
    public void iCreateAUserWithNameAndJob(String user, String job) {

        String env = ConfigReader.get("env");

        // Only run if API module is enabled
        if (!RunnerMapReader.isModuleEnabled("api")) {
            System.out.println("Skipping API step since API module is disabled");
            return;
        }

        // Get first user for current environment
        List<JsonNode> users = DatalistReader.getByEnv("api", "users", env);
        if (users.isEmpty()) {
            throw new RuntimeException("No user found in datalist for env: " + env);
        }
        JsonNode userNode = users.getFirst();

//        String name = userNode.path("name").asText();
//        String job = userNode.path("job").asText();
        String userId = userNode.path("id").asText();

        ClientRequest request = new ClientRequest(user, job, userId);
        Response response = apiClient.createUser(request);
        clientResponse = response.as(ClientResponse.class);
        scenarioVariables.set("clientResponse", clientResponse);
        scenarioVariables.set("userId", clientResponse.getId());
        System.out.println("Created user: " + clientResponse.getName() + ", ID: " + clientResponse.getId());
    }

    @When("I retrieve the created user ID")
    public void i_retrieve_created_user_id() {
        clientResponse = scenarioVariables.get("clientResponse", ClientResponse.class);
        String userId = clientResponse.getId();
        scenarioVariables.set("userId", userId);
        System.out.println("Retrieved User ID: " + userId);
    }

    @Then("The user ID should not be null")
    public void the_user_id_should_not_be_null() {
        String userId = scenarioVariables.get("userId", String.class);
        Assert.assertNotNull(userId, "User ID should not be null");
        System.out.println("Validation successful, User ID: " + userId);
    }
}
