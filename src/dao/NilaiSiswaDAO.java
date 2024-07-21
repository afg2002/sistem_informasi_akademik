package dao;

import database.DatabaseMySQL;
import entity.NilaiSiswa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NilaiSiswaDAO {

    private Connection connection;

    public NilaiSiswaDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }

    public void addNilaiSiswa(NilaiSiswa nilaiSiswa) throws SQLException {
        String query = "INSERT INTO nilai_siswa (tahun_pelajaran, semester, kelas, angkatan, nis, kode_mapel, id_guru, nilai_absen, nilai_harian, nilai_uts, nilai_uas, nilai_rata2, grade, keterangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nilaiSiswa.getTahunPelajaran());
            stmt.setInt(2, nilaiSiswa.getSemester());
            stmt.setString(3, nilaiSiswa.getKelas());
            stmt.setInt(4, nilaiSiswa.getAngkatan());
            stmt.setInt(5, nilaiSiswa.getNis());
            stmt.setString(6, nilaiSiswa.getKodeMapel());
            stmt.setInt(7, nilaiSiswa.getIdGuru());
            stmt.setDouble(8, nilaiSiswa.getNilaiAbsen());
            stmt.setDouble(9, nilaiSiswa.getNilaiHarian());
            stmt.setDouble(10, nilaiSiswa.getNilaiUts());
            stmt.setDouble(11, nilaiSiswa.getNilaiUas());
            stmt.setDouble(12, nilaiSiswa.getNilaiRata2()); 
            stmt.setString(13, nilaiSiswa.getGrade());
            stmt.setString(14, nilaiSiswa.getKeterangan());
            stmt.executeUpdate();
        }
    }

    public void updateNilaiSiswa(NilaiSiswa nilaiSiswa) throws SQLException {
        String query = "UPDATE nilai_siswa SET tahun_pelajaran = ?, semester = ?, kelas = ?, angkatan = ?, nis = ?, kode_mapel = ?, id_guru = ?, nilai_absen = ?, nilai_harian = ?, nilai_uts = ?, nilai_uas = ?, nilai_rata2 = ?, grade = ?, keterangan = ? WHERE id_nilai = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nilaiSiswa.getTahunPelajaran());
            stmt.setInt(2, nilaiSiswa.getSemester());
            stmt.setString(3, nilaiSiswa.getKelas());
            stmt.setInt(4, nilaiSiswa.getAngkatan());
            stmt.setInt(5, nilaiSiswa.getNis());
            stmt.setString(6, nilaiSiswa.getKodeMapel());
            stmt.setInt(7, nilaiSiswa.getIdGuru());
            stmt.setDouble(8, nilaiSiswa.getNilaiAbsen());
            stmt.setDouble(9, nilaiSiswa.getNilaiHarian());
            stmt.setDouble(10, nilaiSiswa.getNilaiUts());
            stmt.setDouble(11, nilaiSiswa.getNilaiUas());
            stmt.setDouble(12, nilaiSiswa.getNilaiRata2());
            stmt.setString(13, nilaiSiswa.getGrade());
            stmt.setString(14, nilaiSiswa.getKeterangan());
            stmt.setInt(15, nilaiSiswa.getIdNilai());
            stmt.executeUpdate();
        }
    }

    public void deleteNilaiSiswa(int idNilai) throws SQLException {
        String query = "DELETE FROM nilai_siswa WHERE id_nilai = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idNilai);
            stmt.executeUpdate();
        }
    }

    public NilaiSiswa getNilaiSiswaById(int idNilai) throws SQLException {
        String query = "SELECT * FROM nilai_siswa WHERE id_nilai = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idNilai);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new NilaiSiswa(
                        rs.getInt("id_nilai"),
                        rs.getInt("tahun_pelajaran"),
                        rs.getInt("semester"),
                        rs.getString("kelas"),
                        rs.getInt("angkatan"),
                        rs.getInt("nis"),
                        rs.getString("kode_mapel"),
                        rs.getInt("id_guru"),
                        rs.getDouble("nilai_absen"),
                        rs.getDouble("nilai_harian"),
                        rs.getDouble("nilai_uts"),
                        rs.getDouble("nilai_uas"),
                        rs.getDouble("nilai_rata2"),
                        rs.getString("grade"),
                        rs.getString("keterangan")
                    );
                }
            }
        }
        return null;
    }
}
