package dao;

import database.DatabaseMySQL;
import entity.Pembayaran;
import entity.JenisPembayaran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PembayaranDAO {

    private Connection connection;

    public PembayaranDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }
    
    public List<Pembayaran> getAllPembayaran() throws SQLException {
    List<Pembayaran> pembayaranList = new ArrayList<>();
    String query = "SELECT * FROM pembayaran";
    
    try (PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            Pembayaran pembayaran = new Pembayaran(
                rs.getString("kode_pembayaran"),
                JenisPembayaran.valueOf(rs.getString("jenis_pembayaran")), // Convert String to enum
                rs.getDouble("nominal")
            );
            pembayaranList.add(pembayaran);
        }
    }
    
    return pembayaranList;
}

    public void addPembayaran(Pembayaran pembayaran) throws SQLException {
        String query = "INSERT INTO pembayaran (kode_pembayaran, jenis_pembayaran, nominal) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pembayaran.getKodePembayaran());
            stmt.setString(2, pembayaran.getJenisPembayaran().name()); // Convert enum to String
            stmt.setDouble(3, pembayaran.getNominal());
            stmt.executeUpdate();
        }
    }

    public void updatePembayaran(Pembayaran pembayaran) throws SQLException {
        String query = "UPDATE pembayaran SET jenis_pembayaran = ?, nominal = ? WHERE kode_pembayaran = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pembayaran.getJenisPembayaran().name()); // Convert enum to String
            stmt.setDouble(2, pembayaran.getNominal());
            stmt.setString(3, pembayaran.getKodePembayaran());
            stmt.executeUpdate();
        }
    }

    public void deletePembayaran(String kodePembayaran) throws SQLException {
        String query = "DELETE FROM pembayaran WHERE kode_pembayaran = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kodePembayaran);
            stmt.executeUpdate();
        }
    }

    public Pembayaran getPembayaranByKode(String kodePembayaran) throws SQLException {
        String query = "SELECT * FROM pembayaran WHERE kode_pembayaran = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kodePembayaran);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pembayaran(
                        rs.getString("kode_pembayaran"),
                        JenisPembayaran.valueOf(rs.getString("jenis_pembayaran")), // Convert String to enum
                        rs.getDouble("nominal")
                    );
                }
            }
        }
        return null;
    }
}
