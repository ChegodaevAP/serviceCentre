package ServiceCentre.service;


import ServiceCentre.dao.Role;
import ServiceCentre.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void saveUser(User user, List<Role> profileType);

    User findByUserName(String name);
}
