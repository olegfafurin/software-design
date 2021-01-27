package ru.akirakozov.sd.refactoring;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;

import static org.mockito.Mockito.when;
import static ru.akirakozov.sd.refactoring.Main.DB_NAME;

/**
 * created by imd on 26.01.2021
 */

public class TestBase {

    @Mock
    protected HttpServletRequest request;

    @Mock
    protected HttpServletResponse response;

    protected final DatabaseManager databaseManager = new DatabaseManager(DB_NAME);
    protected final StringWriter writer = new StringWriter();

    @Before
    public void before() throws SQLException, IOException {
        databaseManager.execute("create table if not exists product" +
                "(id integer primary key autoincrement not null," +
                " name text not null," +
                " price int not null)");
        databaseManager.execute("delete from product");
        MockitoAnnotations.initMocks(this);
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
    }

    @After
    public void after() throws SQLException {
        databaseManager.execute("delete from product");
    }

    public String expected(Collection<Product> c) {
        StringBuilder response = new StringBuilder();
        response.append("<html><body>\n");

        for (Product product : c) {
            response.append(product.name).append("\t").append(product.price).append("</br>\n");
        }

        response.append("</body></html>\n");
        return response.toString();
    }
}
