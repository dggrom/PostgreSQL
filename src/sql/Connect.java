package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/HellowPost";
    static final String USER = "postgres";
    static final String PASS = "12481248";
    static Connection connection = null;

    public static Connection ConnectPostgre(){
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return connection;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return connection;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }

        return connection;
    }

}