package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.Database;
import ru.akirakozov.sd.refactoring.html.HtmlPrinter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by imd on 27.01.2021
 */

public class SumHandler extends QueryHandler {

    public SumHandler(Database db) {
        super(db);
    }

    @Override
    protected String htmlOption(ResultSet rs) throws SQLException {
        return HtmlPrinter.printNumber(rs);
    }

    @Override
    public String query() {
        return "SELECT SUM(price) FROM PRODUCT";
    }

    @Override
    public String title() {
        return "Summary price: ";
    }
}
