package org.komalsuryan;

import java.time.LocalDate;

public class Person {
    private String ssNumber;
    private String name;
    private String communityId;
    private LocalDate dateOfBirth;
    private String sex;
    private float height;
    private float weight;

    public Person(String ssNumber, String name, String communityId, LocalDate dateOfBirth, String sex, float height, float weight) {
        this.ssNumber = ssNumber;
        this.name = name;
        this.communityId = communityId;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
    }

    public String getSsNumber() {
        return ssNumber;
    }

    public void setSsNumber(String ssNumber) {
        this.ssNumber = ssNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
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
}
