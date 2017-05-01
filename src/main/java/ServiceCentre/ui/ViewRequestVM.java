package ServiceCentre.ui;

import org.zkoss.bind.annotation.*;
import ServiceCentre.model.Client;
import ServiceCentre.model.Request;
import ServiceCentre.model.Status;
import ServiceCentre.service.DirectoryService;
import ServiceCentre.service.RequestService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

import static ServiceCentre.ui.RequestListVM.REQUEST;

@VariableResolver(DelegatingVariableResolver.class)
public class ViewRequestVM {
    public static final String ADD_STATUS = "ADD_STATUS";

    @WireVariable
    private RequestService requestService;

    @WireVariable
    private DirectoryService directoryService;

    private Request selectedRequest;
    private Window window;

    @Init(superclass = true)
    public void init(@ExecutionArgParam(REQUEST) Request request) {
        selectedRequest = request;
    }

    public Request getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(Request selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    @Command
    public void addStatus() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(REQUEST, selectedRequest);
        Window wind = (Window) Executions.createComponents("status-list.zul", window, params);
        wind.setTitle("Статусы");
        wind.setClosable(true);
        wind.setWidth("40%");
        wind.doModal();
    }
    @GlobalCommand(ADD_STATUS)
    @NotifyChange("selectedRequest")
    public void refreshStatus(@BindingParam("value") Status status){
        requestService.addStatus(selectedRequest, status);
    }
}
