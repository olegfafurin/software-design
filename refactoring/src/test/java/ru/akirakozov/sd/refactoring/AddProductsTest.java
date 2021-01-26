package ru.akirakozov.sd.refactoring;

import org.junit.Assert;
import org.junit.Test;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * created by imd on 26.01.2021
 */

public class AddProductsTest extends TestBase {

    private final AddProductServlet servlet = new AddProductServlet();

    private void addProducts(Product product) throws IOException {
        when(request.getParameter("name")).thenReturn(product.name);
        when(request.getParameter("price")).thenReturn(String.valueOf(product.price));
        servlet.doGet(request, response);
    }

    @Test
    public void addProductTest() throws IOException {
        addProducts(new Product("iphone", 10000));
        Assert.assertEquals("OK\n", writer.toString());
    }

}
