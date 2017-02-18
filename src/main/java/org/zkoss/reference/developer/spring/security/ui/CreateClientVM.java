package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Client;
import org.zkoss.reference.developer.spring.security.model.Status;
import org.zkoss.reference.developer.spring.security.service.ClientService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class CreateClientVM {

    private final String INSERT_ERROR = "Запись уже существует";
    private final String INSERT_SUCSSES = "Успешно";
    private Window window;
    private Client client;

    @WireVariable
    ClientService clientService;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        client = new Client();
    }

    //TODO: Сделать общий интерфейс отвечающий за создание новой записи в БД

    @Command
    public void create() {
        if (clientService.saveClient(client) == null) {
            Clients.showNotification(INSERT_ERROR);
        } else {
            Clients.showNotification(INSERT_SUCSSES);
            window.detach();
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
