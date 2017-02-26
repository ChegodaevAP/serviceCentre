package org.zkoss.reference.developer.spring.security.service;

import org.zkoss.reference.developer.spring.security.model.Request;

import java.util.List;

public interface RequestService {

    List<Request> getAllRequest();

    Request insertRequest(Request request);

    Request getRequestById(Integer id);
}
