package org.example;

import javax.swing.*;
import java.awt.*;

public class PersonView {
    private JLabel welcomeLabel;
    private JButton editPersonButton;
    private JButton logoutPersonButton;
    private JPanel personViewMainPanel;
    private JPanel personViewHeadingPanel;
    private JLabel findDoctorsLabel;

    public PersonView(int id) {
        welcomeLabel.setText("Welcome, " + id);
        editPersonButton.setText("Edit");
        logoutPersonButton.setText("Logout");
        findDoctorsLabel.setText("Doctors in your community");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PersonView");
        frame.setContentPane(new PersonView(1).personViewMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setSize(600, 600);
        frame.pack();
        frame.setVisible(true);
    }
}
