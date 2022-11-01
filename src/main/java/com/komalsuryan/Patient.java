package com.komalsuryan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Patient extends Person implements User {
    private final int id;
    private String email;
    private String password;
    private static final AtomicInteger count = new AtomicInteger(new Database().getMaxPatientId());
    public Patient(String ssNumber, String name, int communityId, LocalDate dateOfBirth, String sex, float height, float weight, String email, String password) {
        super(ssNumber, name, communityId, dateOfBirth, sex, height, weight);
        this.id = count.incrementAndGet();
        if (checkEmail(email, UserTypes.PATIENT)) {
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

    public Patient(Person person, String email, String password) {
        super(person);
        this.id = count.incrementAndGet();
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (checkEmail(email, UserTypes.PATIENT)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email or email already exists.");
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
}
