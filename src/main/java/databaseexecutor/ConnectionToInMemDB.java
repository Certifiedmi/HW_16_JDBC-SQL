package databaseexecutor;

import studentstable.Students;

import java.sql.*;

public class ConnectionToInMemDB {
    private static final String DB_URL = "jdbc:h2:mem:test";
    private static final String DB_DRIVER = "org.h2.Driver";

    static final String USER = "admin";
    static final String PASS = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void connectionToDB() throws ClassNotFoundException {
        Class.forName(DB_DRIVER);
    }

    private static void createTable() throws SQLException {
        Connection conn;
        Statement stmt;
        System.out.println("Connection installed");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String sqlDrop = "Drop Table if exists Students";
        String sql = "CREATE TABLE IF NOT EXISTS Students " +
                "( id INTEGER not NULL, " +
                " first_name VARCHAR(255), " +
                " last_name VARCHAR(255), " +
                " age INTEGER, " +
                " city VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        stmt.execute(sqlDrop);
        stmt.execute(sql);
        System.out.println("Table created");
        Students.insertToTable(stmt);
        Students.selectFromTable(stmt);
        Students.orderByAge(stmt);
        Students.countStudents(stmt);
        Students.groupByName(stmt);
        Students.deleteByAge(stmt);
        try {
            stmt.close();
        } catch (SQLException ignored) {
        }
        try {
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private static void testGetTable() throws SQLException {
        Connection conn = getConnection();
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, null, null);
        System.out.println("List of tables in h2 memory: ");
        while (rs.next()) {
            System.out.println(rs.getString(3));
        }
        conn.close();
    }

    public void executor() {
        try {
            ConnectionToInMemDB.getConnection();
            ConnectionToInMemDB.connectionToDB();
            ConnectionToInMemDB.createTable();
            ConnectionToInMemDB.testGetTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
    }
}