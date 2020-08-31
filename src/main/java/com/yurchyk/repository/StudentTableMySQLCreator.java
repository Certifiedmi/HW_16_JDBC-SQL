package com.yurchyk.repository;

import com.yurchyk.connector.MySQLConnector;
import com.yurchyk.studentservice.StudentTableOperations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentTableMySQLCreator {

    private Connection connection;
    private Statement statement;

    public void createTable() throws IOException, ClassNotFoundException {
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.createStatement();
            String sqlDrop = "Drop Table if exists StudentTableOperations";
            String sql = "CREATE TABLE IF NOT EXISTS StudentTableOperations " +
                    "( id INTEGER not NULL, " +
                    " first_name VARCHAR(255), " +
                    " last_name VARCHAR(255), " +
                    " age INTEGER, " +
                    " city VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            statement.execute(sqlDrop);
            statement.execute(sql);
            System.out.println("Table created");
            StudentTableOperations.insertToMySQLTable();
            StudentTableOperations.selectFromTable(statement);
            StudentTableOperations.orderByAge(statement);
            StudentTableOperations.countStudents(statement);
            StudentTableOperations.groupByName(statement);
            StudentTableOperations.deleteByAge(statement);
            StudentTableOperations.selectFromTableAfterDeleting(statement);
            statement.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}