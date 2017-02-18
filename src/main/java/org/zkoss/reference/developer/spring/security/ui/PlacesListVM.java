package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Place;
import org.zkoss.reference.developer.spring.security.service.DirectoryService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class PlacesListVM extends SelectorComposer<Component> {

    private final String CREATE_PLACE = "/create-place.zul";
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
    public void close() {
        window.detach();
    }

    @Command
    public void addPlace() {
        Window wind = (Window) Executions.createComponents(CREATE_PLACE, window, null);
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

