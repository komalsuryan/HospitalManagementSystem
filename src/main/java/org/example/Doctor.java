package org.example;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private int hospitalId;

    public Doctor(int id, String name, String specialization, int hospitalId) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.hospitalId = hospitalId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

}
