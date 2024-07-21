package dao;

import database.DatabaseMySQL;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (nama, jabatan, username, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getJabatan());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword()); // In real applications, hash the password before storing
            stmt.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET nama = ?, jabatan = ?, username = ?, password = ? WHERE id_user = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getJabatan());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword()); // In real applications, hash the password before updating
            stmt.setInt(5, user.getIdUser());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int idUser) throws SQLException {
        String query = "DELETE FROM users WHERE id_user = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
        }
    }

    public User getUserById(int idUser) throws SQLException {
        String query = "SELECT * FROM users WHERE id_user = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id_user"),
                        rs.getString("nama"),
                        rs.getString("jabatan"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null;
    }

    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id_user"),
                        rs.getString("nama"),
                        rs.getString("jabatan"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null;
    }

    // Method to authenticate user during login
    public User login(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); 
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id_user"),
                        rs.getString("nama"),
                        rs.getString("jabatan"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null; // Return null if login fails
    }
}
