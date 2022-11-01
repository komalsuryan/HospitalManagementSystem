package com.komalsuryan;

import java.time.LocalDate;

public class Person {
    private String ssNumber;
    private String name;
    private int communityId;
    private LocalDate dateOfBirth;
    private String sex;
    private float height;
    private float weight;

    public Person(String ssNumber, String name, int communityId, LocalDate dateOfBirth, String sex, float height, float weight) {
        if (checkSsNumber(ssNumber)) {
            this.ssNumber = ssNumber;
        } else {
            throw new IllegalArgumentException("Invalid identity number. ID must be 9 digits only.");
        }
        if (checkName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name. Name can only contain alphabets, spaces, hyphen, comma and dots.");
        }
        if (checkCommunityId(communityId)) {
            this.communityId = communityId;
        } else {
            throw new IllegalArgumentException("Invalid community ID. No community with ID " + communityId + " found.");
        }
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
    }

    public Person(Person person) {
        this.ssNumber = person.getSsNumber();
        this.name = person.getName();
        this.communityId = person.getCommunityId();
        this.dateOfBirth = person.getDateOfBirth();
        this.sex = person.getSex();
        this.height = person.getHeight();
        this.weight = person.getWeight();
    }

    public String getSsNumber() {
        return ssNumber;
    }

    public void setSsNumber(String ssNumber) {
        if (checkSsNumber(ssNumber)) {
            this.ssNumber = ssNumber;
        } else {
            throw new IllegalArgumentException("Invalid identity number. ID must be 9 digits only.");
        }
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

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        if (checkCommunityId(communityId)) {
            this.communityId = communityId;
        } else {
            throw new IllegalArgumentException("Invalid community ID. No community with ID " + communityId + " found.");
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    private boolean checkCommunityId(int communityId) {
        return new Database().getCommunity(communityId) != null;
    }

    private boolean checkSsNumber(String ssNumber) {
        // match for SSN format: 9 numbers only
        return ssNumber.matches("\\d{9}");
    }

    private boolean checkName(String name) {
        return name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }
}
