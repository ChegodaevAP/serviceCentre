package org.zkoss.reference.developer.spring.security.ui;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Client;
import org.zkoss.reference.developer.spring.security.service.ClientService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class ClientListVM {

    private Window window;
    @WireVariable
    ClientService clientService;
    private ListModel<Client> clientListModel;
    private Client selectedClient;
    private String keyWord;
    private final String CREATE_CLIENT = "/create-client.zul";

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        clientListModel = new ListModelList<Client>(clientService.getAllClient());
        ((ListModelList<Client>) clientListModel).setMultiple(true);
    }

    @Command
    public void addClient() {
        Window wind = (Window) Executions.createComponents(CREATE_CLIENT, window, null);
        wind.doModal();
    }

    public ListModel<Client> getClientListModel() {
        return clientListModel;
    }

    public void setClientListModel(ListModel<Client> clientListModel) {
        this.clientListModel = clientListModel;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }
}
