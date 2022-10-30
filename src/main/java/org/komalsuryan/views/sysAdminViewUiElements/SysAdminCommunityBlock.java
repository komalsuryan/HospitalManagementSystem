package org.komalsuryan.views.sysAdminViewUiElements;

import org.komalsuryan.Community;

import javax.swing.*;

public class SysAdminCommunityBlock {
    private JLabel communityNameLabel;
    private JPanel mainPanel;
    private JPanel communityInfoPanel;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel zipLabel;
    private JLabel cityValue;
    private JLabel stateValue;
    private JLabel zipValue;
    private JButton editButton;
    private JButton deleteButton;

    public SysAdminCommunityBlock(Community community) {
        // heading
        communityNameLabel.setText(community.getName());

        // info
        cityLabel.setText("City");
        cityValue.setText(community.getCity());
        stateLabel.setText("State");
        stateValue.setText(community.getState());
        zipLabel.setText("Zip Code");
        zipValue.setText(String.valueOf(community.getZipCode()));

        // buttons
        editButton.setText("Edit");
        deleteButton.setText("Delete");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
