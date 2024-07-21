package helper;

import javax.swing.*;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Container;

public class Helper {
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
                comboBox.setSelectedIndex(-1); // Set to no selection
            }
        }
        
        // Reset all JDateChooser components
        for (Component component : container.getComponents()) {
            if (component instanceof JDateChooser) {
                ((JDateChooser) component).setDate(null);
            }
        }
        
        // Reset the selection of JRadioButton if a ButtonGroup is provided
        if (rbButtonGroup != null) {
            rbButtonGroup.clearSelection();
        }
    }
}
