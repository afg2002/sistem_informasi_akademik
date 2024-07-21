/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.DatabaseMySQL;
import entity.Guru;
import entity.StatusKepegawaian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Shouzen
 */
public class GuruDAO {
    private Connection connection;

    public GuruDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }

    public void addGuru(Guru guru) throws SQLException {
        String query = "INSERT INTO guru (id_guru, nama, tempat_lahir, tgl_lahir, jenis_kelamin, status_kepegawaian, mengajar_mapel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, guru.getIdGuru());
            stmt.setString(2, guru.getNama());
            stmt.setString(3, guru.getTempatLahir());
            stmt.setDate(4, new Date(guru.getTglLahir().getTime()));
            stmt.setString(5, guru.getJenisKelamin());
            stmt.setString(6, guru.getStatusKepegawaian().toString());
            stmt.setString(7, guru.getMengajarMapel());
            stmt.executeUpdate();
        }
    }

    public void updateGuru(Guru guru) throws SQLException {
        String query = "UPDATE guru SET nama = ?, tempat_lahir = ?, tgl_lahir = ?, jenis_kelamin = ?, status_kepegawaian = ?, mengajar_mapel = ? WHERE id_guru = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, guru.getNama());
            stmt.setString(2, guru.getTempatLahir());
            stmt.setDate(3, new Date(guru.getTglLahir().getTime()));
            stmt.setString(4, guru.getJenisKelamin());
            stmt.setString(5, guru.getStatusKepegawaian().toString());
            stmt.setString(6, guru.getMengajarMapel());
            stmt.setInt(7, guru.getIdGuru());
            stmt.executeUpdate();
        }
    }

    public void deleteGuru(int idGuru) throws SQLException {
        String query = "DELETE FROM guru WHERE id_guru = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idGuru);
            stmt.executeUpdate();
        }
    }

    public Guru getGuruById(int idGuru) throws SQLException {
        String query = "SELECT * FROM guru WHERE id_guru = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idGuru);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Guru(
                        rs.getInt("id_guru"),
                        rs.getString("nama"),
                        rs.getString("tempat_lahir"),
                        rs.getDate("tgl_lahir"),
                        rs.getString("jenis_kelamin"),
                        StatusKepegawaian.valueOf(rs.getString("status_kepegawaian")),
                        rs.getString("mengajar_mapel")
                    );
                }
            }
        }
        return null;
    }

    public List<Guru> getAllGuru() throws SQLException {
        List<Guru> guruList = new ArrayList<>();
        String query = "SELECT * FROM guru";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Guru guru = new Guru(
                    rs.getInt("id_guru"),
                    rs.getString("nama"),
                    rs.getString("tempat_lahir"),
                    rs.getDate("tgl_lahir"),
                    rs.getString("jenis_kelamin"),
                    StatusKepegawaian.valueOf(rs.getString("status_kepegawaian")),
                    rs.getString("mengajar_mapel")
                );
                guruList.add(guru);
            }
        }
        return guruList;
    }
}
