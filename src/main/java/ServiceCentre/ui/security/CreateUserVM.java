package ServiceCentre.ui.security;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import ServiceCentre.dao.Role;
import ServiceCentre.model.User;
import ServiceCentre.service.UserService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.LinkedList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class CreateUserVM {

    public final String SELECT_ROLES = "/select-roles.zul";

    @WireVariable
    private UserService userService;

    private User user;
    private Window window;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        user = new User();
    }

    @Command
    public void create() {
        List<Role> userProfileTypes = new LinkedList<Role>();
        Role userProfileType = Role.USER;
        userProfileTypes.add(userProfileType);
        userService.saveUser(user, userProfileTypes);
    }

    @Command
    public void showRoleList(Event e) {
        Window window = (Window) Executions.createComponents(
                SELECT_ROLES, null, null);
        window.doModal();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
}
