package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    static final String user = "postgres";
    static final String password = "last1379";
    static final String URL = "jdbc:postgresql://localhost:5432/skypro";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}