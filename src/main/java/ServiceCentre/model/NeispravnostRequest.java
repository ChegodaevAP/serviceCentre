package ServiceCentre.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NEISPRAVNOST_HAS_NEISPRAVNOST")
public class NeispravnostRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "NEISPRAVNOST_ID", nullable = false)
    private Defect defect;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "REQUEST_ID", nullable = false)
    private Request request;

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
