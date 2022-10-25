package org.example;

public class Hospital {
    private int id;
    private String Name;
    private String location;
    private int communityId;


    public Hospital(int id, String name, String location, int communityId) {
        this.id = id;
        Name = name;
        this.location = location;
        this.communityId = communityId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }
}
