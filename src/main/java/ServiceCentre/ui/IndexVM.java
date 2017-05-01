package ServiceCentre.ui;

import org.zkoss.bind.annotation.*;
import ServiceCentre.service.SecurityService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexVM {

    @WireVariable
    SecurityService securityService;
    private final String PLACES_LIST = "sc/places-list.zul";
    private final String STATUS_LIST = "sc/status-list.zul";
    private final String CLIENT_LIST = "sc/client-list.zul";
    private final String REQUEST_LIST = "sc/request-list.zul";
    private final String CREATE_USER = "sc/create-user.zul";
    private final String DEFECT_LIST = "sc/defect-list.zul";

    private Window window;
    private String currentPage;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        currentPage = REQUEST_LIST;
        this.window = window;
    }

    @Command
    @NotifyChange("currentPage")
    public void showRequests() {
        currentPage = REQUEST_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showPlaces() {
        currentPage = PLACES_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showStatus() {
        currentPage = STATUS_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showClients() {
        currentPage = CLIENT_LIST;
    }

    @Command
    @NotifyChange("currentPage")
    public void showDefects() {
        currentPage = DEFECT_LIST;
    }

    @Command
    public void createUser() {
        Window wind = (Window) Executions.createComponents(CREATE_USER, window, null);
        wind.doModal();
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public String getNameCurrentUser() {
        return "Welcome " + securityService.getCurrentUser().getName();
    }

}
