package ServiceCentre.ui;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import ServiceCentre.model.Place;
import ServiceCentre.service.DirectoryService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class CreatePlaceVM {
    private final String INSERT_ERROR = "Запись уже существует";
    private final String INSERT_SUCCESS = "Успешно";

    private Window window;

    @WireVariable
    private DirectoryService directoryService;

    private Place place;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        place = new Place();
    }

    @Command
    public void create() {
        if (directoryService.addNewPlace(place) == null) {
            Clients.showNotification(INSERT_ERROR);
        } else {
            Clients.showNotification(INSERT_SUCCESS);
            window.detach();
        }
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
