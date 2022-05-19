package dat.startcode.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private final static String USER = "carport";
    private final static String PASSWORD = "carport";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;
    private static UserMapper userMapper;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        userMapper = new UserMapper(connectionPool);
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Remove all rows from all tables
                stmt.execute("delete from user");
                // Indsæt et par brugere
                stmt.execute("insert into user (email, full_name, password, balance, address, zip_nr, role) " +
                        "values ('a@a.dk','Adminbruger','1234','100000','Nørgaardsvej 30','2800','admin'),('b@.dk','Testbruger','1234','100000','Nørgaardsvej 30','2800','user')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void login() throws DatabaseException {
        User expectedUser = new User("user", "1234", "user");
        User actualUser = userMapper.login("user", "1234");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> userMapper.login("user", "123"));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> userMapper.login("bob", "1234"));
//    }
//
//    @Test
//    void createUser() throws DatabaseException {
//        User newUser = userMapper.createUser("jill", "1234", "user");
//        User logInUser = userMapper.login("jill", "1234");
//        User expectedUser = new User("jill", "1234", "user");
//        assertEquals(expectedUser, newUser);
//        assertEquals(expectedUser, logInUser);

    }
}