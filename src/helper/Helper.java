package helper;

import com.toedter.calendar.JDateChooser;
import database.DatabaseMySQL;
import java.awt.Component;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author afgha
 */
public class Helper {
    public static void resetForm(JFrame frame, ButtonGroup rbButtonGroup) {
        // Reset semua JTextField
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }

        // Reset semua JComboBox
        for (Component component : components) {
            if (component instanceof JComboBox) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBox = (JComboBox<String>) component;
                comboBox.setSelectedIndex(0);
            }
        }
        
         // Reset semua JDateChooser
    for (Component component : components) {
        if (component instanceof JDateChooser) {
            ((JDateChooser) component).setDate(null);
        }
    }
        // Reset seleksi JRadioButton jika ada ButtonGroup yang diberikan
        if (rbButtonGroup != null) {
            rbButtonGroup.clearSelection();
        }
    }
    
    public static int parseInt(String text) {
    if (text == null || text.trim().isEmpty()) {
        return 0;
    }
    try {
        return Integer.parseInt(text);
    } catch (NumberFormatException e) {
        return 0;
    }
}

    
    public static String parseString(int number){
        String hasilParseString = String.valueOf(number);
        return hasilParseString;
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

    
    public void generateReport(String filename, String nip) {
    try {
        // Load the report file
        JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportPath + filename + ".jasper");

        // Set parameters for the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nip", nip); // Assuming "nip" is the parameter name for NIP in your report

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
    
    
    private String getAbsolutePath(String relativePath) {
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }
}