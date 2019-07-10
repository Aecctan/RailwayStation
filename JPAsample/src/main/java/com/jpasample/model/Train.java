package com.jpasample.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Train {
    @Id
    @GeneratedValue
    @Column(name = "TRAIN_ID")
    private long id;

    @Column(name = "MODEL")
    private String model;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "train", cascade={CascadeType.PERSIST})
    private List<Route> routes = new ArrayList<Route>();

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public void correctPlaces(int correction) {
        this.places = places+correction;
    }


    @Column(name = "PLACES")
    private int places;

    public Train(String model) {
        this.model = model;
        this.places = 100;
    }
    public Train(String model, int places) {
        this.model = model;
        this.places = places;
    }

    public Train() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {this.routes = routes;}

    @Override
    public String toString() {
        return super.toString() + " {" + "id=" + id + " model=" + model + " places=" + places + '}';
    }

}
