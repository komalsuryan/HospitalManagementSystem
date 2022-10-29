package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Doctor {
    private final int id;
    private String name;
    private String specialization;
    private int hospitalId;
    private static final AtomicInteger count = new AtomicInteger(0);

    public Doctor(String name, String specialization, int hospitalId) {
        this.id = count.incrementAndGet();
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
