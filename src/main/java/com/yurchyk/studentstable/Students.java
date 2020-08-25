package com.yurchyk.studentstable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Students {
    private static final String INSERT = "INSERT INTO Students ";
    private static final String GET_FROM_TABLE = "FROM Students ";
    private static final String NAME = "first_name";
    private static final String PRE_NAME = "last_name";

    private Students() {
    }

    public static void insertToTable(Statement stmt) throws SQLException {
        String sql = INSERT + "VALUES (1, 'Danylo', 'Yurchyk', 26, 'Lviv')";
        stmt.execute(sql);

        sql = INSERT + "VALUES (2, 'Ivan', 'Ostapko', 14, 'Kyiv')";
        stmt.execute(sql);

        sql = INSERT + "VALUES (3, 'Taras', 'Koval', 37, 'Lviv')";
        stmt.execute(sql);

        sql = INSERT + "VALUES(4, 'Andriy', 'Belet', 42, 'Milan')";
        stmt.execute(sql);

        sql = INSERT + "VALUES (5, 'Roman', 'Kovalyk', 25, 'Lviv')";
        stmt.execute(sql);

        sql = INSERT + "VALUES (6, 'Marta', 'Kushnir', 55, 'Kyiv')";
        stmt.execute(sql);

        sql = INSERT + "VALUES (7, 'Julia', 'Jurasuk', 12, 'Rivne')";
        stmt.execute(sql);
    }

    public static void selectFromTable(Statement stmt) throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, city FROM Students";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("List of Students: " + "\n");
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString(NAME);
            String lastName = rs.getString(PRE_NAME);
            int age = rs.getInt("age");
            String city = rs.getString("city");
            System.out.print("Id: " + id + "First name: " + firstName +
                    " Last name: " + lastName + " Age: " + age + " City: " + city + "\n");
        }
    }

    public static void orderByAge(Statement stmt) throws SQLException {
        String sql = "SELECT * " +
                GET_FROM_TABLE +
                "ORDER BY age ";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("\n" + "List of Students sorted by age: " + "\n");
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
        String sql = "SELECT COUNT(*) AS total FROM Students";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("\n" + "Count of Students: ");
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
        System.out.print("\n" + "List of Students which names start with J: " + "\n");
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
        String sql = "SELECT id, first_name, last_name, age, city FROM Students";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.print("\n" + "List of Students which age is more than 45 and less than 20: " + "\n");
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString(NAME);
            String lastName = rs.getString(PRE_NAME);
            int age = rs.getInt("age");
            String city = rs.getString("city");
            System.out.print("Id: " + id + "First name: " + firstName +
                    " Last name: " + lastName + " Age: " + age + " City: " + city + "\n");
        }
    }
}