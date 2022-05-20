package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper implements IUserMapper {
    ConnectionPool connectionPool;

    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public User login(String email, String password) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("full_name");
                    int balance = rs.getInt("balance");
                    String address = rs.getString("address");
                    int zip = rs.getInt("zip_nr");
                    String role = rs.getString("role");
                    user = new User(email, fullName, balance, address, zip, role);
                } else {
                    throw new DatabaseException("Wrong email or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    @Override
    public User createUser(String email, String fullName, String password, String address, int zipNr, String role) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        Random random = new Random();
        int randomNumberBalance = random.nextInt(100000);
        String sql = "insert into user (email, full_name, password, balance, address, zip_nr, role) values (?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, fullName);
                ps.setString(3, password);
                ps.setInt(4, randomNumberBalance);
                ps.setString(5, address);
                ps.setInt(6, zipNr);
                ps.setString(7, role);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    user = new User(email, password, role);
                } else {
                    throw new DatabaseException("The user with email = " + email + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert email into database");
        }
        return user;
    }

    @Override
    public List<User> retrieveAllUsers() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<User> userList = new ArrayList<>();

        String sql = "SELECT email, full_name, password, balance, address, zip_nr, role FROM carport.user";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("email");
                    String fullName = rs.getString("full_name");
                    String password = rs.getString("password");
                    int balance = rs.getInt("balance");
                    String address = rs.getString("address");
                    int zipNr = rs.getInt("zip_nr");
                    String role = rs.getString("role");
                    User user = new User(email, fullName, password, balance, address, zipNr, role);
                    userList.add(user);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return userList;
    }

    public void updateAddress(String email, String address) throws DatabaseException {
        User user;
        String rawString = address;
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        String utfEncoding = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(utfEncoding);
        String sql = "UPDATE user SET address = \"" + address +"\" WHERE email = \"" + email + "\"";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert email into database");
        }
    }
}
