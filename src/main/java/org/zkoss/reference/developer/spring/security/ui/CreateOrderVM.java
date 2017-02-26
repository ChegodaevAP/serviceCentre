package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.annotation.*;
import org.zkoss.reference.developer.spring.security.model.Client;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.service.ClientService;
import org.zkoss.reference.developer.spring.security.service.OrderService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.*;

@VariableResolver(DelegatingVariableResolver.class)
public class CreateOrderVM {
    public static final String INSERT_ERROR = "Запись уже существует";
    //TODO: опечатка
    public static final String INSERT_SUCSSES = "Успешно";
    public static final String CREATE_CLIENT = "/create-client.zul";
    public static final String CLIENT_LIST = "/client-list.zul";
    public static final String SELECTED_CLIENT = "refreshSelectedClient";
    private Window window;
    private Request order;
    private Client client;

    @WireVariable
    private OrderService orderService;
    @WireVariable
    private ClientService clientService;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        //TODO: Переделать механизм конвертации
        List<String> list = new ArrayList<String>();
        for(Client client:clientService.getAllClient()){
            list.add(client.getName() + " " + client.getMidleName() + " " + client.getSurname());
        }
        this.window = window;
        order = new Request();
    }

    @Command
    public void create() {
        order.setClient(client);
        if (orderService.insertOrder(order) == null) {
            Clients.showNotification(INSERT_ERROR);
        } else {
            Clients.showNotification(INSERT_SUCSSES);
            window.detach();
        }
    }

    @Command
    public void selectClient() {
        Window wind = (Window) Executions.createComponents(CLIENT_LIST, window, null);
        wind.doModal();
    }
    @GlobalCommand(SELECTED_CLIENT)
    @NotifyChange({"selectedClient"})
    public void refreshSelectedClient(@BindingParam("value") Client selectedClient) {
        this.client = selectedClient;
    }

    public Request getOrder() {
        return order;
    }

    public void setOrder(Request order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
