package org.zkoss.reference.developer.spring.security.service;

import org.zkoss.reference.developer.spring.security.model.Request;

import java.util.List;

public interface OrderService {

    List<Request> getAllOrder();

    Request insertOrder(Request order);

    Request getOrderById(Integer id);
}
