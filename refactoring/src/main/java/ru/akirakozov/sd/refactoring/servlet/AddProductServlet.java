package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.Database;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import static ru.akirakozov.sd.refactoring.Main.DB_NAME;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));
        try {
            new Database(DB_NAME).executeRequest("INSERT INTO PRODUCT " +
                    "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("OK");
    }
}
