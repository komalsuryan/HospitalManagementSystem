package org.example;

import org.example.personViewUiElements.PersonDoctorBlock;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PersonView {
    private JLabel welcomeLabel;
    private JButton editPersonButton;
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
    private Database db;

    public PersonView(int id) throws IOException {
        db = new Database();
        welcomeLabel.setText("Welcome, " + id);
        editPersonButton.setText("Edit your details");
        logoutPersonButton.setText("Logout");
        findDoctorsLabel.setText("Doctors in your community");
        findDoctorsSearchLabel.setText("Search");
        createDoctorBlocks();
    }

    private void createDoctorBlocks() throws IOException {
        ArrayList<Doctor> doctors = db.getAllDoctors();
        findDoctorsPanel.setLayout(new BoxLayout(findDoctorsPanel, BoxLayout.X_AXIS));
        for (Doctor doctor : doctors) {
            PersonDoctorBlock doctorBlock = new PersonDoctorBlock(doctor);
            findDoctorsPanel.add(doctorBlock.getMainPanel());
        }
        findDoctorsPanel.revalidate();
        findDoctorsPanel.repaint();
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("PersonView");
        frame.setContentPane(new PersonView(1).personViewMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setSize(600, 600);
        frame.pack();
        frame.setVisible(true);
    }
}
