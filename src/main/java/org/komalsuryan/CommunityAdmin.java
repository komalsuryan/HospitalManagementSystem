package org.komalsuryan;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunityAdmin extends Person {
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
