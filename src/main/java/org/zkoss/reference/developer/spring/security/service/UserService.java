package org.zkoss.reference.developer.spring.security.service;


import org.zkoss.reference.developer.spring.security.dao.Role;
import org.zkoss.reference.developer.spring.security.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void saveUser(User user, List<Role> profileType);

    User findByUserName(String name);
}
