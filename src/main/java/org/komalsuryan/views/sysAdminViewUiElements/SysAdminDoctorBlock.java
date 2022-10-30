package org.komalsuryan.views.sysAdminViewUiElements;

import org.komalsuryan.Database;
import org.komalsuryan.Doctor;

import javax.swing.*;

public class SysAdminDoctorBlock {
    private JPanel mainPanel;
    private JLabel doctorNameLabel;
    private JButton viewDoctorbutton;
    private JButton editDoctorButton;
    private JButton deleteDoctorButton;
    private JLabel doctorSpecializationLabel;
    private JLabel doctorHospitalLabel;
    private JLabel doctorHoursAvailable;
    private JLabel doctorSpecializationValue;
    private JLabel doctorHospitalValue;
    private JLabel doctorHoursAvailableValue;

    public SysAdminDoctorBlock(Doctor doctor) {
        doctorNameLabel.setText(doctor.getName());
        doctorSpecializationLabel.setText("Specialization");
        doctorSpecializationValue.setText(doctor.getSpecialization());
        doctorHospitalLabel.setText("Hospital");
        doctorHospitalValue.setText(new Database().getHospital(doctor.getHospitalId()).getName());
        doctorHoursAvailable.setText("Hours available");
        doctorHoursAvailableValue.setText(doctor.getStartTime().toString() + " - " + doctor.getEndTime().toString());
        viewDoctorbutton.setText("View Details");
        editDoctorButton.setText("Edit");
        deleteDoctorButton.setText("Delete");
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

}
