package helper;

import javax.swing.*;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;
import database.DatabaseMySQL;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    
    private Connection conn = new DatabaseMySQL().connectDB();
    private final String reportPath = ".\\src\\laporan\\";

    public void generateReport(String filename) {
    try {
        // Load the report file
        JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportPath + filename + ".jasper");

        // Fill the report with data (without parameters)
        JasperPrint jprint = JasperFillManager.fillReport(report, null, conn);

        // Create and display the viewer for the generated report
        JasperViewer jviewer = new JasperViewer(jprint, false);
        jviewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
        jviewer.setVisible(true);
    } catch (JRException ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    public void generateReport(String filename, String param1) {
    try {
        // Load the report file
        JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportPath + filename + ".jasper");

        // Set parameters for the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param", param1); // Assuming "nip" is the parameter name for NIP in your report

        // Fill the report with data and parameters
        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, conn);

        // Create and display the viewer for the generated report
        JasperViewer jviewer = new JasperViewer(jprint, false);
        jviewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
        jviewer.setVisible(true);
    } catch (JRException ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
}
    
    public void generateReport(String filename, String param1, String param2) {
    try {
        // Load the report file
        JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportPath + filename + ".jasper");

        // Set parameters for the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param", param1); // 
        parameters.put("param2", param2);

        // Fill the report with data and parameters
        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, conn);

        // Create and display the viewer for the generated report
        JasperViewer jviewer = new JasperViewer(jprint, false);
        jviewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
        jviewer.setVisible(true);
    } catch (JRException ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public void generateReport(String filename, String param1, String param2, String param3) {
    try {
        // Load the report file
        JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportPath + filename + ".jasper");

        // Set parameters for the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param", param1); // 
        parameters.put("param2", param2);
        parameters.put("param3", param3);

        // Fill the report with data and parameters
        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, conn);

        // Create and display the viewer for the generated report
        JasperViewer jviewer = new JasperViewer(jprint, false);
        jviewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
        jviewer.setVisible(true);
    } catch (JRException ex) {
        Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
}

