package org.zkoss.reference.developer.spring.security.ui;


import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.service.RequestService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.zkoss.reference.developer.spring.security.ui.CreateRequestVM.SELECTED_CLIENT;

@VariableResolver(DelegatingVariableResolver.class)
public class RequestListVM {

    public static final String CREATE_REQUEST = "create-request.zul";
    public static final String VIEW_REQUEST = "view-request.zul";
    public static final String REQUEST = "request";
    private Window window;
    @WireVariable
    private RequestService requestService;

    private ListModel<Request> requestListModel;

    private Request selectedRequest;

    private String keyWord;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        requestListModel = new ListModelList<Request>(requestService.getAllRequest());
        ((ListModelList<Request>) requestListModel).setMultiple(true);
    }

    @Command
    public void addRequest() {
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

    @Command
    @NotifyChange("requestListModel")
    public void refresh() {
        init(window);
    }

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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
