package com.komalsuryan;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunityAdmin extends Person implements User {
    private final int id;
    private String email;
    private String password;
    private static final AtomicInteger count = new AtomicInteger(new Database().getMaxCommunityAdminId());

    public CommunityAdmin(String ssNumber, String name, int communityId, LocalDate dateOfBirth, String sex, float height, float weight, String email, String password) {
        super(ssNumber, name, communityId, dateOfBirth, sex, height, weight);
        this.id = count.incrementAndGet();
        this.email = email;
        this.password = password;
    }

    public CommunityAdmin(String ssNumber, String email, String password) {
        super(ssNumber, null, 0, null, null, 0, 0);
        Person person = new Database().getPerson(ssNumber);
        if (person == null) {
            throw new RuntimeException("No person with SSN " + ssNumber + " found. Please register first.");
        }
        super.setName(person.getName());
        super.setCommunityId(person.getCommunityId());
        super.setDateOfBirth(person.getDateOfBirth());
        super.setSex(person.getSex());
        super.setHeight(person.getHeight());
        super.setWeight(person.getWeight());
        this.id = count.incrementAndGet();
        if (checkEmail(email, UserTypes.COMMUNITY_ADMIN)) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
