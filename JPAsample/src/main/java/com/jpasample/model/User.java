package com.jpasample.model;

public class User extends Passenger{

    private String name;
    private long id;
    private String password;
    public User (String name)
    {
        this.name = name;
        this.password = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
