package org.komalsuryan;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalTime;
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

        // initialize gson with custom adapters
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(DayOfWeek.class, new DayOfWeekAdapter())
                .setPrettyPrinting()
                .create();

        // create the communities.json file if it does not exist
        FileIO.createFile("communities");

        // create the hospitals.json file if it does not exist
        FileIO.createFile("hospitals");

        // create the doctors.json file if it does not exist
        FileIO.createFile("doctors");
    }

    //region Doctor Methods
    public ArrayList<Doctor> getAllDoctors() {
        // get contents of doctors file as a string
        String contents = FileIO.readFile("doctors");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type doctorListType = new TypeToken<ArrayList<Doctor>>(){}.getType();
        return gson.fromJson(contents, doctorListType);
    }

    public Doctor getDoctor(int id) {
        ArrayList<Doctor> doctors = getAllDoctors();
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    public void addDoctor(Doctor doctor) {
        // get all doctors
        ArrayList<Doctor> doctors = getAllDoctors();
        // add new doctor
        doctors.add(doctor);
        // write to file
        FileIO.writeFile("doctors", gson.toJson(doctors));
    }

    public void removeDoctor(int id) {
        // get all doctors
        ArrayList<Doctor> doctors = getAllDoctors();
        // remove doctor with id
        doctors.removeIf(doctor -> doctor.getId() == id);
        // write to file
        FileIO.writeFile("doctors", gson.toJson(doctors));
    }

    public void updateDoctor(Doctor doctor) {
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
    //endregion

    //region Community Methods
    public ArrayList<Community> getAllCommunities() {
        // get contents of communities file as a string
        String contents = FileIO.readFile("communities");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type communityListType = new TypeToken<ArrayList<Community>>(){}.getType();
        return gson.fromJson(contents, communityListType);
    }

    public Community getCommunity(int id) {
        ArrayList<Community> communities = getAllCommunities();
        for (Community community : communities) {
            if (community.getId() == id) {
                return community;
            }
        }
        return null;
    }

    public void addCommunity(Community community) {
        // get all communities
        ArrayList<Community> communities = getAllCommunities();
        // add new community
        communities.add(community);
        // write to file
        FileIO.writeFile("communities", gson.toJson(communities));
    }

    public void removeCommunity(int id) {
        // get all communities
        ArrayList<Community> communities = getAllCommunities();
        // remove community with id
        communities.removeIf(community -> community.getId() == id);
        // write to file
        FileIO.writeFile("communities", gson.toJson(communities));
    }

    public void updateCommunity(Community community) {
        // get all communities
        ArrayList<Community> communities = getAllCommunities();
        // update community
        for (int i = 0; i < communities.size(); i++) {
            if (communities.get(i).getId() == community.getId()) {
                communities.set(i, community);
                break;
            }
        }
        // write to file
        FileIO.writeFile("communities", gson.toJson(communities));
    }
    //endregion

    //region Hospital Methods
    public ArrayList<Hospital> getAllHospitals() {
        // get the contents of hospitals files as a string
        String contents = FileIO.readFile("hospitals");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type hospitalListType = new TypeToken<ArrayList<Hospital>>(){}.getType();
        return gson.fromJson(contents, hospitalListType);
    }

    public Hospital getHospital(int hospitalId) {
        ArrayList<Hospital> hospitals = getAllHospitals();
        for (Hospital hospital: hospitals) {
            if (hospital.getId() == hospitalId) {
                return hospital;
            }
        }
        return null;
    }

    public void addHospital(Hospital hospital) {
        // get all hospitals
        ArrayList<Hospital> hospitals = getAllHospitals();
        // add new hospital
        hospitals.add(hospital);
        // write to file
        FileIO.writeFile("hospitals", gson.toJson(hospitals));
    }

    public void removeHospital(int id) {
        // get all hospitals
        ArrayList<Hospital> hospitals = getAllHospitals();
        // remove hospital with id
        hospitals.removeIf(hospital -> hospital.getId() == id);
        // write to file
        FileIO.writeFile("hospitals", gson.toJson(hospitals));
    }

    public void updateHospital(Hospital hospital) {
        // get all communities
        ArrayList<Hospital> hospitals = getAllHospitals();
        // update community
        for (int i = 0; i < hospitals.size(); i++) {
            if (hospitals.get(i).getId() == hospital.getId()) {
                hospitals.set(i, hospital);
                break;
            }
        }
        // write to file
        FileIO.writeFile("hospitals", gson.toJson(hospitals));
    }
    //endregion
}

class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
    @Override
    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localTime.toString());
    }

    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalTime.parse(jsonElement.getAsString());
    }
}

class DayOfWeekAdapter implements JsonSerializer<DayOfWeek>, JsonDeserializer<DayOfWeek> {
    @Override
    public JsonElement serialize(DayOfWeek dayOfWeek, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dayOfWeek.toString());
    }

    @Override
    public DayOfWeek deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return DayOfWeek.valueOf(jsonElement.getAsString());
    }
}
