package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.Database;
import ru.akirakozov.sd.refactoring.handler.GetQueryHandler;
import ru.akirakozov.sd.refactoring.handler.QueryHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import static ru.akirakozov.sd.refactoring.Main.DB_NAME;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QueryHandler handler = new GetQueryHandler(new Database(DB_NAME));
        try {
            handler.execute(response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
