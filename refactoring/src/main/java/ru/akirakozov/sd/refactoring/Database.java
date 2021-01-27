package ru.akirakozov.sd.refactoring;

import java.sql.*;

/**
 * created by imd on 27.01.2021
 */

public class Database {

    private final String databaseName;

    public Database(String s) {
        super();
        this.databaseName = s;
    }

    public void executeRequest(String request) throws SQLException {
        try (Connection conn = DriverManager.getConnection(databaseName)) {
            try (Statement statement = conn.createStatement()){
                statement.execute(request);
            }
        }
    }

    public ResultSet executeAndGet(String request) throws SQLException {
        Connection conn = DriverManager.getConnection(databaseName);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(request);
        statement.closeOnCompletion();
        return rs;
    }

}
