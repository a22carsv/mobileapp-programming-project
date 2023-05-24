package com.example.project;

public class City {
    private String ID;
    private String login;
    private String size;
    private String location;
    private String name;

    public City(String ID, String login, String size, String location, String name) {
        this.ID = ID;
        this.login = login;
        this.size = size;
        this.location = location;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public String getSize() {
        return size;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
