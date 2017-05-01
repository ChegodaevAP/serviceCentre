package ServiceCentre.service;

import ServiceCentre.model.Report;
import ServiceCentre.model.Request;
import ServiceCentre.model.RequestUser;
import ServiceCentre.model.Status;

import java.util.List;

public interface RequestService {

    List<Request> getAllRequest();

    Request insertRequest(Request request);

    void deleteRequest(Request request);

    void updateRequest(Request request);

    Request getRequestById(Integer id);

    RequestUser addNewRequestUser(RequestUser requestUser);

    Report getReport();

    void addStatus(Request request, Status status);
}
