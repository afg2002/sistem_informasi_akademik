package dao;

import database.DatabaseMySQL;
import entity.TransaksiPembayaran;

import java.sql.Connection;
import java.sql.Date; // Import java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaksiPembayaranDAO {

    private Connection connection;

    public TransaksiPembayaranDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }

    public void addTransaksiPembayaran(TransaksiPembayaran transaksi) throws SQLException {
        String query = "INSERT INTO transaksi_pembayaran (nis, tgl_pembayaran, kode_pembayaran, jumlah_bayar) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transaksi.getNis());
            stmt.setDate(2, new java.sql.Date(transaksi.getTglPembayaran().getTime())); // Convert java.util.Date to java.sql.Date
            stmt.setString(3, transaksi.getKodePembayaran());
            stmt.setDouble(4, transaksi.getJumlahBayar());
            stmt.executeUpdate();
        }
    }

    public void updateTransaksiPembayaran(TransaksiPembayaran transaksi) throws SQLException {
        String query = "UPDATE transaksi_pembayaran SET nis = ?, tgl_pembayaran = ?, kode_pembayaran = ?, jumlah_bayar = ? WHERE no_transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transaksi.getNis());
            stmt.setDate(2, new java.sql.Date(transaksi.getTglPembayaran().getTime())); // Convert java.util.Date to java.sql.Date
            stmt.setString(3, transaksi.getKodePembayaran());
            stmt.setDouble(4, transaksi.getJumlahBayar());
            stmt.setInt(5, transaksi.getNoTransaksi());
            stmt.executeUpdate();
        }
    }

    public void deleteTransaksiPembayaran(int noTransaksi) throws SQLException {
        String query = "DELETE FROM transaksi_pembayaran WHERE no_transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, noTransaksi);
            stmt.executeUpdate();
        }
    }

    public TransaksiPembayaran getTransaksiPembayaranByNo(int noTransaksi) throws SQLException {
        String query = "SELECT * FROM transaksi_pembayaran WHERE no_transaksi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, noTransaksi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TransaksiPembayaran(
                        rs.getInt("no_transaksi"),
                        rs.getInt("nis"),
                        rs.getDate("tgl_pembayaran"), // Convert java.sql.Date to java.util.Date if needed
                        rs.getString("kode_pembayaran"),
                        rs.getDouble("jumlah_bayar")
                    );
                }
            }
        }
        return null;
    }
}
