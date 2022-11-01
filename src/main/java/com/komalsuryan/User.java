package com.komalsuryan;

import java.util.ArrayList;

public interface User {

    default boolean checkEmail(String email, UserTypes userType) {
        if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            return false;
        }
        if (userType == UserTypes.PATIENT) {
            ArrayList<Patient> patients = new Database().getAllPatients();
            for (Patient patient : patients) {
                if (patient.getEmail().equals(email)) {
                    return false;
                }
            }
        } else if (userType == UserTypes.DOCTOR) {
            ArrayList<Doctor> doctors = new Database().getAllDoctors();
            for (Doctor doctor : doctors) {
                if (doctor.getEmail().equals(email)) {
                    return false;
                }
            }
        } else if (userType == UserTypes.COMMUNITY_ADMIN) {
            ArrayList<CommunityAdmin> communityAdmins = new Database().getAllCommunityAdmins();
            for (CommunityAdmin communityAdmin : communityAdmins) {
                if (communityAdmin.getEmail().equals(email)) {
                    return false;
                }
            }
        }
        return true;
    }

    default boolean checkPassword(String password) {
        return password.length() >= 8 && password.length() <= 20;
    }
}
