package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.OrderDao;
import org.zkoss.reference.developer.spring.security.model.Order;
import org.zkoss.reference.developer.spring.security.service.OrderService;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAll();
    }

    @Override
    public Order insertOrder(Order order) {

        return orderDao.insertOrder(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderDao.findOrderById(id);
    }
}
