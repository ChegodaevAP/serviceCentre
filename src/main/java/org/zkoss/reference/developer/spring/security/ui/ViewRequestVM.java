package org.zkoss.reference.developer.spring.security.ui;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.service.RequestService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;
import fr.opensagres.xdocreport.document.IXDocReport;

import java.io.*;

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

    @Command
    public void addStatus() {


    }

}
