package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.OrderDao;
import org.zkoss.reference.developer.spring.security.model.*;
import org.zkoss.reference.developer.spring.security.service.DirectoryService;
import org.zkoss.reference.developer.spring.security.service.OrderService;
import org.zkoss.reference.developer.spring.security.service.SecurityService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DirectoryService directoryService;
    @Autowired
    SecurityService securityService;

    @Override
    public List<Request> getAllOrder() {
        return orderDao.getAll();
    }

    @Override
    public Request insertOrder(Request order) {
        order.setOrderUsers(getOrderUserForNewOrder());
        order.setStatusHistories(defaultStatus());
        order.setDate(new Date());
        return orderDao.insertOrder(order);
    }


    @Override
    public Request getOrderById(Integer id) {
        return orderDao.findOrderById(id);
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
    private Set<OrderUser> getOrderUserForNewOrder() {
        OrderUser orderUser = new OrderUser();
        orderUser.setDate(new Date());
        orderUser.setUser(securityService.getCurrentUser());
        Set<OrderUser> orderUsers = new HashSet<OrderUser>();
        orderUsers.add(orderUser);
        return orderUsers;
    }
}


