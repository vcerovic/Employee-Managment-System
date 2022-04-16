package com.veljko.project.ui.services;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validator {

    public static boolean validateFields(JTextField[] textFields, String errorMsg, String title) {
        boolean isValid = true;

        for (JTextField field : textFields) {
            if (field.getText().equals("")) isValid = false;
        }

        if (!isValid) {
            JOptionPane.showMessageDialog(null, errorMsg, title,
                    JOptionPane.ERROR_MESSAGE);
        }

        return isValid;
    }

    public static boolean validateTextLength(JTextField[] textFields){
        boolean isValid = true;

        for (JTextField field : textFields) {
            if (field.getText().length() > 40) isValid = false;
        }

        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Only 40 characters are allowed", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return isValid;
    }

    public static boolean validateIfNumber(JTextField[] textFields) {
        boolean isValid = true;

        for (JTextField field : textFields) {
            try {
                Integer.parseInt(field.getText());
            } catch (NumberFormatException e) {
                isValid = false;
                String massage = "You must enter number in " + field.getName() + " (Whole number)";
                JOptionPane.showMessageDialog(null, massage, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }


        return isValid;

    }
}
