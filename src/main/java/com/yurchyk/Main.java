package com.yurchyk;

import com.yurchyk.executor.Executor;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        new Executor().startMyApp();
    }
}