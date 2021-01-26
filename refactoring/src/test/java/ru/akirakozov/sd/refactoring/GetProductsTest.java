package ru.akirakozov.sd.refactoring;

import org.junit.Assert;
import org.junit.Test;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * created by imd on 26.01.2021
 */

public class GetProductsTest extends TestBase {

    private final GetProductsServlet servlet = new GetProductsServlet();

    @Test
    public void oneProductTest() throws SQLException, IOException {
        Product theOnlyProduct = new Product("mouse", 100);
        databaseManager.addProduct(theOnlyProduct);
        servlet.doGet(request, response);
        Assert.assertEquals(writer.toString(), expected(Collections.singletonList(theOnlyProduct)));
    }


    @Test
    public void manyProductsTest() throws SQLException, IOException {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("mouse", 100));
        products.add(new Product("keyboard", 500));
        products.add(new Product("computer", 10000));
        products.add(new Product("car", 1000000));
        for (Product product: products) {
            databaseManager.addProduct(product);
        }
        servlet.doGet(request, response);
        Assert.assertEquals(writer.toString(), expected(products));
    }
}
