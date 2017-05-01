package ServiceCentre.ui;


import org.zkoss.bind.annotation.*;
import ServiceCentre.model.Request;
import ServiceCentre.service.RequestService;
import ServiceCentre.ui.common.CommonList;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class RequestListVM extends CommonList {

    public static final String CREATE_REQUEST = "sc/create-request.zul";
    public static final String VIEW_REQUEST = "sc/view-request.zul";
    public static final String REQUEST = "request";
    private Window window;
    @WireVariable
    private RequestService requestService;

    private ListModel<Request> requestListModel;

    private Request selectedRequest;


    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        requestListModel = new ListModelList<Request>(requestService.getAllRequest());
        ((ListModelList<Request>) requestListModel).setMultiple(true);
    }

    @Override
    @Command
    public void add() {
        Window wind = (Window) Executions.createComponents(CREATE_REQUEST, window, null);
        wind.doModal();
    }

    @Command
    public void viewRequest() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(REQUEST, selectedRequest);

        Window wind = (Window) Executions.createComponents(VIEW_REQUEST, window, params);
        wind.setClosable(true);
        wind.doModal();
    }

    @Override
    @Command
    @NotifyChange("requestListModel")
    public void refresh() {
        init(window);
    }

    @Override
    @Command
    public void delete() {
        if (selectedRequest != null) {
            requestService.deleteRequest(selectedRequest);
        }
        refresh();
    }
    @Override
    @Command
    public void search() {
    }

    public ListModel<Request> getRequestListModel() {
        return requestListModel;
    }

    public void setRequestListModel(ListModel<Request> requestListModel) {
        this.requestListModel = requestListModel;
    }

    public Request getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(Request selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

}
