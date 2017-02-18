package org.zkoss.reference.developer.spring.security.service;

import org.zkoss.reference.developer.spring.security.dao.Role;

import java.util.List;

public interface SecurityService {

    List<Role> getAllRoles();

}
