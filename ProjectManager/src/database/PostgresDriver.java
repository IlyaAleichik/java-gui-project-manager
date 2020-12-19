package database;

import models.User;

import java.sql.*;
import java.util.ArrayList;

public class PostgresDriver {

    private static PostgresDriver instance;
    protected Connection connection;
    protected Statement statement;
    private ResultSet resultSet;

    private static final String USER = "postgres";
    private static final String PASS = "1";
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/ProjectManager";

    public PostgresDriver() {
       getConnection();
    }

    public static synchronized PostgresDriver getInstance() {
        if (instance == null) {
            instance = new PostgresDriver();
        }return instance;
    }

    public ResultSet getResultSet(String str) throws SQLException {
        return this.resultSet = statement.executeQuery(str);
    }

    public void getConnection(){
        System.out.println("Connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }

    }

    public void execute(String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
