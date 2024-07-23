package dao;

import database.DatabaseMySQL;
import entity.Mapel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapelDAO {

    private Connection connection;

    public MapelDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }
    
     public Mapel getMapelByKode(String kodeMapel) throws SQLException {
        String query = "SELECT * FROM mapel WHERE kode_mapel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kodeMapel);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Mapel(
                        rs.getString("kode_mapel"),
                        rs.getString("nama_mapel")
                    );
                }
            }
        }
        return null;
    }

    public void addMapel(Mapel mapel) throws SQLException {
        String query = "INSERT INTO mapel (kode_mapel, nama_mapel) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, mapel.getKodeMapel());
            stmt.setString(2, mapel.getNamaMapel());
            stmt.executeUpdate();
        }
    }

    public void updateMapel(Mapel mapel) throws SQLException {
        String query = "UPDATE mapel SET nama_mapel = ? WHERE kode_mapel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, mapel.getNamaMapel());
            stmt.setString(2, mapel.getKodeMapel());
            stmt.executeUpdate();
        }
    }

    public void deleteMapel(String kodeMapel) throws SQLException {
        String query = "DELETE FROM mapel WHERE kode_mapel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kodeMapel);
            stmt.executeUpdate();
        }
    }

    // Method to search mapel by kode_mapel or nama_mapel
    public List<Mapel> searchMapel(String searchTerm) throws SQLException {
        List<Mapel> mapelList = new ArrayList<>();
        String query;
        
        query = "SELECT * FROM mapel WHERE kode_mapel LIKE ? OR nama_mapel LIKE ?";
        

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // Set parameters only if searchTerm is not empty
                String searchPattern = "%" + searchTerm + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
        
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mapel mapel = new Mapel(
                        rs.getString("kode_mapel"),
                        rs.getString("nama_mapel")
                    );
                    mapelList.add(mapel);
                }
            }
        }
        return mapelList;
    }

    public List<Mapel> getAllMapel() throws SQLException {
        List<Mapel> mapelList = new ArrayList<>();
        String query = "SELECT * FROM mapel";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Mapel mapel = new Mapel(
                    rs.getString("kode_mapel"),
                    rs.getString("nama_mapel")
                );
                mapelList.add(mapel);
            }
        }
        return mapelList;
    }
}
