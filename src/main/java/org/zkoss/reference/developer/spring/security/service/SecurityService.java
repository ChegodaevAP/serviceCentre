package org.zkoss.reference.developer.spring.security.service;

import org.zkoss.reference.developer.spring.security.dao.Role;
import org.zkoss.reference.developer.spring.security.model.User;

import java.util.List;

public interface SecurityService {

    List<Role> getAllRoles();

    User getCurrentUser();

}
