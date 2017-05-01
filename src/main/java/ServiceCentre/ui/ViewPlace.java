package ServiceCentre.ui;

import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import ServiceCentre.model.Place;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class ViewPlace {
    private Place place;

    @Init
    public void init(@ExecutionArgParam("place") Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
    public Double getLat(){
        return place.getLat();
    }
    public Double getLng(){
        return place.getLng();
    }
}
