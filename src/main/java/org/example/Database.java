package org.example;

import java.io.File;

public class Database {
    private static final String PATH = System.getProperty("user.home") + "/.hms/";

    public Database() {
        // if the directory does not exist, create it
        File directory = new File(PATH);
        if (!directory.exists()) {
            if(!directory.mkdir()) {
                throw new RuntimeException("Could not create directory: " + PATH);
            }
        }
        // create the JSON files if they do not exist
        File doctors = new File(PATH + "doctors.json");
    }

    public void addObject(UserTypes type, Object object) {
        if (type == UserTypes.DOCTOR) {
            // add doctor to doctors.json
        } else if (type == UserTypes.PATIENT) {
            // add patient to patients.json
        } else if (type == UserTypes.SYSTEM_ADMIN) {
            // add system admin to system_admins.json
        } else if (type == UserTypes.COMMUNITY_ADMIN) {
            // add community admin to community_admins.json
        }
    }

    public static void main(String[] args) {
        Database db = new Database();
    }
}
