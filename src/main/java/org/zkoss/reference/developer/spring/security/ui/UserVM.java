package org.zkoss.reference.developer.spring.security.ui;


import org.zkoss.reference.developer.spring.security.model.User;
import org.zkoss.reference.developer.spring.security.service.UserService;
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
