package org.komalsuryan.views;

import org.komalsuryan.Community;
import org.komalsuryan.Database;
import org.komalsuryan.Doctor;
import org.komalsuryan.Hospital;
import org.komalsuryan.views.sysAdminViewUiElements.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SysAdminView {
    private JPanel sysAdminViewMainPanel;
    private JPanel sysAdminViewHeadingPanel;
    private JLabel welcomeLabel;
    private JButton editSysAdminButton;
    private JButton logoutSysAdminButton;
    private JScrollPane doctorsScrollPane;
    private JPanel findDoctorsPanel;
    private JPanel doctorsTopPanel;
    private JLabel findDoctorsLabel;
    private JPanel findDoctorsSearchPanel;
    private JLabel findDoctorsSearchLabel;
    private JTextField findDoctorsSearchText;
    private JButton addDoctorButton;
    private JPanel doctorsPanel;
    private JPanel hospitalsPanel;
    private JScrollPane hospitalsScrollPane;
    private JPanel hospitalsTopPanel;
    private JPanel findHospitalsPanel;
    private JLabel findHospitalsLabel;
    private JPanel findHospitalsSearchPanel;
    private JLabel findHosptalsSearchLabel;
    private JTextField findHospitalsSearchText;
    private JButton addHospitalButton;
    private JPanel communitiesPanel;
    private JScrollPane communitiesScrollPane;
    private JPanel findCommunitiesPanel;
    private JPanel communitiesTopPanel;
    private JLabel findCommunitiesLabel;
    private JPanel findCommunitiesSearchPanel;
    private JLabel findCommunitiesSearchLabel;
    private JTextField findCommunitiesSearchText;
    private JButton addCommunityButton;
    private JPanel sysAdminViewContentPanel;
    private JScrollPane sysAdminViewContentScrollPane;
    private final Database db;

    public SysAdminView() {
        db = new Database();

        welcomeLabel.setText("Welcome, System Admin");
        editSysAdminButton.setText("Edit your details");
        logoutSysAdminButton.setText("Logout");

        // communities panel
        findCommunitiesLabel.setText("Communities");
        findCommunitiesSearchLabel.setText("Search");
        addCommunityButton.setText("Add Community");
        addCommunityButton.addActionListener(e -> {
            AddCommunityDialog addCommunityDialog = new AddCommunityDialog();
            addCommunityDialog.pack();
            addCommunityDialog.setVisible(true);
            createCommunityBlocks();
        });

        // hospitals panel
        findHospitalsLabel.setText("Hospitals");
        findHosptalsSearchLabel.setText("Search");
        addHospitalButton.setText("Add a hospital");
        addHospitalButton.addActionListener(e -> {
            AddHospitalDialog addHospitalDialog = new AddHospitalDialog();
            addHospitalDialog.pack();
            addHospitalDialog.setVisible(true);
            createHospitalBlocks();
        });

        // doctors panel
        findDoctorsLabel.setText("Doctors");
        findDoctorsSearchLabel.setText("Search");
        addDoctorButton.setText("Add a doctor");
        addDoctorButton.addActionListener(e -> {
            AddDoctorDialog addDoctorDialog = new AddDoctorDialog();
            addDoctorDialog.pack();
            addDoctorDialog.setVisible(true);
            createDoctorBlocks();
        });
        createCommunityBlocks();
        createHospitalBlocks();
        createDoctorBlocks();
    }

    public void createCommunityBlocks() {
        findCommunitiesPanel.removeAll();
        ArrayList<Community> communities = db.getAllCommunities();
        if (communities.size() == 0) {
            findCommunitiesPanel.setLayout(new GridLayout());
            findCommunitiesPanel.add(new JLabel("No communities found. Add a community to begin"));
        } else {
            findCommunitiesPanel.setLayout(new BoxLayout(findCommunitiesPanel, BoxLayout.X_AXIS));
            for (Community community : communities) {
                SysAdminCommunityBlock communityBlock = new SysAdminCommunityBlock(community);
                findCommunitiesPanel.add(communityBlock.getMainPanel());
            }
        }
        findCommunitiesPanel.revalidate();
        findCommunitiesPanel.repaint();
    }

    public void createHospitalBlocks() {
        findHospitalsPanel.removeAll();
        ArrayList<Hospital> hospitals = db.getAllHospitals();
        if (hospitals.size() == 0) {
            findHospitalsPanel.setLayout(new GridLayout());
            findHospitalsPanel.add(new JLabel("No hospitals found. Add a hospital to begin"));
        } else {
            findHospitalsPanel.setLayout(new BoxLayout(findHospitalsPanel, BoxLayout.X_AXIS));
            for (Hospital hospital : hospitals) {
                SysAdminHospitalBlock hospitalBlock = new SysAdminHospitalBlock(hospital);
                findHospitalsPanel.add(hospitalBlock.getMainPanel());
            }
        }
        findHospitalsPanel.revalidate();
        findHospitalsPanel.repaint();
    }

    private void createDoctorBlocks() {
        findDoctorsPanel.removeAll();
        ArrayList<Doctor> doctors = db.getAllDoctors();
        if (doctors.size() == 0) {
            findDoctorsPanel.setLayout(new GridLayout());
            findDoctorsPanel.add(new JLabel("No doctors found"));
        } else {
            findDoctorsPanel.setLayout(new BoxLayout(findDoctorsPanel, BoxLayout.X_AXIS));
            for (Doctor doctor : doctors) {
                SysAdminDoctorBlock doctorBlock = new SysAdminDoctorBlock(doctor);
                findDoctorsPanel.add(doctorBlock.getMainPanel());
            }
        }
        findDoctorsPanel.revalidate();
        findDoctorsPanel.repaint();
    }

    public JPanel getMainPanel() {
        return sysAdminViewMainPanel;
    }
}
