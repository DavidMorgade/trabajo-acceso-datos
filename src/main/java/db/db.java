package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    String url = "jdbc:mysql://localhost:5432/trabajo";

    String user = "postgres";

    String password = "marcos";

    public db() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
