package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.annotation.*;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.service.RequestService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import static org.zkoss.reference.developer.spring.security.ui.RequestListVM.REQUEST;

@VariableResolver(DelegatingVariableResolver.class)
public class ViewRequestVM {

    @WireVariable
    private RequestService requestService;

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
}
