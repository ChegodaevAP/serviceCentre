package ServiceCentre.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MOVEMENT_HISTORY")
public class MovementHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "REQUEST_ID", nullable = false)
    private Request request;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
