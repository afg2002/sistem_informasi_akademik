/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import dao.GuruDAO;
import dao.MapelDAO;
import entity.Guru;
import entity.Mapel;
import java.sql.*;
import entity.StatusKepegawaian;
import static helper.Helper.resetForm;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shouzen
 */
public class GuruFrame extends javax.swing.JFrame {
    private DefaultTableModel tableModel;   
    public GuruFrame() {
        initComponents();
        
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Status Kepegawaian", "Mengajar Mapel"}, 0);
        tabDataGuru.setModel(tableModel);
        loadDataGuru();
        try {
            populateMengajarMapel();
        } catch (SQLException ex) {
            Logger.getLogger(GuruFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Add row selection listener
        tabDataGuru.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabDataGuru.getSelectedRow();
                    if (selectedRow >= 0) {
                        populateFieldsFromSelectedRow(selectedRow);
                    }
                }
            }
        });
        
        tabDataGuru.getParent().addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(final ComponentEvent e) {
            if (tabDataGuru.getPreferredSize().width < tabDataGuru.getParent().getWidth()) {
                tabDataGuru.setAutoResizeMode(tabDataGuru.AUTO_RESIZE_ALL_COLUMNS);
            } else {
                tabDataGuru.setAutoResizeMode(tabDataGuru.AUTO_RESIZE_OFF);
            }
    }
});
    }
    
     private void populateFieldsFromSelectedRow(int rowIndex) {
        // Retrieve data from the selected row
        int idGuru = (Integer) tableModel.getValueAt(rowIndex, 0);
        String nama = (String) tableModel.getValueAt(rowIndex, 1);
        String tempatLahir = (String) tableModel.getValueAt(rowIndex, 2);
        Date tglLahir = (Date) tableModel.getValueAt(rowIndex, 3);
        String jenisKelamin = (String) tableModel.getValueAt(rowIndex, 4);
        String statusKepegawaian = (String) tableModel.getValueAt(rowIndex, 5);
        String mengajarMapel = (String) tableModel.getValueAt(rowIndex, 6);

        // Set data to the components
        txtId.setText(String.valueOf(idGuru));
        txtNama.setText(nama);
        txtTempatLahir.setText(tempatLahir);
        txtTanggalLahir.setDate(tglLahir);
        txtMengajarMapel.setSelectedItem(mengajarMapel);
        txtStatusKepegawaian.setSelectedItem(statusKepegawaian);
        
        // Set radio button for jenis kelamin
        if ("L".equals(jenisKelamin)) {
            rbLaki.setSelected(true);
        } else if ("P".equals(jenisKelamin)) {
            rbPerempuan.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroupJK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTempatLahir = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTanggalLahir = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMengajarMapel = new javax.swing.JComboBox<>();
        txtStatusKepegawaian = new javax.swing.JComboBox<>();
        e = new javax.swing.JScrollPane();
        tabDataGuru = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCetak = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Guru");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID ");

        txtId.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tempat Lahir");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tgl Lahir");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Status Kepegawaian");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Mengajar Mapel");

        txtMengajarMapel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Mapel" }));

        txtStatusKepegawaian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Status", "TETAP", "HONOR" }));

        e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabDataGuru.setAutoCreateRowSorter(true);
        tabDataGuru.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabDataGuru.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        e.setViewportView(tabDataGuru);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnCetak.setBackground(new java.awt.Color(0, 51, 51));
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("Cetak");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jenis Kelamin");

        rbGroupJK.add(rbLaki);
        rbLaki.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbLaki.setForeground(new java.awt.Color(255, 255, 255));
        rbLaki.setText("Laki-laki");

        rbGroupJK.add(rbPerempuan);
        rbPerempuan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbPerempuan.setForeground(new java.awt.Color(255, 255, 255));
        rbPerempuan.setText("Perempuan");

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logo)
                        .addGap(176, 176, 176))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(106, 106, 106)
                                .addComponent(txtNama))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(122, 122, 122)
                                .addComponent(txtId))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTempatLahir)
                                    .addComponent(txtTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMengajarMapel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtStatusKepegawaian, javax.swing.GroupLayout.Alignment.TRAILING, 0, 156, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbPerempuan)
                                            .addComponent(rbLaki))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnCetak)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReset)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCari))))
                            .addComponent(e))))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(logo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(32, 32, 32)
                .addComponent(btnCetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rbLaki))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbPerempuan)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtStatusKepegawaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMengajarMapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(btnCari)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void populateMengajarMapel() throws SQLException {
        MapelDAO mapelDAO = new MapelDAO();
        List<Mapel> mapelList = mapelDAO.getAllMapel();
        for (Mapel mapel : mapelList) {
            txtMengajarMapel.addItem(mapel.getNamaMapel());
        }
    }
   

    private void loadDataGuru() {
        try {
            GuruDAO guruDAO = new GuruDAO();
            List<Guru> gurus = guruDAO.getAllGuru();
            // Clear existing rows in the table model
            tableModel.setRowCount(0); // This will remove all rows from the table model
            for (Guru guru : gurus) {
                tableModel.addRow(new Object[]{
                    guru.getIdGuru(),
                    guru.getNama(),
                    guru.getTempatLahir(),
                    guru.getTglLahir(),
                    guru.getJenisKelamin(),
                    guru.getStatusKepegawaian().name(),
                    guru.getMengajarMapel()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }
    
    private void refresh(){
        loadDataGuru();
        resetForm(jPanel1, rbGroupJK);
    }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        String nama = txtNama.getText();
        String tempatLahir = txtTempatLahir.getText();
        java.util.Date tanggalLahirUtil = txtTanggalLahir.getDate();
        java.sql.Date tanggalLahir = new java.sql.Date(tanggalLahirUtil.getTime());
        String mengajarMapel = (String) txtMengajarMapel.getSelectedItem();
        String statusKepegawaianStr = (String) txtStatusKepegawaian.getSelectedItem();
        StatusKepegawaian statusKepegawaian = StatusKepegawaian.valueOf(statusKepegawaianStr);

        // Determine the selected gender
        String jenisKelamin;
        if (rbLaki.isSelected()) {
            jenisKelamin = "L";
        } else if (rbPerempuan.isSelected()) {
            jenisKelamin = "P";
        } else {
            JOptionPane.showMessageDialog(this, "Please select a gender");
            return; // Exit the method if no gender is selected
        }

        // Create Guru object
        Guru guru = new Guru(0, nama, tempatLahir, tanggalLahir, jenisKelamin, StatusKepegawaian.valueOf(statusKepegawaian.name()), mengajarMapel);

        // Save Guru object using GuruDAO
        try {
            GuruDAO guruDAO = new GuruDAO();
            if(txtStatusKepegawaian.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please select status pegawaian.");
            }
            if(txtMengajarMapel.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please select mapel.");
            }
            guruDAO.addGuru(guru);
            JOptionPane.showMessageDialog(this, "Guru saved successfully");
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving Guru: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // Retrieve values from GUI components
        int idGuru = Integer.parseInt(txtId.getText());
        String nama = txtNama.getText();
        String tempatLahir = txtTempatLahir.getText();
        java.util.Date tanggalLahirUtil = txtTanggalLahir.getDate();
        java.sql.Date tanggalLahir = new java.sql.Date(tanggalLahirUtil.getTime());
        String mengajarMapel = (String) txtMengajarMapel.getSelectedItem();
        String statusKepegawaianStr = (String) txtStatusKepegawaian.getSelectedItem();
        StatusKepegawaian statusKepegawaian = StatusKepegawaian.valueOf(statusKepegawaianStr);

        // Determine the selected gender
        String jenisKelamin;
        if (rbLaki.isSelected()) {
            jenisKelamin = "L";
        } else if (rbPerempuan.isSelected()) {
            jenisKelamin = "P";
        } else {
            JOptionPane.showMessageDialog(this, "Please select a gender");
            return; // Exit the method if no gender is selected
        }

        // Create Guru object
        Guru guru = new Guru(idGuru, nama, tempatLahir, tanggalLahir, jenisKelamin, StatusKepegawaian.valueOf(statusKepegawaian.name()), mengajarMapel);

        // Update Guru object using GuruDAO
        try {
            GuruDAO guruDAO = new GuruDAO();
            if(txtStatusKepegawaian.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please select status pegawaian.");
            }
            if(txtMengajarMapel.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please select mapel.");
            }
            guruDAO.updateGuru(guru);
            JOptionPane.showMessageDialog(this, "Guru updated successfully");
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating Guru: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
       resetForm(jPanel1, rbGroupJK);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            int idGuru = Integer.parseInt(txtId.getText());
            GuruDAO guruDAO = new GuruDAO();
            guruDAO.deleteGuru(idGuru);
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(GuruFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
         // Retrieve the ID from txtCari
        String idText = txtCari.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID to search.");
            return;
        }

        int idGuru;
        try {
            idGuru = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric value.");
            return;
        }

        GuruDAO guruDAO = new GuruDAO();
        try {
            // Fetch Guru by ID
            Guru guru = guruDAO.getGuruById(idGuru);

            // Clear existing rows in the table
            tableModel.setRowCount(0);

            if (guru != null) {
                // Add the found Guru to the table
                tableModel.addRow(new Object[]{
                    guru.getIdGuru(),
                    guru.getNama(),
                    guru.getTempatLahir(),
                    guru.getTglLahir(),
                    guru.getJenisKelamin(),
                    guru.getStatusKepegawaian().name(),
                    guru.getMengajarMapel()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Guru with ID " + idGuru + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCariActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuruFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuruFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuruFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuruFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuruFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane e;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.ButtonGroup rbGroupJK;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tabDataGuru;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtId;
    private javax.swing.JComboBox<String> txtMengajarMapel;
    private javax.swing.JTextField txtNama;
    private javax.swing.JComboBox<String> txtStatusKepegawaian;
    private com.toedter.calendar.JDateChooser txtTanggalLahir;
    private javax.swing.JTextField txtTempatLahir;
    // End of variables declaration//GEN-END:variables
}
