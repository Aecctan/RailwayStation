package com.jpasample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    @Column(name = "TICKET_ID")
    private long id; // identifier

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JsonIgnore
    private Passenger owner;

    @ManyToOne
    @JsonIgnore
    private Route route;

    public Passenger getOwner() {
        return owner;
    }

    public void setOwner(Passenger owner) {
        this.owner = owner;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Ticket() {
    }
    public Ticket(Passenger owner) {
        this.owner= owner;
    }
    public Ticket(Route route, Passenger owner) {
        this.route = route;
        this.owner= owner;
    }
    public String getOwnerStr(){return owner.getName();}
    public String getRouteStr() {return route.getThisRoute();}
    @Override
    public String toString() {
        return super.toString()+ "{ id=" + id + " owner=" + owner  + " route=" + route;
    }
}
