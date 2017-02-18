package org.zkoss.reference.developer.spring.security.ui.security;


import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.service.UserService;
import org.zkoss.reference.developer.spring.security.ui.model.RoleListModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Window;

public class SelectRolesVM extends SelectorComposer<Component> {

    private Window window;
    @WireVariable
    private UserService userService;

    private ListModel<String> allRoles;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        allRoles = new RoleListModel();
    }
    @Listen("onClick = #closeBtn")
    public void showModal(Event e) {
        window.detach();
    }
    public ListModel<String> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(ListModel<String> allRoles) {
        this.allRoles = allRoles;
    }
}

