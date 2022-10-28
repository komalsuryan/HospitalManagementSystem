package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Database {
    private static final String PATH = System.getProperty("user.home") + "/.hms/";
    private final Gson gson;

    public Database() {
        // if the directory does not exist, create it
        File directory = new File(PATH);
        if (!directory.exists()) {
            if(!directory.mkdir()) {
                throw new RuntimeException("Could not create directory: " + PATH);
            }
        }

        // create the doctors.json file if it does not exist
        File doctorsFile = new File(PATH + "doctors.json");
        if (!doctorsFile.exists()) {
            try {
                if(!doctorsFile.createNewFile()) {
                    throw new RuntimeException("Could not create file: " + PATH + "doctors.json");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // initialize gson
        gson = new Gson();
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

    public ArrayList<Doctor> getAllDoctors() throws FileNotFoundException {
        // get contents of doctors file as a string
        String contents = FileIO.readFile("doctors");
        System.out.println(contents);
        if (contents == null || contents.isEmpty()) {
            return new ArrayList<>();
        }
        Type doctorListType = new TypeToken<ArrayList<Doctor>>(){}.getType();
        return gson.fromJson(contents, doctorListType);
    }

    public void addDoctor(Doctor doctor) throws IOException {
        // get all doctors
        ArrayList<Doctor> doctors = getAllDoctors();
        // add new doctor
        doctors.add(doctor);
        // write to file
        FileIO.writeFile("doctors", gson.toJson(doctors));
    }

    public static void main(String[] args) throws IOException {
        Database db = new Database();
        db.addDoctor(new Doctor(1, "Suyash Saxena", "Ornithology", 1));
    }
}
