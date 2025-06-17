package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

public class Main {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String status =
                client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                                "api/complaints/355/status")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);
        System.out.println("Status: " + status);

        String complaints = client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println("Complaints: " + complaints);

        long id = 353L;
        String complaint = client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println("Complaint with ID " + id + ": " + complaint);

        String updatedComplaint = complaint.replace("\"status\":\"open\"", "\"status\":\"closed\"");
        client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints/" + id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedComplaint, MediaType.APPLICATION_JSON));

        String openComplaints = client.target("http://localhost:8080/Server-1.0-SNAPSHOT/" +
                        "api/complaints?status=open")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println("Open Complaints: " + openComplaints);
        client.close();
    }
}
