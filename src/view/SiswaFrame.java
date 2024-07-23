/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import dao.SiswaDAO;
import entity.Siswa;
import java.sql.*;
import static helper.Helper.resetForm;
import static helper.Helper.setPlaceholder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
public class SiswaFrame extends javax.swing.JFrame {
    private DefaultTableModel tableModel;   
    private final String placeholderText = "Cari berdasarkan nama atau NIS";
    public SiswaFrame() {
        initComponents();
        setPlaceholder(txtCari, placeholderText);
        tableModel = new DefaultTableModel(
    new String[]{
        "NIS", "Nama", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin",
        "NISN", "Angkatan", "Kelas", "Agama", "Alamat", "No Telp",
        "Nama Ayah", "Tanggal Lahir Ayah", "Pendidikan Ayah", "Pekerjaan Ayah",
        "Nama Ibu", "Tanggal Lahir Ibu", "Pendidikan Ibu", "Pekerjaan Ibu"
    }, 
    0
);
tabDataSiswa.setModel(tableModel);
        loadDataSiswa();
        // Add row selection listener
        tabDataSiswa.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabDataSiswa.getSelectedRow();
                    if (selectedRow >= 0) {
                        populateFieldsFromSelectedRow(selectedRow);
                    }
                }
            }
        });
        
        tabDataSiswa.getParent().addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(final ComponentEvent e) {
            if (tabDataSiswa.getPreferredSize().width < tabDataSiswa.getParent().getWidth()) {
                tabDataSiswa.setAutoResizeMode(tabDataSiswa.AUTO_RESIZE_ALL_COLUMNS);
            } else {
                tabDataSiswa.setAutoResizeMode(tabDataSiswa.AUTO_RESIZE_OFF);
            }
    }
});
        
        
        

    }
    
    private void populateFieldsFromSelectedRow(int selectedRow) {
    txtNis.setText(tableModel.getValueAt(selectedRow, 0).toString());
    txtNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
    txtTempatLahir.setText(tableModel.getValueAt(selectedRow, 2).toString());
    txtTanggalLahir.setDate((Date) tableModel.getValueAt(selectedRow, 3));
    
    String jenisKelamin = tableModel.getValueAt(selectedRow, 4).toString();
    if (jenisKelamin.equals("L")) {
        rbLaki.setSelected(true);
    } else if (jenisKelamin.equals("P")) {
        rbPerempuan.setSelected(true);
    }

    txtNisn.setText(tableModel.getValueAt(selectedRow, 5).toString());
    txtAngkatan.setText(tableModel.getValueAt(selectedRow, 6).toString());
    txtKelas.setText(tableModel.getValueAt(selectedRow, 7).toString());
    cbAgama.setSelectedItem(tableModel.getValueAt(selectedRow, 8).toString());
    txtAlamat.setText(tableModel.getValueAt(selectedRow, 9).toString());
    txtNoTelp.setText(tableModel.getValueAt(selectedRow, 10).toString());
    txtNamaAyah.setText(tableModel.getValueAt(selectedRow, 11).toString());
    txtTglLahirAyah.setDate((Date) tableModel.getValueAt(selectedRow, 12));
    txtPendidikanAyah.setText(tableModel.getValueAt(selectedRow, 13).toString());
    txtPekerjaanAyah.setText(tableModel.getValueAt(selectedRow, 14).toString());
    txtNamaIbu.setText(tableModel.getValueAt(selectedRow, 15).toString());
    txtTglLahirIbu.setDate((Date) tableModel.getValueAt(selectedRow, 16));
    txtPendidikanIbu.setText(tableModel.getValueAt(selectedRow, 17).toString());
    txtPekerjaanIbu.setText(tableModel.getValueAt(selectedRow, 18).toString());
}

    
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroupJK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTempatLahir = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTanggalLahir = new com.toedter.calendar.JDateChooser();
        e = new javax.swing.JScrollPane();
        tabDataSiswa = new javax.swing.JTable();
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
        jLabel6 = new javax.swing.JLabel();
        txtNisn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAngkatan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbAgama = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        txtNoTelp = new javax.swing.JTextField();
        txtNamaAyah = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPendidikanAyah = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtPekerjaanAyah = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtNamaIbu = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPendidikanIbu = new javax.swing.JTextField();
        txtPekerjaanIbu = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtTglLahirAyah = new com.toedter.calendar.JDateChooser();
        txtTglLahirIbu = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        txtKelas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Siswa");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NIS");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tempat Lahir");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tgl Lahir");

        e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabDataSiswa.setAutoCreateRowSorter(true);
        tabDataSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabDataSiswa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        e.setViewportView(tabDataSiswa);

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("NISN");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Angkatan");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Agama");

        cbAgama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Islam", "Kristen", "Hindu", "Budha", "Konghucu" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane2.setViewportView(txtAlamat);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("No Telpon");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nama Ayah");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tgl Lahir Ayah");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pendidikan Ayah");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Pekerjaan Ayah");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nama Ibu");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tgl Lahir Ibu");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Pendidikan Ibu");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Pekerjaan Ibu");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Kelas");

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
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel19))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtPendidikanAyah)
                                            .addComponent(txtPekerjaanAyah)
                                            .addComponent(txtNamaAyah, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamaIbu, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPendidikanIbu)
                                            .addComponent(txtPekerjaanIbu, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTglLahirAyah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTglLahirIbu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel20))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNis)
                                    .addComponent(txtNama)
                                    .addComponent(txtAngkatan)
                                    .addComponent(txtNisn)
                                    .addComponent(txtTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTempatLahir)
                                    .addComponent(cbAgama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)
                                    .addComponent(txtNoTelp)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbPerempuan)
                                            .addComponent(rbLaki))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtKelas))))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rbLaki))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbPerempuan)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cbAgama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNamaAyah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(txtTglLahirAyah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtPendidikanAyah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtPekerjaanAyah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtNamaIbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(txtTglLahirIbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtPendidikanIbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtPekerjaanIbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        String searchText = txtCari.getText(); // txtCari is your search input field
         if (searchText.isEmpty() || searchText.equals("Cari berdasarkan nama atau NIS")) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.");
            loadDataSiswa();
            return;
         }
    try {
        SiswaDAO siswaDAO = new SiswaDAO();
        List<Siswa> siswaList = siswaDAO.searchSiswa(searchText);
        
        // Clear existing rows in the table model
        tableModel.setRowCount(0);
        
        for (Siswa siswa : siswaList) {
            tableModel.addRow(new Object[]{
                siswa.getNis(),
                siswa.getNama(),
                siswa.getTempatLahir(),
                siswa.getTglLahir(),
                siswa.getJenisKelamin(),
                siswa.getNisn(),
                siswa.getAngkatan(),
                siswa.getKelas(),
                siswa.getAgama(),
                siswa.getAlamat(),
                siswa.getNoTelp(),
                siswa.getNamaAyah(),
                siswa.getTglLahirAyah(),
                siswa.getPendidikanAyah(),
                siswa.getPekerjaanAyah(),
                siswa.getNamaIbu(),
                siswa.getTglLahirIbu(),
                siswa.getPendidikanIbu(),
                siswa.getPekerjaanIbu()
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            String nis = txtNis.getText();
            SiswaDAO siswa = new SiswaDAO();
            siswa.deleteSiswa(nis);
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(SiswaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            Siswa siswa = new Siswa();
            siswa.setNis(txtNis.getText());
            siswa.setNama(txtNama.getText());
            siswa.setTempatLahir(txtTempatLahir.getText());
            siswa.setTglLahir(txtTanggalLahir.getDate());
            siswa.setJenisKelamin(rbLaki.isSelected() ? "L" : "P");
            siswa.setNisn(txtNisn.getText());
            siswa.setAngkatan(Integer.parseInt(txtAngkatan.getText()));
            siswa.setKelas(txtKelas.getText());
            siswa.setAgama(cbAgama.getSelectedItem().toString());
            siswa.setAlamat(txtAlamat.getText());
            siswa.setNoTelp(txtNoTelp.getText());
            siswa.setNamaAyah(txtNamaAyah.getText());
            siswa.setTglLahirAyah(txtTglLahirAyah.getDate());
            siswa.setPendidikanAyah(txtPendidikanAyah.getText());
            siswa.setPekerjaanAyah(txtPekerjaanAyah.getText());
            siswa.setNamaIbu(txtNamaIbu.getText());
            siswa.setTglLahirIbu(txtTglLahirIbu.getDate());
            siswa.setPendidikanIbu(txtPendidikanIbu.getText());
            siswa.setPekerjaanIbu(txtPekerjaanIbu.getText());

            SiswaDAO siswaDAO = new SiswaDAO();
            siswaDAO.updateSiswa(siswa);
            JOptionPane.showMessageDialog(this, "Siswa updated successfully.");
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    try {
            Siswa siswa = new Siswa();
            siswa.setNis(txtNis.getText());
            siswa.setNama(txtNama.getText());
            siswa.setTempatLahir(txtTempatLahir.getText());
            siswa.setTglLahir(new java.sql.Date(txtTanggalLahir.getDate().getTime()));
            siswa.setJenisKelamin(rbLaki.isSelected() ? "L" : "P");
            siswa.setNisn(txtNisn.getText());
            siswa.setAngkatan(Integer.parseInt(txtAngkatan.getText()));
            siswa.setKelas(txtKelas.getText());
            siswa.setAgama(cbAgama.getSelectedItem().toString());
            siswa.setAlamat(txtAlamat.getText());
            siswa.setNoTelp(txtNoTelp.getText());
            siswa.setNamaAyah(txtNamaAyah.getText());
            siswa.setTglLahirAyah(new java.sql.Date(txtTglLahirAyah.getDate().getTime()));
            siswa.setPendidikanAyah(txtPendidikanAyah.getText());
            siswa.setPekerjaanAyah(txtPekerjaanAyah.getText());
            siswa.setNamaIbu(txtNamaIbu.getText());
            siswa.setTglLahirIbu(new java.sql.Date(txtTglLahirIbu.getDate().getTime()));
            siswa.setPendidikanIbu(txtPendidikanIbu.getText());
            siswa.setPekerjaanIbu(txtPekerjaanIbu.getText());

            SiswaDAO siswaDAO = new SiswaDAO();
            siswaDAO.addSiswa(siswa);
            JOptionPane.showMessageDialog(this, "Siswa saved successfully.");
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    
   

   private void loadDataSiswa() {
    try {
        SiswaDAO siswaDAO = new SiswaDAO();
        List<Siswa> siswaList = siswaDAO.getAllSiswa();
        
        // Clear existing rows in the table model
        tableModel.setRowCount(0);
        
        for (Siswa siswa : siswaList) {
            tableModel.addRow(new Object[]{
                siswa.getNis(),
                siswa.getNama(),
                siswa.getTempatLahir(),
                siswa.getTglLahir(),
                siswa.getJenisKelamin(),
                siswa.getNisn(),
                siswa.getAngkatan(),
                siswa.getKelas(),
                siswa.getAgama(),
                siswa.getAlamat(),
                siswa.getNoTelp(),
                siswa.getNamaAyah(),
                siswa.getTglLahirAyah(),
                siswa.getPendidikanAyah(),
                siswa.getPekerjaanAyah(),
                siswa.getNamaIbu(),
                siswa.getTglLahirIbu(),
                siswa.getPendidikanIbu(),
                siswa.getPekerjaanIbu()
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
    }
}

    
    private void refresh(){
        loadDataSiswa();
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
            java.util.logging.Logger.getLogger(SiswaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SiswaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SiswaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SiswaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SiswaFrame().setVisible(true);
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
    private javax.swing.JComboBox<String> cbAgama;
    private javax.swing.JScrollPane e;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel logo;
    private javax.swing.ButtonGroup rbGroupJK;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tabDataSiswa;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtAngkatan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaAyah;
    private javax.swing.JTextField txtNamaIbu;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtNisn;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtPekerjaanAyah;
    private javax.swing.JTextField txtPekerjaanIbu;
    private javax.swing.JTextField txtPendidikanAyah;
    private javax.swing.JTextField txtPendidikanIbu;
    private com.toedter.calendar.JDateChooser txtTanggalLahir;
    private javax.swing.JTextField txtTempatLahir;
    private com.toedter.calendar.JDateChooser txtTglLahirAyah;
    private com.toedter.calendar.JDateChooser txtTglLahirIbu;
    // End of variables declaration//GEN-END:variables
}
