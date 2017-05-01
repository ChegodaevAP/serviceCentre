package ServiceCentre.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ServiceCentre.dao.Role;
import ServiceCentre.dao.UserDao;
import ServiceCentre.model.User;
import ServiceCentre.service.SecurityService;

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
