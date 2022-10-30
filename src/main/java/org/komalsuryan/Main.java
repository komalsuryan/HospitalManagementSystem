package org.komalsuryan;

import org.komalsuryan.views.SysAdminView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // write your code here
        JFrame jFrame = new JFrame("Hospital Management System");
        try {
            jFrame.setContentPane(new SysAdminView().getMainPanel());
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setMinimumSize(new Dimension(800, 600));
            jFrame.setSize(800, 600);
            jFrame.pack();
            jFrame.setVisible(true);
        } catch (Exception e) {
            // create a dialog to show the error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}