package org.komalsuryan;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import javax.print.Doc;
import java.io.File;
import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        // create the systemAdmin.json file if it does not exist
        FileIO.createFile("systemAdmin");
        FileIO.writeFile("systemAdmin", "[{\"username\":\"admin\", \"password\":\"admin\"}]");

        // create the communityAdmins.json file if it does not exist
        FileIO.createFile("communityAdmins");

        // create the communities.json file if it does not exist
        FileIO.createFile("communities");

        // create the hospitals.json file if it does not exist
        FileIO.createFile("hospitals");

        // create the doctors.json file if it does not exist
        FileIO.createFile("doctors");

        // create the patients.json file if it does not exist
        FileIO.createFile("patients");

        // create the appointments.json file if it does not exist
        FileIO.createFile("appointments");
    }

    //region CommunityAdmin Methods
    public ArrayList<CommunityAdmin> getAllCommunityAdmins() {
        // get contents of communityAdmins file as a string
        String contents = FileIO.readFile("communityAdmins");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type communityAdminListType = new TypeToken<ArrayList<CommunityAdmin>>(){}.getType();
        return gson.fromJson(contents, communityAdminListType);
    }

    public CommunityAdmin getCommunityAdmin(int id) {
        ArrayList<CommunityAdmin> communityAdmins = getAllCommunityAdmins();
        for (CommunityAdmin communityAdmin : communityAdmins) {
            if (communityAdmin.getId() == id) {
                return communityAdmin;
            }
        }
        return null;
    }

    public void addCommunityAdmin(CommunityAdmin communityAdmin) {
        // get all communityAdmins
        ArrayList<CommunityAdmin> communityAdmins = getAllCommunityAdmins();

        // add the new communityAdmin
        communityAdmins.add(communityAdmin);

        // write the new communityAdmins to the file
        FileIO.writeFile("communityAdmins", gson.toJson(communityAdmins));
    }

    public void updateCommunityAdmin(CommunityAdmin communityAdmin) {
        // get all communityAdmins
        ArrayList<CommunityAdmin> communityAdmins = getAllCommunityAdmins();

        // find the communityAdmin to update
        for (int i = 0; i < communityAdmins.size(); i++) {
            if (communityAdmins.get(i).getId() == communityAdmin.getId()) {
                communityAdmins.set(i, communityAdmin);
                break;
            }
        }

        // write the new communityAdmins to the file
        FileIO.writeFile("communityAdmins", gson.toJson(communityAdmins));
    }

    public void deleteCommunityAdmin(int id) {
        // get all communityAdmins
        ArrayList<CommunityAdmin> communityAdmins = getAllCommunityAdmins();

        // find the communityAdmin to delete
        for (int i = 0; i < communityAdmins.size(); i++) {
            if (communityAdmins.get(i).getId() == id) {
                communityAdmins.remove(i);
                break;
            }
        }

        // write the new communityAdmins to the file
        FileIO.writeFile("communityAdmins", gson.toJson(communityAdmins));
    }

    public ArrayList<CommunityAdmin> getCommunityAdminsByCommunityId(int communityId) {
        // get all communityAdmins
        ArrayList<CommunityAdmin> communityAdmins = getAllCommunityAdmins();

        // find the communityAdmins with the given communityId
        ArrayList<CommunityAdmin> communityAdminsByCommunityId = new ArrayList<>();
        for (CommunityAdmin communityAdmin : communityAdmins) {
            if (communityAdmin.getCommunityId() == communityId) {
                communityAdminsByCommunityId.add(communityAdmin);
            }
        }

        return communityAdminsByCommunityId;
    }

    public int getMaxCommunityAdminId() {
        // get all communityAdmins
        ArrayList<CommunityAdmin> communityAdmins = getAllCommunityAdmins();

        // find the max id
        int maxId = 0;
        for (CommunityAdmin communityAdmin : communityAdmins) {
            if (communityAdmin.getId() > maxId) {
                maxId = communityAdmin.getId();
            }
        }

        return maxId;
    }
    //endregion

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

    public ArrayList<Doctor> getDoctors(String search) {
        ArrayList<Doctor> doctors = getAllDoctors();
        ArrayList<Doctor> results = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getName().contains(search) || doctor.getSpecialization().contains(search)) {
                results.add(doctor);
            }
        }
        return results;
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

    public ArrayList<Community> getCommunities(String search) {
        ArrayList<Community> communities = getAllCommunities();
        ArrayList<Community> results = new ArrayList<>();
        for (Community community : communities) {
            if (community.getName().toLowerCase().contains(search.toLowerCase())) {
                results.add(community);
            }
        }
        return results;
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

    public ArrayList<Hospital> getHospitals(String search) {
        ArrayList<Hospital> hospitals = getAllHospitals();
        ArrayList<Hospital> results = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            if (hospital.getName().toLowerCase().contains(search.toLowerCase())) {
                results.add(hospital);
            }
        }
        return results;
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
        // do not remove if hospital has doctors or patients
        ArrayList<Doctor> doctors = getAllDoctors();
        for (Doctor doctor : doctors) {
            if (doctor.getHospitalId() == id) {
                throw new RuntimeException("Cannot remove hospital with doctors");
            }
        }
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

    //region Patient Methods
    public ArrayList<Patient> getAllPatients() {
        // get contents of patients file as a string
        String contents = FileIO.readFile("patients");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type patientListType = new TypeToken<ArrayList<Patient>>(){}.getType();
        return gson.fromJson(contents, patientListType);
    }

    public Patient getPatient(int id) {
        ArrayList<Patient> patients = getAllPatients();
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public void addPatient(Patient patient) {
        // get all patients
        ArrayList<Patient> patients = getAllPatients();
        // add new patient
        patients.add(patient);
        // write to file
        FileIO.writeFile("patients", gson.toJson(patients));
    }

    public void removePatient(int id) {
        // get all patients
        ArrayList<Patient> patients = getAllPatients();
        // remove patient with id
        patients.removeIf(patient -> patient.getId() == id);
        // write to file
        FileIO.writeFile("patients", gson.toJson(patients));
    }
    //endregion

    //region Person Methods
    public ArrayList<Person> getAllPeople() {
        // get contents of people file as a string
        String contents = FileIO.readFile("people");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type personListType = new TypeToken<ArrayList<Person>>(){}.getType();
        return gson.fromJson(contents, personListType);
    }

    public Person getPerson(String ssNumber) {
        ArrayList<Person> people = getAllPeople();
        for (Person person : people) {
            if (person.getSsNumber().equals(ssNumber)) {
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person) {
        // get all people
        ArrayList<Person> people = getAllPeople();
        // add new person
        people.add(person);
        // write to file
        FileIO.writeFile("people", gson.toJson(people));
    }

    public void removePerson(String ssNumber) {
        // get all people
        ArrayList<Person> people = getAllPeople();
        // remove person with ssNumber
        people.removeIf(person -> person.getSsNumber().equals(ssNumber));
        // write to file
        FileIO.writeFile("people", gson.toJson(people));
    }
    //endregion

    //region Appointment Methods
    public ArrayList<Appointment> getAllAppointments() {
        // get contents of appointments file as a string
        String contents = FileIO.readFile("appointments");
        if (contents.isEmpty() || contents.equals("null")) {
            return new ArrayList<>();
        }
        Type appointmentListType = new TypeToken<ArrayList<Appointment>>(){}.getType();
        return gson.fromJson(contents, appointmentListType);
    }

    public Appointment getAppointment(int id) {
        ArrayList<Appointment> appointments = getAllAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null;
    }

    public void addAppointment(Appointment appointment) {
        // get all appointments
        ArrayList<Appointment> appointments = getAllAppointments();
        // add new appointment
        appointments.add(appointment);
        // write to file
        FileIO.writeFile("appointments", gson.toJson(appointments));
    }

    public void removeAppointment(int id) {
        // get all appointments
        ArrayList<Appointment> appointments = getAllAppointments();
        // remove appointment with id
        appointments.removeIf(appointment -> appointment.getId() == id);
        // write to file
        FileIO.writeFile("appointments", gson.toJson(appointments));
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

class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDate.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDate.parse(jsonElement.getAsString());
    }
}
