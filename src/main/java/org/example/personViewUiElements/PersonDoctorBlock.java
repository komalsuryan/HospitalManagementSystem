package org.example.personViewUiElements;

import org.example.Doctor;

import javax.swing.*;

public class PersonDoctorBlock {
    private JPanel mainPanel;
    private JLabel doctorNameLabel;
    private JPanel doctorInfoPanel;
    private JButton viewBookDoctorButton;
    private JPanel doctorInfoKeysPanel;
    private JPanel doctorInfoValuesPanel;
    private JLabel doctorInfoSpecializationKeyLabel;
    private JLabel doctorInfoLocationKeyLabel;
    private JLabel doctorInfoSpecializationValueLabel;
    private JLabel doctorInfoLocationValueLabel;

    public PersonDoctorBlock(Doctor doctor) {
        doctorNameLabel.setText(doctor.getName());
        doctorInfoSpecializationKeyLabel.setText("Specialization");
        doctorInfoSpecializationValueLabel.setText(doctor.getSpecialization());
        doctorInfoLocationKeyLabel.setText("Where to find");
        doctorInfoLocationValueLabel.setText("Hospital " + doctor.getHospitalId());
        viewBookDoctorButton.setText("View/Book Appointment");
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
