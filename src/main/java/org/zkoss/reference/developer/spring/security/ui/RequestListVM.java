package org.zkoss.reference.developer.spring.security.ui;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.service.RequestService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class RequestListVM {

    public static final String CREATE_REQUEST = "/create-request.zul";
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
