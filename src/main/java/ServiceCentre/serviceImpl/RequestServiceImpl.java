package ServiceCentre.serviceImpl;

import ServiceCentre.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ServiceCentre.dao.RequestDao;
import ServiceCentre.service.DirectoryService;
import ServiceCentre.service.RequestService;
import ServiceCentre.service.SecurityService;

import java.util.Date;
import java.util.List;

@Service("requestService")
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDao requestDao;
    @Autowired
    private DirectoryService directoryService;
    @Autowired
    SecurityService securityService;

    @Override
    public List<Request> getAllRequest() {
        return requestDao.getAll();
    }

    @Override
    public Request insertRequest(Request request) {
        request.setDate(new Date());
        request = requestDao.insertRequest(request);
        addNewRequestUser(getRequestUserForNewRequest(request));
        directoryService.addNewMovementHistory(getMovementHistoryForNewRequest(request));
        directoryService.addNewStatusHistory(defaultStatus(request));
        return request;
    }

    @Override
    public void deleteRequest(Request request) {
        requestDao.delete(request);
    }

    @Override
    public void updateRequest(Request request) {
        requestDao.updateRequest(request);
    }


    @Override
    public Request getRequestById(Integer id) {
        return requestDao.findRequestById(id);
    }

    @Override
    public RequestUser addNewRequestUser(RequestUser requestUser) {
        return requestDao.insertRequestUser(requestUser);
    }

    @Override
    public Report getReport() {
        return requestDao.getReport();
    }

    @Override
    public void addStatus(Request request, Status status) {
        request.getStatusHistories().add(updateStatusHistory(request, status));
        directoryService.addNewStatusHistory(updateStatusHistory(request,status));
    }

    private StatusHistory updateStatusHistory(Request request, Status status) {
        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setDate(new Date());
        statusHistory.setRequest(request);
        statusHistory.setStatus(status);
        return statusHistory;
    }

    private StatusHistory defaultStatus(Request request) {
        Status status = directoryService.getDefaultStatusOnCreate();
        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setDate(new Date());
        statusHistory.setStatus(status);
        statusHistory.setDate(new Date());
        statusHistory.setRequest(request);
        return statusHistory;
    }

    private RequestUser getRequestUserForNewRequest(Request request) {
        RequestUser requestUser = new RequestUser();
        requestUser.setDate(new Date());
        requestUser.setUser(securityService.getCurrentUser());
        requestUser.setRequest(request);
        return requestUser;
    }

    private MovementHistory getMovementHistoryForNewRequest(Request request) {
        MovementHistory movementHistory = new MovementHistory();
        movementHistory.setDate(new Date());
        movementHistory.setPlace(securityService.getCurrentUser().getPlace());
        movementHistory.setRequest(request);
        return movementHistory;
    }

}


