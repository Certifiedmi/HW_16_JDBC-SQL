package executor;

import databaseexecutor.ConnectionToInMemDB;
import databaseexecutor.ConnectionToMysqlDB;

import java.sql.SQLException;

public class Executor {

    public void startMyApp() throws SQLException {
        System.out.println("\n" + "CREATING OF H2 DATABASE" + "\n" + "---------------");
        new ConnectionToInMemDB().executor();
        System.out.println("\n" + "CREATING OF MYSQL DATABASE" + "\n" + "---------------");
        new ConnectionToMysqlDB().connection();
    }

}
