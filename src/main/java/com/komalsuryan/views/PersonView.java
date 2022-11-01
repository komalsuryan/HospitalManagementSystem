package com.komalsuryan.views;

import com.komalsuryan.Community;
import com.komalsuryan.Database;
import com.komalsuryan.Doctor;
import com.komalsuryan.Hospital;
import com.komalsuryan.views.personViewUiElements.PersonDoctorBlock;
import com.komalsuryan.views.sysAdminViewUiElements.EditHospitalDialog;
import com.komalsuryan.views.sysAdminViewUiElements.SysAdminHospitalBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PersonView {
    private JLabel welcomeLabel;
    private JButton logoutPersonButton;
    private JPanel personViewMainPanel;
    private JPanel personViewHeadingPanel;
    private JLabel findDoctorsLabel;
    private JScrollPane findDoctorsScrollPane;
    private JPanel findDoctorsPanel;
    private JPanel findDoctorsTopPanel;
    private JPanel findDoctorsSearchPanel;
    private JLabel findDoctorsSearchLabel;
    private JTextField findDoctorsSearchText;
    private JComboBox<String> communitySelectComboBox;
    private JLabel communitySelectLabel;
    private JLabel findHospitalsLabel;
    private JLabel findHospitalsSearchLabel;
    private JTextField findHospitalsSearchText;
    private JPanel findHospitalsPanel;
    private final Database db;

    public PersonView() {
        db = new Database();
        welcomeLabel.setText("Welcome, Find doctors at your location");
        communitySelectLabel.setText("Select Community");
        // populate the community select combo box
        communitySelectComboBox.addItem("Anywhere");
        ArrayList<Community> communities = db.getAllCommunities();
        for (Community community : communities) {
            communitySelectComboBox.addItem(community.getName());
        }
        logoutPersonButton.setText("Logout");
        logoutPersonButton.addActionListener(e -> {
            JFrame jFrame = new JFrame("Hospital Management System");
            jFrame.setContentPane(new LoginView(jFrame).getMainPanel());
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLocationRelativeTo(null);
            jFrame.pack();
            jFrame.setVisible(true);
            // close the current window
            SwingUtilities.getWindowAncestor(personViewMainPanel).dispose();
        });

        findDoctorsLabel.setText("Find Doctors");
        findDoctorsSearchLabel.setText("Search");

        findHospitalsLabel.setText("Find Hospitals");
        findHospitalsSearchLabel.setText("Search");

        final int[] communityId = {0};
        communitySelectComboBox.addActionListener(e -> {
            findDoctorsSearchText.setText("");
            findHospitalsSearchText.setText("");
            String communityName = (String) communitySelectComboBox.getSelectedItem();
            if (communityName == null || communityName.equals("Anywhere")) {
                createDoctorBlocks(0);
                createHospitalBlocks(0);
                return;
            }
            communityId[0] = db.getAllCommunities().stream().filter(community -> community.getName().equals(communityName)).findFirst().get().getId();
            createDoctorBlocks(communityId[0]);
            createHospitalBlocks(communityId[0]);
        });
        communitySelectComboBox.setSelectedItem("Anywhere");

        findDoctorsSearchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String searchQuery = findDoctorsSearchText.getText();
                if (searchQuery.isEmpty()) {
                    createDoctorBlocks(communityId[0]);
                    return;
                }
                createDoctorBlocks(communityId[0], searchQuery);
            }
        });

        findHospitalsSearchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String searchQuery = findHospitalsSearchText.getText();
                if (searchQuery.isEmpty()) {
                    createHospitalBlocks(communityId[0]);
                    return;
                }
                createHospitalBlocks(communityId[0], searchQuery);
            }
        });
    }

    private void createHospitalBlocks(int communityId) {
        findHospitalsPanel.removeAll();
        ArrayList<Hospital> hospitals = db.getAllHospitals();
        if (communityId != 0) {
            hospitals.removeIf(hospital -> hospital.getCommunityId() != communityId);
        }
        if (hospitals.size() == 0) {
            findHospitalsPanel.setLayout(new GridLayout(0, 1));
            findHospitalsPanel.add(new JLabel("No hospitals found"));
            findHospitalsPanel.revalidate();
            findHospitalsPanel.repaint();
            return;
        }
        findHospitalsPanel.setLayout(new GridLayout(0, 3));
        for (Hospital hospital : hospitals) {
            SysAdminHospitalBlock sysAdminHospitalBlock = new SysAdminHospitalBlock(hospital);
            sysAdminHospitalBlock.getDeleteButton().setVisible(false);
            sysAdminHospitalBlock.getEditButton().setText("View");
            sysAdminHospitalBlock.getEditButton().addActionListener(e -> {
                EditHospitalDialog editHospitalDialog = new EditHospitalDialog(hospital);
                editHospitalDialog.setReadOnly();
                editHospitalDialog.pack();
                editHospitalDialog.setLocationRelativeTo(null);
                editHospitalDialog.setVisible(true);
            });
            findHospitalsPanel.add(sysAdminHospitalBlock.getMainPanel());
        }
        findHospitalsPanel.revalidate();
        findHospitalsPanel.repaint();
    }

    private void createHospitalBlocks(int communityId, String searchQuery) {
        findHospitalsPanel.removeAll();
        ArrayList<Hospital> hospitals = db.getHospitals(searchQuery);
        if (communityId != 0) {
            hospitals.removeIf(hospital -> hospital.getCommunityId() != communityId);
        }
        if (hospitals.size() == 0) {
            findHospitalsPanel.setLayout(new GridLayout(0, 1));
            findHospitalsPanel.add(new JLabel("No hospitals found"));
            findHospitalsPanel.revalidate();
            findHospitalsPanel.repaint();
            return;
        }
        findHospitalsPanel.setLayout(new GridLayout(0, 3));
        for (Hospital hospital : hospitals) {
            SysAdminHospitalBlock sysAdminHospitalBlock = new SysAdminHospitalBlock(hospital);
            findHospitalsPanel.add(sysAdminHospitalBlock.getMainPanel());
            sysAdminHospitalBlock.getDeleteButton().setVisible(false);
            sysAdminHospitalBlock.getEditButton().setText("View");
            sysAdminHospitalBlock.getEditButton().addActionListener(e -> {
                EditHospitalDialog editHospitalDialog = new EditHospitalDialog(hospital);
                editHospitalDialog.setReadOnly();
                editHospitalDialog.pack();
                editHospitalDialog.setLocationRelativeTo(null);
                editHospitalDialog.setVisible(true);
            });
        }
        findHospitalsPanel.revalidate();
        findHospitalsPanel.repaint();
    }

    private void createDoctorBlocks(int communityId) {
        findDoctorsPanel.removeAll();
        findDoctorsPanel.revalidate();
        findDoctorsPanel.repaint();
        ArrayList<Doctor> doctors = db.getAllDoctors();
        if (communityId != 0) {
            doctors.removeIf(doctor -> db.getHospital(doctor.getHospitalId()).getCommunityId() != communityId);
        }
        if (doctors.size() == 0) {
            findDoctorsPanel.setLayout(new GridLayout(0, 1));
            findDoctorsPanel.add(new JLabel("No doctors found"));
            findDoctorsPanel.revalidate();
            findDoctorsPanel.repaint();
            return;
        }
        findDoctorsPanel.setLayout(new GridLayout(0, 3));
        for (Doctor doctor : doctors) {
            PersonDoctorBlock doctorBlock = new PersonDoctorBlock(doctor);
            findDoctorsPanel.add(doctorBlock.getMainPanel());
        }
        findDoctorsPanel.revalidate();
        findDoctorsPanel.repaint();
    }

    private void createDoctorBlocks(int communityId, String search) {
        findDoctorsPanel.removeAll();
        findDoctorsPanel.revalidate();
        findDoctorsPanel.repaint();
        ArrayList<Doctor> doctors = db.getDoctors(search);
        if (communityId != 0) {
            doctors.removeIf(doctor -> db.getHospital(doctor.getHospitalId()).getCommunityId() != communityId);
        }
        if (doctors.size() == 0) {
            findDoctorsPanel.setLayout(new GridLayout(0, 1));
            findDoctorsPanel.add(new JLabel("No doctors found"));
            findDoctorsPanel.revalidate();
            findDoctorsPanel.repaint();
            return;
        }
        findDoctorsPanel.setLayout(new GridLayout(0, 3));
        for (Doctor doctor : doctors) {
            PersonDoctorBlock doctorBlock = new PersonDoctorBlock(doctor);
            findDoctorsPanel.add(doctorBlock.getMainPanel());
        }
        findDoctorsPanel.revalidate();
        findDoctorsPanel.repaint();
    }

    public JPanel getMainPanel() {
        return personViewMainPanel;
    }
}
