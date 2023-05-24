package com.example.project;

public class City {
    private String id;
    private String login;
    private String size;
    private String location;
    private String name;

    public City(String id, String login, String size, String location, String name) {
        this.id = id;
        this.login = login;
        this.size = size;
        this.location = location;
        this.name = name;
    }

    public String getId() {
        return id;
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