package ServiceCentre.ui;


import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import ServiceCentre.model.Client;
import ServiceCentre.service.ClientService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.util.Collections;

import static ServiceCentre.ui.CreateRequestVM.SELECTED_CLIENT;

@VariableResolver(DelegatingVariableResolver.class)
public class ClientListVM {

    private Window window;
    @WireVariable
    ClientService clientService;
    private ListModel<Client> clientListModel;
    private Client selectedClient;
    private String keyWord;
    public static final String CREATE_CLIENT = "sc/create-client.zul";
    public static final String CLIENT = "CLIENT";

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        refresh();
    }

    @Command
    public void addClient() {
        Window wind = (Window) Executions.createComponents(CREATE_CLIENT, window, null);
        wind.doModal();
    }

    @GlobalCommand
    @NotifyChange("clientListModel")
    public void refresh() {
        clientListModel = new ListModelList<Client>(clientService.getAllClient());
        ((ListModelList<Client>) clientListModel).setMultiple(true);
    }

    @Command
    public void close() {
        BindUtils.postGlobalCommand(null, null, SELECTED_CLIENT,
                Collections.<String, Object>singletonMap("value", selectedClient));
        window.onClose();
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
