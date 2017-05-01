package ServiceCentre.service;

import ServiceCentre.dao.Role;
import ServiceCentre.model.User;

import java.util.List;

public interface SecurityService {

    List<Role> getAllRoles();

    User getCurrentUser();

}
