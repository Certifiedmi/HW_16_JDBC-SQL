package com.yurchyk.executor;

import com.yurchyk.repository.StudentTableH2Creator;
import com.yurchyk.repository.StudentTableMySQLCreator;

import java.io.IOException;
import java.sql.SQLException;

public class Executor {

    public void startMyApp() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n" + "CREATING OF H2 DATABASE" + "\n" + "---------------");
        new StudentTableH2Creator().createTable();
        System.out.println("\n" + "CREATING OF MYSQL DATABASE" + "\n" + "---------------");
        new StudentTableMySQLCreator().createTable();
    }

}