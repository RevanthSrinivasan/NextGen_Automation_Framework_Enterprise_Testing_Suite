package api.responses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse {
    private String id;
    private String name;
    private String job;
    public ClientResponse() {}

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

}
