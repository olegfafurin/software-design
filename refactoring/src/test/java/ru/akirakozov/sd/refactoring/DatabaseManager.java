package ru.akirakozov.sd.refactoring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * created by imd on 26.01.2021
 */

public class DatabaseManager {

    private final String databaseName;

    public DatabaseManager(String s) {
        super();
        this.databaseName = s;
    }

    public void execute(String request) throws SQLException {
        try (Connection conn = DriverManager.getConnection(databaseName)) {
            try (Statement statement = conn.createStatement()){
                statement.execute(request);
            }
        }
    }

    public void addProduct(Product product) throws SQLException {
        execute("insert into product (name, price) values (\"" + product.name + "\",\"" + product.price + "\")");
    }

}
