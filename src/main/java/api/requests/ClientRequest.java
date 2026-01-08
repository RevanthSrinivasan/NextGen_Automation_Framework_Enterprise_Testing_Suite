package api.requests;

public class ClientRequest {


    private String name;
    private String job;
    private String userId;

public ClientRequest(String name, String job, String userId) {
        this.name = name;
        this.job = job;
        this.userId = userId;
    }

    // getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public String getId() { return userId; }
    public void setId(String id) { this.userId = id; }
}
