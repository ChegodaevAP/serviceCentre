package org.zkoss.reference.developer.spring.security.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "order_user_id")
    private Integer orderUserId;
    @Column(name = "status_history_id")
    private Integer statusHistoryId;
    @Column(name = "status_history_status_id")
    private Integer statusHistoryStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klient_id", nullable = false)
    private Client client;

    //TODO: Опечатка
    @OneToMany(mappedBy = "order")
    private Set<MuvementHistory> muvementHistory;

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

    public Set<MuvementHistory> getMuvementHistory() {
        return muvementHistory;
    }

    public void setMuvementHistory(Set<MuvementHistory> muvementHistory) {
        this.muvementHistory = muvementHistory;
    }

    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    public Integer getStatusHistoryId() {
        return statusHistoryId;
    }

    public void setStatusHistoryId(Integer statusHistoryId) {
        this.statusHistoryId = statusHistoryId;
    }

    public Integer getStatusHistoryStatusId() {
        return statusHistoryStatusId;
    }

    public void setStatusHistoryStatusId(Integer statusHistoryStatusId) {
        this.statusHistoryStatusId = statusHistoryStatusId;
    }
}
