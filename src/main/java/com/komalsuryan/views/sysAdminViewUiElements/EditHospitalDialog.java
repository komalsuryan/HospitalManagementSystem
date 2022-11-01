package com.komalsuryan.views.sysAdminViewUiElements;

import com.komalsuryan.Community;
import com.komalsuryan.Database;
import com.komalsuryan.Doctor;
import com.komalsuryan.Hospital;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EditHospitalDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel editHospitalHeadingLabel;
    private JLabel editHospitalNameKeyLabel;
    private JLabel editHospitalLocationKeyLabel;
    private JLabel editHospitalCommunityKeyLabel;
    private JTextField editHospitalNameValueField;
    private JTextField editHospitalLocationValueField;
    private JComboBox<String> editHospitalCommunityValueComboBox;
    private JCheckBox editHospitalIsClinicCheckbox;
    private JPanel doctorsPanel;
    private JScrollPane doctorsScrollPane;
    private final Hospital hospital;

    public EditHospitalDialog(Hospital hospital) {
        this.hospital = hospital;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // heading
        editHospitalHeadingLabel.setText("Edit Hospital");

        // labels
        editHospitalNameKeyLabel.setText("Name");
        editHospitalLocationKeyLabel.setText("Location");
        editHospitalCommunityKeyLabel.setText("Community");

        // checkbox
        editHospitalIsClinicCheckbox.setText("Is Clinic");

        // value fields
        editHospitalNameValueField.setText(hospital.getName());
        editHospitalLocationValueField.setText(hospital.getLocation());
        // fill the combo-box with community names from getAllCommunities()
        editHospitalCommunityValueComboBox.setModel(new DefaultComboBoxModel<>(new Database().getAllCommunities().stream().map(Community::getName).toArray(String[]::new)));
        // set the selected item to the community of the hospital
        editHospitalCommunityValueComboBox.setSelectedItem(new Database().getCommunity(hospital.getCommunityId()).getName());
        // set the checkbox to the value of the hospital
        editHospitalIsClinicCheckbox.setSelected(hospital.isClinic());

        // doctors panel
        doctorsPanel.setVisible(false);
        doctorsScrollPane.setVisible(false);

        // buttons
        buttonOK.setText("Edit");
        buttonCancel.setText("Cancel");

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // get the values from the fields
        String name = editHospitalNameValueField.getText();
        String location = editHospitalLocationValueField.getText();
        String communityName = (String) editHospitalCommunityValueComboBox.getSelectedItem();
        boolean isClinic = editHospitalIsClinicCheckbox.isSelected();

        // update the hospital
        hospital.setName(name);
        hospital.setLocation(location);
        hospital.setCommunityId(new Database().getAllCommunities().stream().filter(c -> c.getName().equals(communityName)).findFirst().get().getId());
        hospital.setClinicStatus(isClinic);

        // update the database
        new Database().updateHospital(hospital);

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public void setReadOnly() {
        editHospitalHeadingLabel.setText("View Hospital");
        editHospitalNameValueField.setEditable(false);
        editHospitalLocationValueField.setEditable(false);
        editHospitalCommunityValueComboBox.setEnabled(false);
        editHospitalIsClinicCheckbox.setEnabled(false);
        buttonOK.setVisible(false);

        // show the doctors
        doctorsPanel.setLayout(new BoxLayout(doctorsPanel, BoxLayout.Y_AXIS));
        doctorsPanel.setVisible(true);
        doctorsScrollPane.setVisible(true);
        // create a hashmap of specializations and doctors
        HashMap<String, ArrayList<Doctor>> doctorsBySpecialization = new HashMap<>();
        ArrayList<Doctor> doctorsInHospital = new Database().getAllDoctors();
        doctorsInHospital.removeIf(d -> d.getHospitalId() != hospital.getId());
        for (Doctor doctor : doctorsInHospital) {
            if (doctorsBySpecialization.containsKey(doctor.getSpecialization())) {
                doctorsBySpecialization.get(doctor.getSpecialization()).add(doctor);
            } else {
                ArrayList<Doctor> doctors = new ArrayList<>();
                doctors.add(doctor);
                doctorsBySpecialization.put(doctor.getSpecialization(), doctors);
            }
        }
        // create a label for each specialization
        for (String specialization : doctorsBySpecialization.keySet()) {
            JLabel specializationLabel = new JLabel(specialization);
            // set the font to bold 14 pt
            specializationLabel.setFont(specializationLabel.getFont().deriveFont(14f).deriveFont(java.awt.Font.BOLD));
            doctorsPanel.add(specializationLabel);
            // create a label for each doctor
            for (Doctor doctor : doctorsBySpecialization.get(specialization)) {
                JLabel doctorLabel = new JLabel(doctor.getName());
                doctorsPanel.add(doctorLabel);
            }
        }
    }

    public void setEditHospitalCommunityValueComboBox(ComboBoxModel<String> model) {
        editHospitalCommunityValueComboBox.setModel(model);
    }
}
