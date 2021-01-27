package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.Database;
import ru.akirakozov.sd.refactoring.html.HtmlPrinter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by imd on 27.01.2021
 */

public abstract class QueryHandler {

    private final Database db;

    public QueryHandler(Database db) {
        this.db = db;
    }

    public void execute(HttpServletResponse response) throws SQLException, IOException {
        ResultSet rs = db.executeAndGet(query());
        String title = title();
        String body = htmlOption(rs);
        HtmlPrinter.printHtml(title, body, response.getWriter());
        rs.close();

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected abstract String htmlOption(ResultSet rs) throws SQLException;

    public abstract String query();

    public abstract String title();

}
