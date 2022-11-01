import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.example.CassandraConnector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CassandraTests {
    private Session session;

    @Before
    public void connect() {
        CassandraConnector client = new CassandraConnector();
        client.connect("127.0.0.1", 9042);
        this.session = client.getSession();
    }

    @Test
    public void selectAllArticles() {
        ResultSet result =
                session.execute("SELECT * FROM rss.article;");

        assertEquals(result.all().size(), 2);
    }
}
