package org.zkoss.reference.developer.spring.security.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    //TODO: Опечатка
    @Column(name = "MIDLE_NAME", nullable = false)
    private String midleName;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PHONE", unique = true, nullable = false)
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Request> orders = new HashSet<Request>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Request> getOrders() {
        return orders;
    }

    public void setOrders(Set<Request> orders) {
        this.orders = orders;
    }
}
