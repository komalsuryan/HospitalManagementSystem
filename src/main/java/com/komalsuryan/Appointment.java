package com.komalsuryan;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Appointment {
    private final int id;
    private int patientId;
    private int hospitalId;
    private LocalDate date;
    private int doctorId;
    private LocalTime time;
    private String reason;
    private HashMap<String, String> vitals;
    private String diagnosis;
    private String treatment;
    private static final AtomicInteger count = new AtomicInteger(new Database().getMaxAppointmentId());

    public Appointment(int patientId, int hospitalId, LocalDate date, int doctorId, LocalTime time, String reason, HashMap<String, String> vitals, String diagnosis, String treatment) {
        this.id = count.incrementAndGet();
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.date = date;
        this.doctorId = doctorId;
        this.time = time;
        if (isDoctorAvailable(doctorId, date, time)) {
            this.time = time;
        } else {
            throw new IllegalArgumentException("The doctor is not available at this time");
        }
        if (doesPatientHaveAppointment(patientId, date, time)) {
            this.time = time;
        } else {
            throw new IllegalArgumentException("The patient already has an appointment at this time");
        }
        if (isReasonValid(reason)) {
            this.reason = reason;
        } else {
            throw new IllegalArgumentException("The reason is not valid");
        }
        this.vitals = vitals;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        if (isReasonValid(reason)) {
            this.reason = reason;
        } else {
            throw new IllegalArgumentException("The reason is not valid");
        }
    }

    public HashMap<String, String> getVitals() {
        return vitals;
    }

    public void setVitals(HashMap<String, String> vitals) {
        this.vitals = vitals;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    private boolean isReasonValid(String reason) {
        return reason.length() > 0;
    }

    private boolean isDoctorAvailable(int doctorId, LocalDate date, LocalTime time) {
        Doctor doctor = new Database().getDoctor(doctorId);
        if (date.getDayOfWeek() == doctor.getWeeklyOffDay()) {
            return false;
        }
        if (time.isBefore(doctor.getStartTime()) || time.isAfter(doctor.getEndTime())) {
            return false;
        }
        ArrayList<Appointment> appointments = new Database().getAllAppointments();
        appointments.removeIf(appointment -> appointment.getDoctorId() != doctorId);
        appointments.removeIf(appointment -> appointment.getDate() != date);
        appointments.removeIf(appointment -> appointment.getTime() != time);
        return appointments.size() < doctor.getMaxPatientsPerHour();
    }

    private boolean doesPatientHaveAppointment(int patientId, LocalDate date, LocalTime time) {
        ArrayList<Appointment> appointments = new Database().getAllAppointments();
        appointments.removeIf(appointment -> appointment.getPatientId() != patientId);
        appointments.removeIf(appointment -> appointment.getDate() != date);
        appointments.removeIf(appointment -> appointment.getTime() != time);
        System.out.println(appointments.size());
        return appointments.size() == 0;
    }
}
