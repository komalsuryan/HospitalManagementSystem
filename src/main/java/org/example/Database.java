package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
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

        // initialize gson
        gson = new Gson();

        // create the doctors.json file if it does not exist
        File doctorsFile = new File(PATH + "doctors.json");
        if (!doctorsFile.exists()) {
            try {
                if(!doctorsFile.createNewFile()) {
                    throw new RuntimeException("Could not create file: " + PATH + "doctors.json");
                }
                FileIO.writeFile("doctors", "[]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Doctor> getAllDoctors() throws IOException {
        // get contents of doctors file as a string
        String contents = FileIO.readFile("doctors");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type doctorListType = new TypeToken<ArrayList<Doctor>>(){}.getType();
        return gson.fromJson(contents, doctorListType);
    }

    public Doctor getDoctor(int id) throws IOException {
        ArrayList<Doctor> doctors = getAllDoctors();
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    public void addDoctor(Doctor doctor) throws IOException {
        // get all doctors
        ArrayList<Doctor> doctors = getAllDoctors();
        // add new doctor
        doctors.add(doctor);
        // write to file
        FileIO.writeFile("doctors", gson.toJson(doctors));
    }

    public void removeDoctor(int id) throws IOException {
        // get all doctors
        ArrayList<Doctor> doctors = getAllDoctors();
        // remove doctor with id
        doctors.removeIf(doctor -> doctor.getId() == id);
        // write to file
        FileIO.writeFile("doctors", gson.toJson(doctors));
    }

    public void updateDoctor(Doctor doctor) throws IOException {
        // get all doctors
        ArrayList<Doctor> doctors = getAllDoctors();
        // update doctor
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == doctor.getId()) {
                doctors.set(i, doctor);
                break;
            }
        }
        // write to file
        FileIO.writeFile("doctors", gson.toJson(doctors));
    }
}
