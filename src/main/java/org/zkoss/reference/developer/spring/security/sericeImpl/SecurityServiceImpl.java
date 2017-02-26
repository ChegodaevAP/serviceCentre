package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.Role;
import org.zkoss.reference.developer.spring.security.dao.UserDao;
import org.zkoss.reference.developer.spring.security.model.User;
import org.zkoss.reference.developer.spring.security.service.SecurityService;

import java.util.List;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<Role> getAllRoles() {
        userDao.getAllUserProfile();
        return null;
    }

    @Override
    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDao.findUserByName(name);
    }
}
