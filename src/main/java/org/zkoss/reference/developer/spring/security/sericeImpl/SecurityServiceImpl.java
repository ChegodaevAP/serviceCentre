package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.Role;
import org.zkoss.reference.developer.spring.security.dao.UserDao;
import org.zkoss.reference.developer.spring.security.service.SecurityService;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<Role> getAllRoles() {
        userDao.getAllUserProfile();
        return null;
    }
}
