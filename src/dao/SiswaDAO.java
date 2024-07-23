package dao;

import database.DatabaseMySQL;
import entity.Siswa;
import java.sql.Connection;
import java.sql.Date; // Import java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiswaDAO {

    private Connection connection;

    public SiswaDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }
    
    
     public Siswa getSiswaByNis(String nis) throws SQLException {
        String query = "SELECT * FROM siswa WHERE nis = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nis);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Siswa(
                        rs.getString("nis"),
                        rs.getString("nama"),
                        rs.getString("tempat_lahir"),
                        rs.getDate("tgl_lahir"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("nisn"),
                        rs.getInt("angkatan"),
                        rs.getString("kelas"),
                        rs.getString("agama"),
                        rs.getString("alamat"),
                        rs.getString("no_telp"),
                        rs.getString("nama_ayah"),
                        rs.getDate("tgl_lahir_ayah"),
                        rs.getString("pendidikan_ayah"),
                        rs.getString("pekerjaan_ayah"),
                        rs.getString("nama_ibu"),
                        rs.getDate("tgl_lahir_ibu"),
                        rs.getString("pendidikan_ibu"),
                        rs.getString("pekerjaan_ibu")
                    );
                }
            }
        }
        return null;
    }
    public List<Siswa> getAllSiswa() throws SQLException {
        List<Siswa> siswaList = new ArrayList<>();
        String query = "SELECT * FROM siswa";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Siswa siswa = new Siswa(
                    rs.getString("nis"),
                    rs.getString("nama"),
                    rs.getString("tempat_lahir"),
                    rs.getDate("tgl_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("nisn"),
                    rs.getInt("angkatan"),
                    rs.getString("kelas"),
                    rs.getString("agama"),
                    rs.getString("alamat"),
                    rs.getString("no_telp"),
                    rs.getString("nama_ayah"),
                    rs.getDate("tgl_lahir_ayah"),
                    rs.getString("pendidikan_ayah"),
                    rs.getString("pekerjaan_ayah"),
                    rs.getString("nama_ibu"),
                    rs.getDate("tgl_lahir_ibu"),
                    rs.getString("pendidikan_ibu"),
                    rs.getString("pekerjaan_ibu")
                );
                siswaList.add(siswa);
            }
        }
        return siswaList;
    }

    public void addSiswa(Siswa siswa) throws SQLException {
    String query = "INSERT INTO siswa (nis, nama, tempat_lahir, tgl_lahir, jenis_kelamin, nisn, angkatan, kelas, agama, alamat, no_telp, nama_ayah, tgl_lahir_ayah, pendidikan_ayah, pekerjaan_ayah, nama_ibu, tgl_lahir_ibu, pendidikan_ibu, pekerjaan_ibu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, siswa.getNis());
        stmt.setString(2, siswa.getNama());
        stmt.setString(3, siswa.getTempatLahir());
        stmt.setDate(4, new java.sql.Date(siswa.getTglLahir().getTime()));
        stmt.setString(5, siswa.getJenisKelamin());
        stmt.setString(6, siswa.getNisn());
        stmt.setInt(7, siswa.getAngkatan());
        stmt.setString(8, siswa.getKelas());
        stmt.setString(9, siswa.getAgama());
        stmt.setString(10, siswa.getAlamat());
        stmt.setString(11, siswa.getNoTelp());
        stmt.setString(12, siswa.getNamaAyah());
        stmt.setDate(13, new java.sql.Date(siswa.getTglLahirAyah().getTime()));
        stmt.setString(14, siswa.getPendidikanAyah());
        stmt.setString(15, siswa.getPekerjaanAyah());
        stmt.setString(16, siswa.getNamaIbu());
        stmt.setDate(17, new java.sql.Date(siswa.getTglLahirIbu().getTime()));
        stmt.setString(18, siswa.getPendidikanIbu());
        stmt.setString(19, siswa.getPekerjaanIbu());
        
        stmt.executeUpdate();
    }
}


    public void updateSiswa(Siswa siswa) throws SQLException {
        String query = "UPDATE siswa SET nama = ?, tempat_lahir = ?, tgl_lahir = ?, jenis_kelamin = ?, nisn = ?, angkatan = ?, kelas = ?, agama = ?, alamat = ?, no_telp = ?, nama_ayah = ?, tgl_lahir_ayah = ?, pendidikan_ayah = ?, pekerjaan_ayah = ?, nama_ibu = ?, tgl_lahir_ibu = ?, pendidikan_ibu = ?, pekerjaan_ibu = ? WHERE nis = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, siswa.getNama());
            stmt.setString(2, siswa.getTempatLahir());
            stmt.setDate(3, new java.sql.Date(siswa.getTglLahir().getTime()));  // Convert java.util.Date to java.sql.Date
            stmt.setString(4, siswa.getJenisKelamin());
            stmt.setString(5, siswa.getNisn());
            stmt.setInt(6, siswa.getAngkatan());
            stmt.setString(7, siswa.getKelas());
            stmt.setString(8, siswa.getAgama());
            stmt.setString(9, siswa.getAlamat());
            stmt.setString(10, siswa.getNoTelp());
            stmt.setString(11, siswa.getNamaAyah());
            stmt.setDate(12, new java.sql.Date(siswa.getTglLahirAyah().getTime()));  // Convert java.util.Date to java.sql.Date
            stmt.setString(13, siswa.getPendidikanAyah());
            stmt.setString(14, siswa.getPekerjaanAyah());
            stmt.setString(15, siswa.getNamaIbu());
            stmt.setDate(16, new java.sql.Date(siswa.getTglLahirIbu().getTime()));  // Convert java.util.Date to java.sql.Date
            stmt.setString(17, siswa.getPendidikanIbu());
            stmt.setString(18, siswa.getPekerjaanIbu());
            stmt.setString(19, siswa.getNis());
            stmt.executeUpdate();
        }
    }

    public void deleteSiswa(String nis) throws SQLException {
        String query = "DELETE FROM siswa WHERE nis = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nis);
            stmt.executeUpdate();
        }
    }

    public List<Siswa> searchSiswa(String keyword) throws SQLException {
        List<Siswa> siswaList = new ArrayList<>();
        String query = "SELECT * FROM siswa WHERE nama LIKE ? OR nis LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String keywordPattern = "%" + keyword + "%";
            stmt.setString(1, keywordPattern);
            stmt.setString(2, keywordPattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Siswa siswa = new Siswa(
                        rs.getString("nis"),
                        rs.getString("nama"),
                        rs.getString("tempat_lahir"),
                        rs.getDate("tgl_lahir"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("nisn"),
                        rs.getInt("angkatan"),
                        rs.getString("kelas"),
                        rs.getString("agama"),
                        rs.getString("alamat"),
                        rs.getString("no_telp"),
                        rs.getString("nama_ayah"),
                        rs.getDate("tgl_lahir_ayah"),
                        rs.getString("pendidikan_ayah"),
                        rs.getString("pekerjaan_ayah"),
                        rs.getString("nama_ibu"),
                        rs.getDate("tgl_lahir_ibu"),
                        rs.getString("pendidikan_ibu"),
                        rs.getString("pekerjaan_ibu")
                    );
                    siswaList.add(siswa);
                }
            }
        }
        return siswaList;
    }
}


