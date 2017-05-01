package ServiceCentre.ui;


import ServiceCentre.model.User;
import ServiceCentre.service.UserService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.ArrayList;
import java.util.List;


@VariableResolver(DelegatingVariableResolver.class)
public class UserVM {

    @WireVariable
    UserService userService;

    public List<User> getAllUser() {
        List<User> list = new ArrayList<User>();
        return userService.getAllUser();
    }
}
