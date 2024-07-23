/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import dao.PembayaranDAO;
import dao.SiswaDAO;
import dao.TransaksiPembayaranDAO;
import entity.JenisPembayaran;
import entity.Pembayaran;
import entity.Siswa;
import entity.TransaksiPembayaran;
import java.sql.*;
import static helper.Helper.resetForm;
import static helper.Helper.setPlaceholder;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class TransaksiPembayaranFrame extends javax.swing.JFrame {
    private DefaultTableModel tableModel;   
    private final String placeholderText = "Cari berdasarkan no transaksi";
    public TransaksiPembayaranFrame() {
        initComponents();
        
        tableModel = new DefaultTableModel(
    new String[]{
        "No Transaksi", "NIS","Tanggal Pembayaran","Kode Pembayaran" ,"Jumlah Bayar"
    }, 
    0
);
        tabDataTransaksiPembayaran.setModel(tableModel);
        loadDataTransaksiPembayaran();
        setPlaceholder(txtCari, placeholderText);
        tabDataTransaksiPembayaran.getParent().addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(final ComponentEvent e) {
            if (tabDataTransaksiPembayaran.getPreferredSize().width < tabDataTransaksiPembayaran.getParent().getWidth()) {
                tabDataTransaksiPembayaran.setAutoResizeMode(tabDataTransaksiPembayaran.AUTO_RESIZE_ALL_COLUMNS);
            } else {
                tabDataTransaksiPembayaran.setAutoResizeMode(tabDataTransaksiPembayaran.AUTO_RESIZE_OFF);
            }
    }
        }
                );
        // Add row selection listener
        tabDataTransaksiPembayaran.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabDataTransaksiPembayaran.getSelectedRow();
                    if (selectedRow >= 0) {
                        populateFieldsFromSelectedRow(selectedRow);
                    }
                }
            }
        });
        
            class PlainNotationRenderer extends DefaultTableCellRenderer {
                private final DecimalFormat decimalFormat = new DecimalFormat("#,##0"); // Format for plain notation

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value instanceof Number) {
                        // Format number in plain notation
                        setText(decimalFormat.format(value));
                    } else {
                        setText(value.toString());
                    }
                    return this;
                }
            }

            // Apply scientific notation renderer to the third column
            tabDataTransaksiPembayaran.getColumnModel().getColumn(4).setCellRenderer(new PlainNotationRenderer());
    }
    
     private void populateFieldsFromSelectedRow(int selectedRow) {
    // Ensure the selectedRow is valid
    if (selectedRow >= 0) {
        // Get the data from the selected row in the table
        int noTransaksi = (int) tableModel.getValueAt(selectedRow, 0);
        String nis = (String) tableModel.getValueAt(selectedRow, 1);
        String kodePembayaran = (String) tableModel.getValueAt(selectedRow, 3);
        Object jumlahBayar = tableModel.getValueAt(selectedRow, 4);
        Date tglPembayaran = (Date) tableModel.getValueAt(selectedRow, 2);

        // Populate the basic fields
        txtNoTransaksi.setText(String.valueOf(noTransaksi));
        txtNis.setText(nis);
        txtKodePembayaran.setText(kodePembayaran);

        // Convert nominal to a plain string if it is a Double
        String jumlahBayarString;
        if (jumlahBayar instanceof Double) {
            jumlahBayarString = String.format("%.2f", (Double) jumlahBayar); // Format the double value as a string
        } else {
            jumlahBayarString = jumlahBayar.toString(); // Fallback to the object's string representation
        }

        txtJumlahBayar.setText(jumlahBayarString);
        txtTglPembayaran.setDate(tglPembayaran);

        // Fetch additional data using DAO methods and set the fields
        try {
            SiswaDAO siswaDAO = new SiswaDAO();
            Siswa siswa = siswaDAO.getSiswaByNis(nis);
            if (siswa != null) {
                txtNama.setText(siswa.getNama());
                txtKelas.setText(siswa.getKelas());
                txtAngkatan.setText(String.valueOf(siswa.getAngkatan()));
            }

            PembayaranDAO pembayaranDAO = new PembayaranDAO();
            Pembayaran pembayaran = pembayaranDAO.getPembayaranByKode(kodePembayaran);
            if (pembayaran != null) {
                txtJenisPembayaran.setText(pembayaran.getJenisPembayaran().name());
                txtNominal.setText(String.valueOf(pembayaran.getNominal()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching additional data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        e = new javax.swing.JScrollPane();
        tabDataTransaksiPembayaran = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCetak = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtNoTransaksi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNis = new javax.swing.JTextField();
        btnCariSiswa = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtKelas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAngkatan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtKodePembayaran = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtJenisPembayaran = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNominal = new javax.swing.JTextField();
        btnCariPembayaran = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtTglPembayaran = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        txtJumlahBayar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Transaksi Pembayaran");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No Transaksi");

        tabDataTransaksiPembayaran.setModel(new javax.swing.table.DefaultTableModel(
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
        e.setViewportView(tabDataTransaksiPembayaran);

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

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        btnCetak.setBackground(new java.awt.Color(0, 51, 51));
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("Cetak");

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NIS");

        btnCariSiswa.setText("Cari Siswa");
        btnCariSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariSiswaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama");

        txtNama.setEditable(false);

        txtKelas.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kelas");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Angkatan");

        txtAngkatan.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kode Pembayaran");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jenis Pembayaran");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nominal");

        btnCariPembayaran.setText("Cari Pembayaran");
        btnCariPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPembayaranActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tgl Pembayaran");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Jumlah Bayar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logo)
                        .addGap(205, 205, 205))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(txtNoTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNominal)
                                                    .addComponent(txtJenisPembayaran)
                                                    .addComponent(txtJumlahBayar)))
                                            .addComponent(txtTglPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(txtKodePembayaran)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNama)
                                            .addComponent(txtNis)
                                            .addComponent(txtKelas)
                                            .addComponent(txtAngkatan))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariPembayaran)
                            .addComponent(btnCariSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCetak)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                            .addComponent(txtCari)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnCari))))))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(btnReset))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCariSiswa)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtTglPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtKodePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariPembayaran))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetForm(jPanel1, rbGroupJK);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
      // Get the search term from the text field
    String searchTerm = txtCari.getText().trim();
     if (searchTerm.isEmpty() || searchTerm.equals(placeholderText)) {
        JOptionPane.showMessageDialog(this, "Please enter a search term.");
        loadDataTransaksiPembayaran();
        return;
    }
    // Check if the search term is not empty
    if (!searchTerm.isEmpty()) {
        try {
            // Create an instance of the DAO class
            TransaksiPembayaranDAO transaksiDAO = new TransaksiPembayaranDAO();
            
            // Convert search term to integer if possible
            Integer noTransaksi = null;
            try {
                noTransaksi = Integer.parseInt(searchTerm);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid search term. Please enter a valid transaction number.");
                return;
            }
            
            // Search for a transaction by no_transaksi
            TransaksiPembayaran transaksi = transaksiDAO.getTransaksiPembayaranByNo(noTransaksi);
            
            // Clear existing rows in the table model
            tableModel.setRowCount(0);
            
            // Add search results to the table model if a record is found
            if (transaksi != null) {
                tableModel.addRow(new Object[]{
                    transaksi.getNoTransaksi(),
                transaksi.getNis(),
                transaksi.getTglPembayaran(),
                transaksi.getKodePembayaran(),
                transaksi.getJumlahBayar(),
                });
            } else {
                JOptionPane.showMessageDialog(this, "No record found for the given transaction number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Search field is empty. Please enter a valid search term.");
    }

    }//GEN-LAST:event_btnCariActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Get the noTransaksi value from the text field
    String noTransaksi = txtNoTransaksi.getText().trim();
    
    // Check if the noTransaksi field is not empty
    if (!noTransaksi.isEmpty()) {
        // Confirm deletion with the user
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the record with No Transaksi: " + noTransaksi + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Create an instance of the DAO class
                TransaksiPembayaranDAO transaksiDAO = new TransaksiPembayaranDAO();
                
                // Call the delete method from the DAO class
                transaksiDAO.deleteTransaksiPembayaran(Integer.parseInt(noTransaksi));
                
                // Remove the row from the table model (optional)
                // You may need to refresh the table data after deletion
                loadDataTransaksiPembayaran(); // Refresh the table
                
                // Clear the form fields
                refresh();
                
                JOptionPane.showMessageDialog(this, "Record deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting record: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "No Transaksi field is empty. Please enter a valid No Transaksi.");
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    // Validate input fields
        if (txtNoTransaksi.getText().trim().isEmpty() || 
            txtNis.getText().trim().isEmpty() || 
            txtKodePembayaran.getText().trim().isEmpty() || 
            txtNominal.getText().trim().isEmpty() || 
            txtTglPembayaran.getDate() == null) {

            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        try {
            // Get values from text fields and convert them if necessary
            int noTransaksi = Integer.parseInt(txtNoTransaksi.getText().trim());
            String nis = txtNis.getText().trim();
            String kodePembayaran = txtKodePembayaran.getText().trim();
            Date tglPembayaran = new java.sql.Date(txtTglPembayaran.getDate().getTime());
            Double total = Double.parseDouble(txtJumlahBayar.getText());

            // Create TransaksiPembayaran object
            TransaksiPembayaran transaksi = new TransaksiPembayaran(
                noTransaksi, nis, tglPembayaran, kodePembayaran,total
            );

            // Save to database using DAO
            TransaksiPembayaranDAO transaksiDAO = new TransaksiPembayaranDAO();
            transaksiDAO.updateTransaksiPembayaran(transaksi);

            // Refresh table data
            loadDataTransaksiPembayaran();

            // Clear form fields
            refresh();

            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    // Validate input fields
        if ( 
            txtNis.getText().trim().isEmpty() || 
            txtKodePembayaran.getText().trim().isEmpty() || 
            txtNominal.getText().trim().isEmpty() || 
            txtTglPembayaran.getDate() == null) {

            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        try {
            // Get values from text fields and convert them if necessary
            
            String nis = txtNis.getText().trim();
            String kodePembayaran = txtKodePembayaran.getText().trim();
            Date tglPembayaran = new java.sql.Date(txtTglPembayaran.getDate().getTime());
            Double total = Double.parseDouble(txtJumlahBayar.getText());

            // Create TransaksiPembayaran object
            TransaksiPembayaran transaksi = new TransaksiPembayaran(
                0, nis, tglPembayaran, kodePembayaran,total
            );

            // Save to database using DAO
            TransaksiPembayaranDAO transaksiDAO = new TransaksiPembayaranDAO();
            transaksiDAO.addTransaksiPembayaran(transaksi);

            // Refresh table data
            loadDataTransaksiPembayaran();

            // Clear form fields
            refresh();

            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btnCariSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariSiswaActionPerformed
          String nis = txtNis.getText().trim();
    
    if (nis.isEmpty()) {
        JOptionPane.showMessageDialog(this, "NIS cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        SiswaDAO siswaDAO = new SiswaDAO();
        Siswa siswa = siswaDAO.getSiswaByNis(nis);

        if (siswa != null) {
            // Populate the form fields with the data
            txtNama.setText(siswa.getNama());
            txtKelas.setText(siswa.getKelas());
            txtAngkatan.setText(String.valueOf(siswa.getAngkatan()));
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            txtNama.setText("");
            txtKelas.setText("");
            txtAngkatan.setText("");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching student data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnCariSiswaActionPerformed

    private void btnCariPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPembayaranActionPerformed
        // Get the search term from the text field
    String kodePembayaran = txtKodePembayaran.getText().trim();
    
    if (kodePembayaran.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Kode Pembayaran cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        PembayaranDAO pembayaranDAO = new PembayaranDAO();
        Pembayaran pembayaran = pembayaranDAO.getPembayaranByKode(kodePembayaran);

        if (pembayaran != null) {
            // Populate the form fields with the data
            txtJenisPembayaran.setText(pembayaran.getJenisPembayaran().name());
            // Format the nominal to avoid scientific notation
            txtNominal.setText(String.format("%.2f", pembayaran.getNominal()));
        } else {
            JOptionPane.showMessageDialog(this, "Pembayaran not found.", "Error", JOptionPane.ERROR_MESSAGE);
            txtJenisPembayaran.setText("");
            txtNominal.setText("");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching pembayaran data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnCariPembayaranActionPerformed
    
   

    private void loadDataTransaksiPembayaran() {
    try {
        TransaksiPembayaranDAO transaksiDAO = new TransaksiPembayaranDAO();
        List<TransaksiPembayaran> transaksiList = transaksiDAO.getAllTransaksiPembayaran();
        
        // Clear the current table data
        tableModel.setRowCount(0);
        
        // Populate the table model with the fetched data
        for (TransaksiPembayaran transaksi : transaksiList) {
            tableModel.addRow(new Object[]{
                transaksi.getNoTransaksi(),
                transaksi.getNis(),
                transaksi.getTglPembayaran(),
                transaksi.getKodePembayaran(),
                transaksi.getJumlahBayar(),
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
    }
}


    
    private void refresh(){
        loadDataTransaksiPembayaran();
        resetForm(jPanel1, rbGroupJK);
    }
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
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiPembayaranFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCariPembayaran;
    private javax.swing.JButton btnCariSiswa;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane e;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.ButtonGroup rbGroupJK;
    private javax.swing.JTable tabDataTransaksiPembayaran;
    private javax.swing.JTextField txtAngkatan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtJenisPembayaran;
    private javax.swing.JTextField txtJumlahBayar;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtKodePembayaran;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtNominal;
    private com.toedter.calendar.JDateChooser txtTglPembayaran;
    // End of variables declaration//GEN-END:variables
}
