package backing;

import data.RequestRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.html.HtmlDataTable;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lab.requests.entites.Request;

import java.time.LocalDate;
import java.util.List;

@RequestScoped
@Named
public class RequestsList {

    @Inject
    private RequestRepository requestRepository;

    @Size(min = 3, max = 60, message = "{request.size}")
    private String newRequest;

    private HtmlDataTable requestsDataTable;

    @Transactional
    public String addRequest() {
        Request request = new Request();
        request.setRequestText(newRequest);
        request.setRequestDate(LocalDate.now());
        requestRepository.create(request);
        setNewRequest("");
        return "requestsList.xhtml?faces-redirect=true";
    }

    @Transactional
    public String deleteRequest() {
        Request request = (Request) requestsDataTable.getRowData();
        requestRepository.remove(request);
        return "requestsList.xhtml?faces-redirect=true";
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public String getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(String newRequest) {
        this.newRequest = newRequest;
    }

    public HtmlDataTable getRequestsDataTable() {
        return requestsDataTable;
    }

    public void setRequestsDataTable(HtmlDataTable requestsDataTable) {
        this.requestsDataTable = requestsDataTable;
    }
}
