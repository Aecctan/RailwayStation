package com.jpasample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class City {
    @Id
    @GeneratedValue
    @Column(name = "CITY_ID")
    private long id;

    @Column(name = "CITY")
    private String city;

    public City(String city) {
        this.city = city;
    }

    public City() {
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() + " {" + "id=" + id + " city=" + city + '}';
    }

}
