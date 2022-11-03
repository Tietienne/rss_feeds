package bdd.bigdata.rss_feeds;

import com.datastax.driver.core.*;

import java.util.List;
import java.util.Optional;

public class CassandraConnector {
    private Cluster cluster;

    private Session session;

    public void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }

    private void checkSession() {
        if (session == null) {
            throw new IllegalStateException("Need to be connected : use connect() method first");
        }
    }

    public void createKeySpace() {
        checkSession();
        this.session.execute("CREATE KEYSPACE IF NOT EXISTS rss WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };");
    }

    public void removeTables() {
        checkSession();
        this.session.execute("DROP TABLE IF EXISTS rss.article_by_id");
        this.session.execute("DROP TABLE IF EXISTS rss.article_by_userId");
    }

    public void createTables() {
        checkSession();
        this.session.execute("CREATE TABLE IF NOT EXISTS rss.article_by_id ( " +
                                "id text PRIMARY KEY," +
                                "title text," +
                                "pubDate text," +
                                "description text," +
                                "link text " +
                                ");");
        this.session.execute("CREATE TABLE IF NOT EXISTS rss.article_by_userId ( " +
                                "id text," +
                                "title text," +
                                "pubDate text," +
                                "description text," +
                                "link text " +
                                "userId text" +
                                "PRIMARY KEY((userId, id), pubDate)" +
                                ");");
    }

    public List<ArticleSummary> findLast10ArticlesSummaries(String userId) {
        // TODO
        return null;
    }

    public Optional<Article> findOneArticle(String article_id) {
        PreparedStatement prepared = session.prepare("SELECT * FROM rss.article_by_id" +
                                                        "WHERE id = ?;");
        ResultSet result = session.execute(prepared.bind(article_id));
        Row row = result.one();

        return Optional.of(new Article(row.getString("id"), row.getString("title"), row.getString("pubDate"), row.getString("description"), row.getString("link")));
    }

    public void saveArticles(List<Article> articles) {
        // TODO
    }
}
