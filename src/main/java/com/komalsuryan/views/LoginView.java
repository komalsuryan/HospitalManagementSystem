package com.komalsuryan.views;

import com.komalsuryan.Database;
import com.komalsuryan.UserTypes;

import javax.swing.*;

public class LoginView {
    private JPanel mainPanel;
    private JLabel loginPanelHeading;
    private JTextField loginEmailValue;
    private JPasswordField loginPasswordValue;
    private JLabel loginEmailLabel;
    private JLabel loginPasswordLabel;
    private JComboBox<String> loginTypesComboBox;
    private JLabel loginAsLabel;
    private JButton loginButton;
    private JButton browseAnonButton;

    private final Database db;

    public LoginView(JFrame currentJFrame) {
        db = new Database();
        loginPanelHeading.setText("Welcome to the Hospital Management System. Login to continue");
        loginEmailLabel.setText("Email: ");
        loginPasswordLabel.setText("Password: ");
        loginAsLabel.setText("Login as: ");
        // populate the combo box with UserTypes Enum
        for (UserTypes type : UserTypes.values()) {
            loginTypesComboBox.addItem(type.toString());
        }
        loginTypesComboBox.setSelectedItem(UserTypes.PATIENT.toString());

        loginButton.setText("Login");
        browseAnonButton.setText("Browse without logging in");

        // add enter key listener on dialog
        mainPanel.registerKeyboardAction(e -> {
            if (loginEmailValue.getText().isEmpty() && loginPasswordValue.getPassword().length == 0) {
                browseAnonButton.doClick();
            } else {
                loginButton.doClick();
            }
        }, KeyStroke.getKeyStroke("ENTER"), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        loginButton.addActionListener(e -> onLogin(currentJFrame));
        browseAnonButton.addActionListener(e -> onBrowseAnon(currentJFrame));
    }

    private void onLogin(JFrame currentJFrame) {
        // get values
        String email = loginEmailValue.getText();
        // get string from password field
        String password = new String(loginPasswordValue.getPassword());
        String userType = (String) loginTypesComboBox.getSelectedItem();
        // get the enum from its string representation
        assert userType != null;
        UserTypes type = UserTypes.valueOf(userType.toUpperCase().replace(" ", "_"));

        // validate values
        int userId = new Database().validate(email, password, type);

        if (userId == -1) {
            throw new IllegalArgumentException("Invalid credentials. Check your email, password or user type");
        }

        if (type == UserTypes.SYSTEM_ADMIN) {
            JFrame systemAdminFrame = new JFrame("System Admin");
            systemAdminFrame.setContentPane(new SysAdminView().getMainPanel());
            systemAdminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            systemAdminFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            systemAdminFrame.pack();
            systemAdminFrame.setVisible(true);
            currentJFrame.dispose();
        } else if (type == UserTypes.COMMUNITY_ADMIN) {
            JFrame communityAdminFrame = new JFrame("Community Admin");
            communityAdminFrame.setContentPane(new CommunityAdminView(db.getCommunityAdmin(userId)).getMainPanel());
            communityAdminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            communityAdminFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            communityAdminFrame.pack();
            communityAdminFrame.setVisible(true);
            currentJFrame.dispose();
        } else if (type == UserTypes.DOCTOR) {
            JFrame doctorFrame = new JFrame("Doctor");
            doctorFrame.setContentPane(new DoctorView(db.getDoctor(userId)).getMainPanel());
            doctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            doctorFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            doctorFrame.pack();
            doctorFrame.setVisible(true);
            currentJFrame.dispose();
        } else if (type == UserTypes.PATIENT) {
            JFrame patientFrame = new JFrame("Patient");
            patientFrame.setContentPane(new PatientView(db.getPatient(userId)).getMainPanel());
            patientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            patientFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            patientFrame.pack();
            patientFrame.setVisible(true);
            currentJFrame.dispose();
        }
    }

    private void onBrowseAnon(JFrame currentJFrame) {
        JFrame anonFrame = new JFrame("Browse Anonymously");
        anonFrame.setContentPane(new PersonView().getMainPanel());
        anonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        anonFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        anonFrame.pack();
        anonFrame.setVisible(true);
        currentJFrame.dispose();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
