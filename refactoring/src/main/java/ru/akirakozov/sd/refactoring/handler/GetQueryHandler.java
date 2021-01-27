package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.Database;
import ru.akirakozov.sd.refactoring.html.HtmlPrinter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by imd on 27.01.2021
 */

public class GetQueryHandler extends QueryHandler{

    public GetQueryHandler(Database db) {
        super(db);
    }

    @Override
    protected String htmlOption(ResultSet rs) throws SQLException {
        return HtmlPrinter.printBody(rs);
    }

    @Override
    public String query() {
        return "SELECT * FROM PRODUCT";
    }

    @Override
    public String title() {
        return null;
    }
}
