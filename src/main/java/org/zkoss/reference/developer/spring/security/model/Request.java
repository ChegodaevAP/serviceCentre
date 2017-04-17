package org.zkoss.reference.developer.spring.security.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "REQUEST")
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "request")
    private Set<RequestUser> requestUsers;

    @OneToMany(mappedBy = "request")
    private Set<StatusHistory> statusHistories;

    @OneToMany(mappedBy = "request")
    private Set<MovementHistory> movementHistory;

    @OneToMany(mappedBy = "request")
    private Set<NeispravnostRequest> neispravnost;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<MovementHistory> getMovementHistory() {
        return movementHistory;
    }

    public void setMovementHistory(Set<MovementHistory> movementHistory) {
        this.movementHistory = movementHistory;
    }

    public Set<StatusHistory> getStatusHistories() {
        return statusHistories;
    }

    public void setStatusHistories(Set<StatusHistory> statusHistories) {
        this.statusHistories = statusHistories;
    }

    public Set<RequestUser> getRequestUsers() {
        return requestUsers;
    }

    public void setRequestUsers(Set<RequestUser> requestUsers) {
        this.requestUsers = requestUsers;
    }
}
