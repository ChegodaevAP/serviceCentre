package org.zkoss.reference.developer.spring.security.service;

import org.zkoss.reference.developer.spring.security.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrder();

    Order insertOrder(Order order);

    Order getOrderById(Integer id);
}
