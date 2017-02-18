package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Status;
import org.zkoss.reference.developer.spring.security.service.DirectoryService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class StatusListVM {

    private Window window;
    @WireVariable
    DirectoryService directoryService;
    private ListModel<Status> statusListModel;
    private String keyWord;
    private final String CREATE_STATUS = "/create-status.zul";

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        statusListModel = new ListModelList<Status>(directoryService.getAllStatus());
        ((ListModelList<Status>) statusListModel).setMultiple(true);
    }

    @Command
    public void addStatus() {
        Window wind = (Window) Executions.createComponents(CREATE_STATUS, window, null);
        wind.doModal();
    }

    public ListModel<Status> getStatusListModel() {
        return statusListModel;
    }

    public void setStatusListModel(ListModel<Status> statusListModel) {
        this.statusListModel = statusListModel;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
