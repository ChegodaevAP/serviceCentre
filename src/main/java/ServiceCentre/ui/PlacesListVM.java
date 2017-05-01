package ServiceCentre.ui;

import org.zkoss.bind.annotation.*;
import ServiceCentre.model.Place;
import ServiceCentre.service.DirectoryService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.util.HashMap;

@VariableResolver(DelegatingVariableResolver.class)
public class PlacesListVM extends SelectorComposer<Component> {

    static final String CREATE_PLACE = "sc/create-place.zul";
    static final String VIEW_PLACE = "sc/view-place.zul";
    private Window window;
    @WireVariable
    private DirectoryService directoryService;

    private ListModel<Place> placeListModel;

    private Place selectedPlace;

    private String keyWord;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        placeListModel = new ListModelList<Place>(directoryService.getAllPlace());
        ((ListModelList<Place>) placeListModel).setMultiple(true);
    }

    @Command
    public void search() {

    }
    @Command
    @NotifyChange("placeListModel")
    public void refresh(){
        init(window);
    }

    @Command
    public void close() {
        window.detach();
    }

    @Command
    public void addPlace() {

        Window wind = (Window) Executions.createComponents(CREATE_PLACE, window, null);
        wind.doModal();
    }
    @Command
    public void viewPlace(){
        HashMap<String, Place> params = new HashMap<String, Place>();
        params.put("place",selectedPlace);
        Window wind = (Window) Executions.createComponents(VIEW_PLACE, window, params);
        wind.setTitle(selectedPlace.getAddress());
        wind.doModal();
    }

    public ListModel<Place> getPlaceListModel() {
        return placeListModel;
    }

    public Place getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(Place selectedPlace) {
        this.selectedPlace = selectedPlace;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}

