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
        request.setRequestUsers(getRequestUserForNewRequest());
        request.setStatusHistories(defaultStatus());
        request.setDate(new Date());
        return requestDao.insertRequest(request);
    }


    @Override
    public Request getRequestById(Integer id) {
        return requestDao.findRequestById(id);
    }

    private Set<StatusHistory> defaultStatus() {
        Status status = directoryService.getDefaultStatusOnCreate();
        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setDate(new Date());
        statusHistory.setStatus(status);
        statusHistory.setDate(new Date());
        Set<StatusHistory> statusHistories = new HashSet<StatusHistory>();
        statusHistories.add(statusHistory);
        return statusHistories;
    }
    private Set<RequestUser> getRequestUserForNewRequest() {
        RequestUser requestUser = new RequestUser();
        requestUser.setDate(new Date());
        requestUser.setUser(securityService.getCurrentUser());
        Set<RequestUser> requestUsers = new HashSet<RequestUser>();
        requestUsers.add(requestUser);
        return requestUsers;
    }
}


