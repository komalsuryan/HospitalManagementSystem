package org.komalsuryan;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Patient extends Person {
    private final int id;
    private static final AtomicInteger count = new AtomicInteger(new Database().getAllPatients().size());
    public Patient(String ssNumber, String name, int communityId, LocalDate dateOfBirth, String sex, float height, float weight) {
        super(ssNumber, name, communityId, dateOfBirth, sex, height, weight);
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }
}
