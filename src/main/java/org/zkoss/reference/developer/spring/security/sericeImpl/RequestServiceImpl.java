package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.RequestDao;
import org.zkoss.reference.developer.spring.security.model.*;
import org.zkoss.reference.developer.spring.security.service.DirectoryService;
import org.zkoss.reference.developer.spring.security.service.RequestService;
import org.zkoss.reference.developer.spring.security.service.SecurityService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private MovementHistory getMovementHistoryForNewRequest(Request request){
        MovementHistory movementHistory = new MovementHistory();
        movementHistory.setDate(new Date());
        movementHistory.setPlace(securityService.getCurrentUser().getPlace());
        movementHistory.setRequest(request);
        return movementHistory;
    }

}


