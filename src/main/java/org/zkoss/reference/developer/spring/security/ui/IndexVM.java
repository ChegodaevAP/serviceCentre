package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexVM {
    private final String PLACES_LIST = "places-list.zul";
    private final String STATUS_LIST = "status-list.zul";
    private final String CLIENT_LIST = "client-list.zul";
    private final String REQUEST_LIST = "request-list.zul";
    private final String CREATE_USER = "create-user.zul";
    private final String DEFECT_LIST = "defect-list.zul";

    private Window window;
    private String currentPage;
    private Map<String, Map<String, NavigationPage>> pageMap;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        currentPage = REQUEST_LIST;
        this.window = window;
    }

    @Command
    @NotifyChange("currentPage")
    public void showRequests() {
        currentPage = REQUEST_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showPlaces() {
        currentPage = PLACES_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showStatus() {
        currentPage = STATUS_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showClients() {
        currentPage = CLIENT_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showDefects() {
        currentPage = DEFECT_LIST;
    }

    @Command
    public void createUser() {
        Window wind = (Window) Executions.createComponents(CREATE_USER, window, null);
        wind.doModal();
    }


    public String getCurrentPage() {
        return currentPage;
    }

}
