package ru.akirakozov.sd.refactoring.servlet;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by imd on 27.01.2021
 */

public class HtmlPrinter {

    public static String printBody(ResultSet resultSet) throws SQLException {
        StringBuilder sb = new StringBuilder();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");
            sb.append(name).append("\t").append(price).append("</br>\n");
        }
        return sb.toString();
    }

    public static String printNumber(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? resultSet.getInt(1) + "\n" : "";
    }

    public static void printHtml(String head, String body, PrintWriter printWriter) {
        printWriter.println("<html><body>");
        if (head != null)
            printWriter.println(head);
        printWriter.print(body);
        printWriter.println("</body></html>");
    }

}
