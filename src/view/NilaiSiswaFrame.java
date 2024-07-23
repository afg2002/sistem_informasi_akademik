/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import dao.GuruDAO;
import dao.MapelDAO;
import dao.NilaiSiswaDAO;
import dao.SiswaDAO;
import entity.Guru;
import entity.Mapel;
import entity.NilaiSiswa;
import entity.Siswa;
import java.sql.*;
import static helper.Helper.resetForm;
import static helper.Helper.setPlaceholder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shouzen
 */
public class NilaiSiswaFrame extends javax.swing.JFrame {
    private DefaultTableModel tableModel;   
    private final String placeholderText = "Cari berdasarkan ID";
    public NilaiSiswaFrame() {
        initComponents();
        semesterAndAcademicYear();
        setPlaceholder(txtCari, placeholderText);
        String[] columnNames = {
            "ID","Tahun Pelajaran", "Semester", "Kelas", "Angkatan", "NIS", "Kode Mapel", "ID Guru",
            "Nilai Absen", "Nilai Harian", "Nilai UTS", "Nilai UAS", "Nilai Rata-Rata", "Grade", "Keterangan"
        };

        tableModel = new DefaultTableModel(columnNames, 0);
        tabDataNilaiSiswa.setModel(tableModel);
        loadDataNilaiSiswa();
        // Add row selection listener
        tabDataNilaiSiswa.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabDataNilaiSiswa.getSelectedRow();
                    if (selectedRow >= 0) {
                        populateFieldsFromSelectedRow(selectedRow);
                    }
                }
            }
        });
        
        tabDataNilaiSiswa.getParent().addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(final ComponentEvent e) {
            if (tabDataNilaiSiswa.getPreferredSize().width < tabDataNilaiSiswa.getParent().getWidth()) {
                tabDataNilaiSiswa.setAutoResizeMode(tabDataNilaiSiswa.AUTO_RESIZE_ALL_COLUMNS);
            } else {
                tabDataNilaiSiswa.setAutoResizeMode(tabDataNilaiSiswa.AUTO_RESIZE_OFF);
            }
    }
});
        
        
        

    }
    
    private void populateFieldsFromSelectedRow(int selectedRow) {
    // Get the selected row data from the table model
    int id = (int) tableModel.getValueAt(selectedRow, 0);
    String tahunPelajaran = (String) tableModel.getValueAt(selectedRow, 1);
    int semester = (int) tableModel.getValueAt(selectedRow, 2);
    String kelas = (String) tableModel.getValueAt(selectedRow, 3);
    int angkatan = (int) tableModel.getValueAt(selectedRow, 4);
    String nis = (String) tableModel.getValueAt(selectedRow, 5);
    String kodeMapel = (String) tableModel.getValueAt(selectedRow, 6);
    int idGuru = (int) tableModel.getValueAt(selectedRow, 7);
    double nilaiAbsen = (double) tableModel.getValueAt(selectedRow, 8);
    double nilaiHarian = (double) tableModel.getValueAt(selectedRow, 9);
    double nilaiUts = (double) tableModel.getValueAt(selectedRow, 10);
    double nilaiUas = (double) tableModel.getValueAt(selectedRow, 11);
    double nilaiRata2 = (double) tableModel.getValueAt(selectedRow, 12);
    String grade = (String) tableModel.getValueAt(selectedRow, 13);
    String keterangan = (String) tableModel.getValueAt(selectedRow, 14);

    // Set the values in the form fields
    txtID.setText(String.valueOf(id));
    txtTahunPelajaran.setText(String.valueOf(tahunPelajaran));
    txtSemester.setText(String.valueOf(semester));
    txtKelas.setText(kelas);
    txtNis.setText(String.valueOf(nis));
    txtKodeMapel.setText(kodeMapel);
    txtIdGuru.setText(String.valueOf(idGuru));
    txtNilaiAbsen.setText(String.valueOf(nilaiAbsen));
    txtNilaiHarian.setText(String.valueOf(nilaiHarian));
    txtNilaiUts.setText(String.valueOf(nilaiUts));
    txtNilaiUas.setText(String.valueOf(nilaiUas));
    txtNilaiRata2.setText(String.valueOf(nilaiRata2));
    txtGrade.setText(grade);
    txtKeterangan.setText(keterangan);
    txtAngkatan.setText(String.valueOf(angkatan));

    // Retrieve and set additional details using DAOs
    setDetailsFromDAOs(nis, kodeMapel, idGuru);
}
    
    
    private void  semesterAndAcademicYear(){
        // Get current year for academic year
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        // Set academic year and semester
        String academicYear = currentMonth <= 6 ? currentYear + "/" + (currentYear + 1) : (currentYear - 1) + "/" + currentYear;
        String semester = currentMonth <= 6 ? "1" : "2";

        txtTahunPelajaran.setText(academicYear);
        txtSemester.setText(semester);
    }
    
    private void setDetailsFromDAOs(String nis, String kodeMapel, int idGuru) {
    try {
        // Retrieve student details using SiswaDAO
        SiswaDAO siswaDAO = new SiswaDAO();
        Siswa siswa = siswaDAO.getSiswaByNis(nis);
        if (siswa != null) {
            txtNama.setText(siswa.getNama());
        }

        // Retrieve mapel details using MapelDAO
        MapelDAO mapelDAO = new MapelDAO();
        Mapel mapel = mapelDAO.getMapelByKode(kodeMapel);
        if (mapel != null) {
            txtNamaMapel.setText(mapel.getNamaMapel());
        }

        // Retrieve guru details using GuruDAO
        GuruDAO guruDAO = new GuruDAO();
        Guru guru = guruDAO.getGuruById(idGuru);
        if (guru != null) {
            txtNamaGuru.setText(guru.getNama());
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving additional details: " + e.getMessage());
    }
}


    
    private void loadDataNilaiSiswa() {
    try {
        NilaiSiswaDAO nilaiSiswaDAO = new NilaiSiswaDAO();
        List<NilaiSiswa> nilaiSiswaList = nilaiSiswaDAO.getAllNilaiSiswa();

        // Clear existing rows in the table model
        tableModel.setRowCount(0);

        // Add rows to the table model
        for (NilaiSiswa nilaiSiswa : nilaiSiswaList) {
            tableModel.addRow(new Object[]{
                nilaiSiswa.getIdNilai(),
                nilaiSiswa.getTahunPelajaran(),
                nilaiSiswa.getSemester(),
                nilaiSiswa.getKelas(),
                nilaiSiswa.getAngkatan(),
                nilaiSiswa.getNis(),
                nilaiSiswa.getKodeMapel(),
                nilaiSiswa.getIdGuru(),
                nilaiSiswa.getNilaiAbsen(),
                nilaiSiswa.getNilaiHarian(),
                nilaiSiswa.getNilaiUts(),
                nilaiSiswa.getNilaiUas(),
                nilaiSiswa.getNilaiRata2(),
                nilaiSiswa.getGrade(),
                nilaiSiswa.getKeterangan()
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
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
        txtTahunPelajaran = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSemester = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNis = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        e = new javax.swing.JScrollPane();
        tabDataNilaiSiswa = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCetak = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtAngkatan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtKodeMapel = new javax.swing.JTextField();
        txtKelas = new javax.swing.JTextField();
        txtNamaMapel = new javax.swing.JTextField();
        txtIdGuru = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNamaGuru = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNilaiAbsen = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNilaiHarian = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNilaiUts = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNilaiUas = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNilaiRata2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtGrade = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        btnCariSiswa = new javax.swing.JButton();
        btnCariMapel = new javax.swing.JButton();
        btnCariGuru = new javax.swing.JButton();
        btnHitung = new javax.swing.JButton();
        txtID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Nilai Siswa");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tahun Pelajaran");

        txtTahunPelajaran.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Semester");

        txtSemester.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NIS");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kelas");

        e.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabDataNilaiSiswa.setAutoCreateRowSorter(true);
        tabDataNilaiSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabDataNilaiSiswa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        e.setViewportView(tabDataNilaiSiswa);

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

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Angkatan");

        txtAngkatan.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");

        txtNama.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mata Pelajaran");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID Guru");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Kode Mapel");

        txtKelas.setEditable(false);

        txtNamaMapel.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nama Guru");

        txtNamaGuru.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nilai Absen");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nilai Harian");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nilai UTS");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nilai UAS");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nilai Rata2");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Grade");

        txtGrade.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Keterangan");

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane1.setViewportView(txtKeterangan);

        btnCariSiswa.setText("Cari NIS");
        btnCariSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariSiswaActionPerformed(evt);
            }
        });

        btnCariMapel.setText("Cari Kode Mapel");
        btnCariMapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMapelActionPerformed(evt);
            }
        });

        btnCariGuru.setText("Cari Id Guru");
        btnCariGuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariGuruActionPerformed(evt);
            }
        });

        btnHitung.setText("Hitung");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 153, 255));
        txtID.setText("ID");

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
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel20)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(txtID))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTahunPelajaran)
                            .addComponent(txtSemester)
                            .addComponent(txtNama)
                            .addComponent(txtAngkatan)
                            .addComponent(txtNis)
                            .addComponent(txtKodeMapel)
                            .addComponent(txtKelas)
                            .addComponent(txtNamaMapel)
                            .addComponent(txtIdGuru)
                            .addComponent(txtNamaGuru)
                            .addComponent(txtNilaiAbsen)
                            .addComponent(txtNilaiHarian)
                            .addComponent(txtNilaiUts)
                            .addComponent(txtNilaiUas)
                            .addComponent(txtNilaiRata2)
                            .addComponent(txtGrade)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCariMapel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCariSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCariGuru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHitung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
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
                            .addComponent(e)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetak)
                    .addComponent(txtID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTahunPelajaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariSiswa))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtKodeMapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariMapel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtNamaMapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtIdGuru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariGuru))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNamaGuru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtNilaiAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtNilaiHarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtNilaiUts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtNilaiUas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNilaiRata2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHitung)))
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
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetForm(jPanel1, rbGroupJK);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // Retrieve the search term from txtCari
    String searchId = txtCari.getText().trim();

    if (searchId.isEmpty() || searchId.equals(placeholderText)) {
        JOptionPane.showMessageDialog(this, "Please enter a search term.");
        loadDataNilaiSiswa();
        return;
    }

    try {
        // Create DAO instance
        NilaiSiswaDAO nilaiSiswaDAO = new NilaiSiswaDAO();
        
        // Perform the search
        NilaiSiswa nilaiSiswa = nilaiSiswaDAO.getNilaiSiswaById(Integer.parseInt(searchId));

        // Clear the existing rows in the table model
        tableModel.setRowCount(0);

        // Check if the search result is not null
        if (nilaiSiswa != null) {
            // Add the search result to the table model
            tableModel.addRow(new Object[]{
                nilaiSiswa.getIdNilai(),
                nilaiSiswa.getTahunPelajaran(),
                nilaiSiswa.getSemester(),
                nilaiSiswa.getKelas(),
                nilaiSiswa.getAngkatan(),
                nilaiSiswa.getNis(),
                nilaiSiswa.getKodeMapel(),
                nilaiSiswa.getIdGuru(),
                nilaiSiswa.getNilaiAbsen(),
                nilaiSiswa.getNilaiHarian(),
                nilaiSiswa.getNilaiUts(),
                nilaiSiswa.getNilaiUas(),
                nilaiSiswa.getNilaiRata2(),
                nilaiSiswa.getGrade(),
                nilaiSiswa.getKeterangan()
            });
        } else {
            JOptionPane.showMessageDialog(this, "No record found for ID: " + searchId);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Get the ID from txtId field
    String idNilai = txtID.getText().trim();
    
    if (idNilai.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an ID to delete.");
        return;
    }

    // Confirm deletion with the user
    int confirm = JOptionPane.showConfirmDialog(
        this, 
        "Are you sure you want to delete the record with ID: " + idNilai + "?", 
        "Confirm Deletion", 
        JOptionPane.YES_NO_OPTION
    );
    
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Create DAO instance
            NilaiSiswaDAO nilaiSiswaDAO = new NilaiSiswaDAO();
            
            // Delete the record from the database
            nilaiSiswaDAO.deleteNilaiSiswa(Integer.parseInt(idNilai));
            
            // Refresh the table data
            refresh();
            
            // Notify user
            JOptionPane.showMessageDialog(this, "Record deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting record: " + e.getMessage());
        }
    }    
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
          try {
            // Validate input fields
            if (txtNis.getText().trim().isEmpty() || txtKodeMapel.getText().trim().isEmpty() || txtIdGuru.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "NIS, Kode Mapel, and ID Guru cannot be empty.");
                return;
            }

            // Get values from text fields
            String tahunPelajaran = txtTahunPelajaran.getText().trim();
            int semester = Integer.parseInt(txtSemester.getText().trim());
            String kelas = txtKelas.getText().trim();
            int angkatan = Integer.parseInt(txtAngkatan.getText().trim());
            String nis = txtNis.getText().trim();
            String kodeMapel = txtKodeMapel.getText().trim();
            int idGuru = Integer.parseInt(txtIdGuru.getText().trim());
            double nilaiAbsen = Double.parseDouble(txtNilaiAbsen.getText().trim());
            double nilaiHarian = Double.parseDouble(txtNilaiHarian.getText().trim());
            double nilaiUts = Double.parseDouble(txtNilaiUts.getText().trim());
            double nilaiUas = Double.parseDouble(txtNilaiUas.getText().trim());
            double nilaiRata2 = Double.parseDouble(txtNilaiRata2.getText().trim());
            String grade = txtGrade.getText().trim();
            String keterangan = txtKeterangan.getText().trim();

            // Create a NilaiSiswa object
            NilaiSiswa nilaiSiswa = new NilaiSiswa();
            nilaiSiswa.setTahunPelajaran(tahunPelajaran);
            nilaiSiswa.setSemester(semester);
            nilaiSiswa.setKelas(kelas);
            nilaiSiswa.setAngkatan(angkatan);
            nilaiSiswa.setNis(nis);
            nilaiSiswa.setKodeMapel(kodeMapel);
            nilaiSiswa.setIdGuru(idGuru);
            nilaiSiswa.setNilaiAbsen(nilaiAbsen);
            nilaiSiswa.setNilaiHarian(nilaiHarian);
            nilaiSiswa.setNilaiUts(nilaiUts);
            nilaiSiswa.setNilaiUas(nilaiUas);
            nilaiSiswa.setNilaiRata2(nilaiRata2);
            nilaiSiswa.setGrade(grade);
            nilaiSiswa.setKeterangan(keterangan);

            // Save the NilaiSiswa object using the DAO
            NilaiSiswaDAO nilaiSiswaDAO = new NilaiSiswaDAO();
            nilaiSiswaDAO.addNilaiSiswa(nilaiSiswa);

            // Refresh the data and reset the form
            refresh();
            resetForm(this, null);

            // Show success message
            JOptionPane.showMessageDialog(this, "Data Nilai Siswa saved successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }  
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCariSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariSiswaActionPerformed
        // Get the NIS from the search field
    String nis = txtNis.getText().trim();
    
    if (nis.isEmpty()) {
        JOptionPane.showMessageDialog(this, "NIS field cannot be empty.");
        return;
    }
    
    try {
        // Create a new instance of SiswaDAO
        SiswaDAO siswaDAO = new SiswaDAO();
        
        // Search for the student by NIS
        Siswa siswa = siswaDAO.getSiswaByNis(nis);
        
        if (siswa != null) {
            // Populate the form fields with the student's data
            txtNama.setText(siswa.getNama());
            txtAngkatan.setText(String.valueOf(siswa.getAngkatan()));
            txtKelas.setText(siswa.getKelas());
        } else {
            // Show a message if no student is found
            JOptionPane.showMessageDialog(this, "No student found with NIS: " + nis);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error searching for student: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCariSiswaActionPerformed

    private void btnCariMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMapelActionPerformed
        // Get the search term from the search field
    String searchTerm = txtKodeMapel.getText().trim();
    
    if (searchTerm.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Search field cannot be empty.");
        return;
    }
    
    try {
        // Create a new instance of MapelDAO
        MapelDAO mapelDAO = new MapelDAO();
        
        // Search for the mapel by code
        Mapel mapel = mapelDAO.getMapelByKode(searchTerm);
        
        if (mapel == null) {
            // Show a message if no mapel is found
            JOptionPane.showMessageDialog(this, "No subject found with the code: " + searchTerm);
            // Optionally clear the text fields if no mapel is found
            txtKodeMapel.setText("");
            txtNamaMapel.setText("");
        } else {
            // Populate the form fields with the mapel data
            txtKodeMapel.setText(mapel.getKodeMapel());
            txtNamaMapel.setText(mapel.getNamaMapel());
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error searching for subject: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCariMapelActionPerformed

    private void btnCariGuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariGuruActionPerformed
        // Get the search term from the search field
    String searchTerm = txtIdGuru.getText().trim();
    
    if (searchTerm.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Search field cannot be empty.");
        return;
    }
    
    try {
        // Parse the search term to an integer
        int idGuru;
        try {
            idGuru = Integer.parseInt(searchTerm);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid numeric ID.");
            return;
        }
        
        // Create a new instance of GuruDAO
        GuruDAO guruDAO = new GuruDAO();
        
        // Search for the guru by ID
        Guru guru = guruDAO.getGuruById(idGuru);
        
        if (guru == null) {
            // Show a message if no guru is found
            JOptionPane.showMessageDialog(this, "No teacher found with the ID: " + idGuru);
            // Optionally clear the text fields if no guru is found
            txtIdGuru.setText("");
            txtNamaGuru.setText("");
            // Optionally clear other fields related to Guru
        } else {
            // Populate the form fields with the guru data
            txtIdGuru.setText(String.valueOf(guru.getIdGuru()));
            txtNamaGuru.setText(guru.getNama());
            // Optionally set other fields related to Guru
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error searching for teacher: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCariGuruActionPerformed

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        try {
        // Get values from text fields and convert to double
        double nilaiAbsen = Double.parseDouble(txtNilaiAbsen.getText());
        double nilaiHarian = Double.parseDouble(txtNilaiHarian.getText());
        double nilaiUts = Double.parseDouble(txtNilaiUts.getText());
        double nilaiUas = Double.parseDouble(txtNilaiUas.getText());
        
        // Calculate the average score
        double rata2 = (nilaiAbsen + nilaiHarian + nilaiUts + nilaiUas) / 4;
        txtNilaiRata2.setText(String.format("%.2f", rata2)); 
        
        // Determine the grade
        String grade = calculateGrade(rata2);
        txtGrade.setText(grade);
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage());
    }
    }//GEN-LAST:event_btnHitungActionPerformed
    
    private String calculateGrade(double average) {
        if (average >= 85) return "A";
        else if (average >= 70) return "B";
        else if (average >= 55) return "C";
        else if (average >= 40) return "D";
        else return "E";
    }
    
    private void refresh(){
        loadDataNilaiSiswa();
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
            java.util.logging.Logger.getLogger(NilaiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NilaiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NilaiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NilaiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NilaiSiswaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCariGuru;
    private javax.swing.JButton btnCariMapel;
    private javax.swing.JButton btnCariSiswa;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane e;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.ButtonGroup rbGroupJK;
    private javax.swing.JTable tabDataNilaiSiswa;
    private javax.swing.JTextField txtAngkatan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtIdGuru;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtKodeMapel;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaGuru;
    private javax.swing.JTextField txtNamaMapel;
    private javax.swing.JTextField txtNilaiAbsen;
    private javax.swing.JTextField txtNilaiHarian;
    private javax.swing.JTextField txtNilaiRata2;
    private javax.swing.JTextField txtNilaiUas;
    private javax.swing.JTextField txtNilaiUts;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtSemester;
    private javax.swing.JTextField txtTahunPelajaran;
    // End of variables declaration//GEN-END:variables
}
