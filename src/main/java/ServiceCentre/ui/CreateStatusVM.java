package ServiceCentre.ui;

import org.zkoss.bind.annotation.*;
import ServiceCentre.model.Status;
import ServiceCentre.service.DirectoryService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class CreateStatusVM {
    private final String INSERT_ERROR = "Запись уже существует";
    private final String INSERT_SUCSSES = "Успешно";
    private Window window;

    @WireVariable
    private DirectoryService directoryService;

    private Status status;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        status = new Status();
    }

    //TODO: Сделать общий интерфейс отвечающий за создание новой записи в БД

    @Command
    public void create() {
        if (directoryService.addNewStatus(status) == null) {
            Clients.showNotification(INSERT_ERROR);
        } else {
            Clients.showNotification(INSERT_SUCSSES);
            window.detach();
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
