package ru.akirakozov.sd.refactoring.handler;

import ru.akirakozov.sd.refactoring.Database;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by imd on 27.01.2021
 */

public class UnknownQueryHandler extends QueryHandler {

    String command;

    public UnknownQueryHandler(Database db, String command) {
        super(db);
        this.command = command;
    }

    @Override
    public void execute(HttpServletResponse response) throws IOException {
        response.getWriter().println("Unknown command: " + command);
    }

    @Override
    protected String htmlOption(ResultSet rs) {
        return "";
    }

    @Override
    public String query() {
        return null;
    }

    @Override
    public String title() {
        return command;
    }
}
