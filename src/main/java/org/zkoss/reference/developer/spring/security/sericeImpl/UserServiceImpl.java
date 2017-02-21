package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.UserDao;
import org.zkoss.reference.developer.spring.security.dao.Role;
import org.zkoss.reference.developer.spring.security.model.User;
import org.zkoss.reference.developer.spring.security.model.UserProfile;
import org.zkoss.reference.developer.spring.security.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUser() {
        return userDao.getAll();
    }

    @Override
    public void saveUser(User user, List<Role> profileType) {
        Set<UserProfile> userProfileSet = new HashSet<UserProfile>();
        for(Role type:profileType){
            userProfileSet.add(userDao.findProfile(type.name()));
        }
        user.setUserProfiles(userProfileSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.insertUser(user);
    }

    @Override
    public User findByUserName(String name) {
        return userDao.findUserByName(name);
    }

}
