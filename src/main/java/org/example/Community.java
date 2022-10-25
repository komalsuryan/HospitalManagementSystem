package org.example;

public class Community {
    private int ID;
    private String Name;
    private String City;

    public Community(int ID, String name, String city) {
        this.ID = ID;
        Name = name;
        City = city;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}


