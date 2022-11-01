package com.komalsuryan.views.sysAdminViewUiElements;

import com.komalsuryan.Patient;

import javax.swing.*;

public class SysAdminPatientBlock {
    private JPanel mainPanel;
    private JLabel patientNameLabel;
    private JLabel patientSsnLabel;
    private JLabel patientEmailLabel;
    private JLabel patientSsnValue;
    private JLabel patientEmailValue;
    private JButton editPatientButton;
    private JButton deletePatientButton;

    public SysAdminPatientBlock(Patient patient) {
        // heading
        patientNameLabel.setText(patient.getName());

        // lables
        patientSsnLabel.setText("SSN: ");
        patientEmailLabel.setText("Email: ");

        // values
        patientSsnValue.setText(patient.getSsNumber());
        patientEmailValue.setText(patient.getEmail());

        // buttons
        editPatientButton.setText("Edit");
        deletePatientButton.setText("Delete");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getEditButton() {
        return editPatientButton;
    }

    public JButton getDeleteButton() {
        return deletePatientButton;
    }
}
