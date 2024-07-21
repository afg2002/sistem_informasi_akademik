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
