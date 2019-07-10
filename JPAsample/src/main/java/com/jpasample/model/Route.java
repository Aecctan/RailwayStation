package com.jpasample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue
    @Column(name = "ROUTE_ID")
    private long id;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JsonIgnore
    private City dispatch_city;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JsonIgnore
    private City destination_city;

    @ManyToOne
    @JsonIgnore
    private Train train;

    public Route(City dispatch_city, City destination_city, Train train) {
        this.destination_city = destination_city;
        this.dispatch_city = dispatch_city;
        this.train = train;
    }

    public Route() {
        this.destination_city = null;
        this.dispatch_city = null;
        this.train = null;
    }

    public City getDispatch_city() {
        return dispatch_city;
    }

    public void setDispatch_city(City city) {
        this.dispatch_city = city;
    }

    public City getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(City city) {
        this.destination_city = city;
    }

    public Train getTrain() {return train;}

    public void setTrain(Train train) {this.train = train;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDispatchcity(){ return this.dispatch_city.getCity();}
    public String getDestinationcity(){ return this.destination_city.getCity();}
    public String getTrainStr(){ return this.train.getModel();}
    public String getThisRoute() {return "Маршрут " + dispatch_city.getCity() + " - " + destination_city.getCity() + "!";}
    @Override
    public String toString() {
        return super.toString() + " {" + "id=" + id + " dispatch=" + dispatch_city + " destination=" + destination_city + '}';
    }


}
