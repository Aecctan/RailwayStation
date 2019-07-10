package com.jpasample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import javax.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue
    @Column(name = "PASSENGER_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    private String password;

    public Passenger() {
    }

    public Passenger(String name) {
        this.name = name;
        this.tickets = null;
        this.password = name;
    }
    public Passenger(String name, String password) {
        this.name = name;
        this.tickets = null;
        this.password = password;
    }

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "owner", cascade={CascadeType.PERSIST})
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {this.tickets = tickets;}

    public void addTicket(Ticket t) {this.tickets.add(t);}

    public void deleteTicket(Ticket t) {this.tickets.remove(t);}

    public String getTicketIds() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tickets: ");
        for (Ticket t : tickets) {
            sb.append(t.getId());
            sb.append(' ');
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return super.toString()+" {" + "id=" + id + " name=" + name + '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}