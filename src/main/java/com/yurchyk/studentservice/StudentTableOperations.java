package com.yurchyk.studentservice;

import com.yurchyk.connector.InMemConnector;
import com.yurchyk.connector.MySQLConnector;
import com.yurchyk.model.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentTableOperations {
    private static final String INSERT = "INSERT INTO StudentTableOperations ";
    private static final String GET_FROM_TABLE = "FROM StudentTableOperations ";
    private static final String NAME = "first_name";
    private static final String PRE_NAME = "last_name";
    private static Connection mySQLConnection;
    private static Connection inMemDBConnection;

    static {
        try {
            mySQLConnection = MySQLConnector.getConnection();
            inMemDBConnection = InMemConnector.getConnection();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private StudentTableOperations() {
    }

    public static void insertStudentToMySQL(Student student) throws SQLException {
        Statement statement = mySQLConnection.createStatement();
        String sql = INSERT + "VALUES (" +
                student.getId() + ", '" +
                student.getFirstName() + "', '" +
                student.getLastName() + "', " +
                student.getAge() + ", '" +
                student.getCity() + "')";
        statement.executeUpdate(sql);
        statement.close();
    }

    public static void insertStudentToH2(Student student) throws SQLException {
        Statement statement = inMemDBConnection.createStatement();
        String sql = INSERT + "VALUES (" +
                student.getId() + ", '" +
                student.getFirstName() + "', '" +
                student.getLastName() + "', " +
                student.getAge() + ", '" +
                student.getCity() + "')";
        statement.executeUpdate(sql);
        statement.close();
    }

    public static void insertToMySQLTable() throws SQLException {
        insertStudentToMySQL(new Student(1, "Danylo", "Yurchyk", 26, "Lviv"));
        insertStudentToMySQL(new Student(2, "Ivan", "Ostapko", 14, "Kyiv"));
        insertStudentToMySQL(new Student(3, "Taras", "Koval", 37, "Lviv"));
        insertStudentToMySQL(new Student(4, "Andriy", "Belet", 42, "Milan"));
        insertStudentToMySQL(new Student(5, "Roman", "Kovalyk", 25, "Lviv"));
        insertStudentToMySQL(new Student(6, "Marta", "Kushnir", 55, "Kyiv"));
        insertStudentToMySQL(new Student(7, "Julia", "Jurasuk", 12, "Rivne"));
    }

    public static void insertToInMemTable() throws SQLException {
        insertStudentToH2(new Student(1, "Danylo", "Yurchyk", 26, "Lviv"));
        insertStudentToH2(new Student(2, "Ivan", "Ostapko", 14, "Kyiv"));
        insertStudentToH2(new Student(3, "Taras", "Koval", 37, "Lviv"));
        insertStudentToH2(new Student(4, "Andriy", "Belet", 42, "Milan"));
        insertStudentToH2(new Student(5, "Roman", "Kovalyk", 25, "Lviv"));
        insertStudentToH2(new Student(6, "Marta", "Kushnir", 55, "Kyiv"));
        insertStudentToH2(new Student(7, "Julia", "Jurasuk", 12, "Rivne"));
    }

    public static void selectFromTable(Statement stmt) throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, city FROM StudentTableOperations";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("List of StudentTableOperations: " + "\n");
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString(NAME);
            String lastName = rs.getString(PRE_NAME);
            int age = rs.getInt("age");
            String city = rs.getString("city");
            System.out.print("Id: " + id + " First name: " + firstName +
                    " Last name: " + lastName + " Age: " + age + " City: " + city + "\n");
        }
    }

    public static void orderByAge(Statement stmt) throws SQLException {
        String sql = "SELECT * " +
                GET_FROM_TABLE +
                "ORDER BY age ";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("\n" + "List of StudentTableOperations sorted by age: " + "\n");
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString(NAME);
            String lastName = rs.getString(PRE_NAME);
            int age = rs.getInt("age");
            String city = rs.getString("City");

            System.out.print("Id: " + id + " Age: " + age + " First name: " + firstName +
                    " Last name: " + lastName + " City: " + city + "\n");
        }
    }

    public static void countStudents(Statement stmt) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM StudentTableOperations";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("\n" + "Count of StudentTableOperations: ");
        while (rs.next()) {
            int count = rs.getInt("total");
            System.out.println(count);
        }
    }

    public static void groupByName(Statement stmt) throws SQLException {
        String sql = "SELECT * " +
                GET_FROM_TABLE +
                "WHERE first_name LIKE 'J%'";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("\n" + "List of StudentTableOperations which names start with J: " + "\n");
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString(NAME);
            String lastName = rs.getString(PRE_NAME);
            int age = rs.getInt("age");
            String city = rs.getString("City");

            System.out.print(" First name: " + firstName + " Id: " + id + " Age: " + age +
                    " Last name: " + lastName + " City: " + city + "\n");
        }
    }

    public static void deleteByAge(Statement stmt) throws SQLException {
        String sql = "DELETE " +
                GET_FROM_TABLE +
                "WHERE age BETWEEN 20 AND 45";
        stmt.executeUpdate(sql);
    }

    public static void selectFromTableAfterDeleting(Statement stmt) throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, city FROM StudentTableOperations";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("\n" + "List of StudentTableOperations which age is more than 45 and less than 20: " + "\n");
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString(NAME);
            String lastName = rs.getString(PRE_NAME);
            int age = rs.getInt("age");
            String city = rs.getString("city");
            System.out.print("Id: " + id + " First name: " + firstName +
                    " Last name: " + lastName + " Age: " + age + " City: " + city + "\n");
        }
    }
}