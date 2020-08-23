package databaseexecutor;

import studentstable.Students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToMysqlDB {
    private static final String URL = "jdbc:mysql://localhost/test?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public void connection() throws SQLException {
        Connection conn;
        Statement stmt;
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
        Students.selectFromTableAfterDeleting(stmt);
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
}