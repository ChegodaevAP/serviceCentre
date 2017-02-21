package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.reference.developer.spring.security.model.Client;
import org.zkoss.reference.developer.spring.security.model.Order;
import org.zkoss.reference.developer.spring.security.service.ClientService;
import org.zkoss.reference.developer.spring.security.service.OrderService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class CreateOrderVM {
    private final String INSERT_ERROR = "Запись уже существует";
    private final String INSERT_SUCSSES = "Успешно";
    private final String CREATE_CLIENT = "/create-client.zul";
    private Window window;
    private Order order;
    private ListModel<String> clients;

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
        clients = new ListModelList<String>(list);
        this.window = window;
        order = new Order();
    }

    @Command
    public void create() {
        if (orderService.insertOrder(order) == null) {
            Clients.showNotification(INSERT_ERROR);
        } else {
            Clients.showNotification(INSERT_SUCSSES);
            window.detach();
        }
    }

    @Command
    public void newClient() {
        Window wind = (Window) Executions.createComponents(CREATE_CLIENT, window, null);
        wind.doModal();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ListModel<String> getClients() {
        return clients;
    }

    public void setClients(ListModel<String> clients) {
        this.clients = clients;
    }
}
