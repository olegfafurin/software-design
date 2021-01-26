package ru.akirakozov.sd.refactoring;

import org.junit.Assert;
import org.junit.Test;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

/**
 * created by imd on 26.01.2021
 */

public class QueryTest extends TestBase {

    private final QueryServlet servlet = new QueryServlet();

    private void fillDB() throws SQLException {
        databaseManager.addProduct(new Product("mouse", 100));
        databaseManager.addProduct(new Product("keyboard", 500));
        databaseManager.addProduct(new Product("computer", 10000));
        databaseManager.addProduct(new Product("car", 1000000));
    }

    @Test
    public void MaxCommand() throws SQLException, IOException {
        fillDB();
        when(request.getParameter("command")).thenReturn("max");
        servlet.doGet(request, response);
        Assert.assertEquals(maxResponse("car", 1000000), writer.toString());
    }

    @Test
    public void MinCommand() throws SQLException, IOException {
        fillDB();
        when(request.getParameter("command")).thenReturn("min");
        servlet.doGet(request, response);
        Assert.assertEquals(minResponse("mouse", 100), writer.toString());
    }

    @Test
    public void SumCommand() throws SQLException, IOException {
        fillDB();
        when(request.getParameter("command")).thenReturn("sum");
        servlet.doGet(request, response);
        Assert.assertEquals(sumResponse(1010600), writer.toString());
    }

    @Test
    public void CountCommand() throws SQLException, IOException {
        fillDB();
        when(request.getParameter("command")).thenReturn("count");
        servlet.doGet(request, response);
        Assert.assertEquals(countResponse(4), writer.toString());
    }

    @Test
    public void UnknownCommand() throws SQLException, IOException {
        when(request.getParameter("command")).thenReturn("nop");
        fillDB();
        servlet.doGet(request, response);
        Assert.assertEquals(unknownResponse("nop"), writer.toString());
    }

    private String toHtml(String s) {
        return "<html><body>\n" + s + "</body></html>\n";
    }

    private String minResponse(String name, int price) {
        return toHtml("<h1>Product with min price: </h1>\n" + nameAndPrice(name, price));
    }

    private String maxResponse(String name, int price) {
        return toHtml("<h1>Product with max price: </h1>\n" + nameAndPrice(name, price));
    }

    private String sumResponse(int price) {
        return toHtml("Summary price: \n" + price + "\n");
    }

    private String countResponse(int n) {
        return toHtml("Number of products: \n" + n + "\n");
    }

    private String unknownResponse(String s) {
        return "Unknown command: " + s + "\n";
    }

    private String nameAndPrice(String name, int price) {
        if (name != null && !name.isEmpty()) {
            return name + "\t" + price + "</br>\n";
        }
        return "";
    }
}
