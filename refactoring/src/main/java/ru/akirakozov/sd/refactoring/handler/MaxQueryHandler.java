package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.Database;
import ru.akirakozov.sd.refactoring.html.HtmlPrinter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by imd on 27.01.2021
 */

public class MaxQueryHandler extends QueryHandler{

    public MaxQueryHandler(Database db) {
        super(db);
    }

    @Override
    protected String htmlOption(ResultSet rs) throws SQLException {
        return HtmlPrinter.printBody(rs);
    }

    @Override
    public String query() {
        return "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
    }

    @Override
    public String title() {
        return HtmlPrinter.toHeader("Product with max price: ");
    }
}
