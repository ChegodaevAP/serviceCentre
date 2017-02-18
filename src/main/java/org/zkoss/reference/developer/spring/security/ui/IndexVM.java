package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexVM {
    private final String PLACES_LIST = "/places-list.zul";
    private final String STATUS_LIST = "/status-list.zul";
    private final String CLIENT_LIST = "/client-list.zul";

    private Window window;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
    }

    @Command
    public void showPlaces() {
        Window wind = (Window) Executions.createComponents(
                PLACES_LIST, null, null);
        wind.doModal();
    }
    @Command
    public void showStatus() {
        Window wind = (Window) Executions.createComponents(
                STATUS_LIST, null, null);
        wind.doModal();
    }
    @Command
    public void showClients() {
        Window wind = (Window) Executions.createComponents(
                CLIENT_LIST, null, null);
        wind.doModal();
    }
}
