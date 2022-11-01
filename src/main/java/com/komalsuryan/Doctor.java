package com.komalsuryan;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Doctor implements User {
    private static final AtomicInteger count = new AtomicInteger(new Database().getMaxDoctorId());
    private final int id;
    private String name;
    private String specialization;
    private int hospitalId;
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxPatientsPerHour;
    private DayOfWeek weeklyOffDay;
    private String email;
    private String password;

    public Doctor(String name, String specialization, int hospitalId, LocalTime startTime, LocalTime endTime, int maxPatientsPerHour, DayOfWeek weeklyOffDay, String email, String password) {
        this.id = count.incrementAndGet();
        if (checkName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name. Name can only contain alphabets, spaces, hyphen, comma and dots.");
        }
        this.specialization = specialization;
        if (checkHospitalId(hospitalId)) {
            this.hospitalId = hospitalId;
        } else {
            throw new IllegalArgumentException("Invalid hospital ID. No hospital with ID " + hospitalId + " found.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPatientsPerHour = maxPatientsPerHour;
        this.weeklyOffDay = weeklyOffDay;
        if (checkEmail(email, UserTypes.DOCTOR)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email or email already exists.");
        }
        if (checkPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password. Password must be at between 8 and 20 characters long.");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (checkName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name. Name can only contain alphabets, spaces, hyphen, comma and dots.");
        }
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
        if (checkHospitalId(hospitalId)) {
            this.hospitalId = hospitalId;
        } else {
            throw new IllegalArgumentException("Invalid hospital ID. No hospital with ID " + hospitalId + " found.");
        }
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getMaxPatientsPerHour() {
        return maxPatientsPerHour;
    }

    public void setMaxPatientsPerHour(int maxPatientsPerHour) {
        this.maxPatientsPerHour = maxPatientsPerHour;
    }

    public DayOfWeek getWeeklyOffDay() {
        return weeklyOffDay;
    }

    public void setWeeklyOffDay(DayOfWeek weeklyOffDay) {
        this.weeklyOffDay = weeklyOffDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (checkEmail(email, UserTypes.DOCTOR)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email. Email must be in the format of x@y.z");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (checkPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password. Password must be at between 8 and 20 characters long.");
        }
    }

    private boolean checkName(String name) {
        return name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    private boolean checkHospitalId(int hospitalId) {
        return new Database().getHospital(hospitalId) != null;
    }
}
