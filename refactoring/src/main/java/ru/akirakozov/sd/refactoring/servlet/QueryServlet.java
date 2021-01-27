package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.Database;
import ru.akirakozov.sd.refactoring.handler.HandlerFactory;
import ru.akirakozov.sd.refactoring.handler.QueryHandler;
import ru.akirakozov.sd.refactoring.html.HtmlPrinter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import static ru.akirakozov.sd.refactoring.Main.DB_NAME;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String command = request.getParameter("command");
//
//        if ("max".equals(command)) {
//            try {
//                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
//                    Statement stmt = c.createStatement();
//                    ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
//                    HtmlPrinter.printHtml("<h1>Product with max price: </h1>", HtmlPrinter.printBody(rs), response.getWriter());
//                    rs.close();
//                    stmt.close();
//                }
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else if ("min".equals(command)) {
//            try {
//                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
//                    Statement stmt = c.createStatement();
//                    ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
//                    HtmlPrinter.printHtml("<h1>Product with min price: </h1>", HtmlPrinter.printBody(rs), response.getWriter());
//                    rs.close();
//                    stmt.close();
//                }
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else if ("sum".equals(command)) {
//            try {
//                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
//                    Statement stmt = c.createStatement();
//                    ResultSet rs = stmt.executeQuery("SELECT SUM(price) FROM PRODUCT");
//                    HtmlPrinter.printHtml("Summary price: ", HtmlPrinter.printNumber(rs), response.getWriter());
//                    rs.close();
//                    stmt.close();
//                }
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else if ("count".equals(command)) {
//            try {
//                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
//                    Statement stmt = c.createStatement();
//                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM PRODUCT");
//                    HtmlPrinter.printHtml("Number of products: ", HtmlPrinter.printNumber(rs), response.getWriter());
//                    rs.close();
//                    stmt.close();
//                }
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            response.getWriter().println("Unknown command: " + command);
//        }
        QueryHandler handler = new HandlerFactory().getHandler(request, new Database(DB_NAME));
        try {
            handler.execute(response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
