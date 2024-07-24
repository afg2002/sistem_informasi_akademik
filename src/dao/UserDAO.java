package dao;

import database.DatabaseMySQL;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseMySQL.connectDB();
    }
    
     public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setNama(rs.getString("nama"));
                user.setJabatan(rs.getString("jabatan"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        }
        return users;
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
    public List<User> searchUsers(String searchTerm) throws SQLException {
    List<User> userList = new ArrayList<>();
    String query = "SELECT * FROM users WHERE id_user LIKE ? OR nama LIKE ? OR username LIKE ?";
    
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        String pattern = "%" + searchTerm + "%";  // Prepare the search pattern with wildcards
        
        // Set parameters for the query
        stmt.setString(1, pattern);
        stmt.setString(2, pattern);
        stmt.setString(3, pattern);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Add users to the list
                userList.add(new User(
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("jabatan"),
                    rs.getString("username"),
                    rs.getString("password")  // Handle password securely
                ));
            }
        }
    }
    return userList;
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
