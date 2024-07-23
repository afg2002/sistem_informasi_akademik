package helper;

import javax.swing.*;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Helper {
    // Method to set placeholder for JTextField
    public static void setPlaceholder(JTextField textField, String placeholderText) {
        textField.setText(placeholderText);
        textField.setForeground(Color.GRAY); // Set placeholder text color

        // Add focus listener to handle placeholder text
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Set text color to black
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(Color.GRAY); // Set placeholder color
                }
            }
        });
    }
    public static void resetForm(Container container, ButtonGroup rbButtonGroup) {
        // Reset all JTextField components
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }

        // Reset all JComboBox components
        for (Component component : container.getComponents()) {
            if (component instanceof JComboBox<?>) {
                JComboBox<?> comboBox = (JComboBox<?>) component;
                comboBox.setSelectedIndex(0); // Set to no selection
            }
        }
        
        // Reset all JDateChooser components
        for (Component component : container.getComponents()) {
            if (component instanceof JDateChooser) {
                ((JDateChooser) component).setDate(null);
            }
        }
        
         // Reset all JTextArea components
            for (Component component : container.getComponents()) {
                if (component instanceof JTextArea) {
                    ((JTextArea) component).setText("");
                }
            }
        
        
        
        // Reset the selection of JRadioButton if a ButtonGroup is provided
        if (rbButtonGroup != null) {
            rbButtonGroup.clearSelection();
        }
    }
}
