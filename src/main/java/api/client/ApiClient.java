package api.client;

import api.requests.ClientRequest;
import io.restassured.response.Response;

public class ApiClient extends BaseClient{
    public Response createUser(ClientRequest request) {
        return post("/users", request); // protected accessible in subclass
    }
}
