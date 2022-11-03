package org.example;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

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

    public void createKeySpace() {
        if (session == null) {
            throw new IllegalStateException("Need to be connected : use connect() method first");
        }
        this.session.execute("CREATE KEYSPACE IF NOT EXISTS rss WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };");
    }

    public List<ArticleSummary> findLast10ArticlesSummaries(String userId) {
        // TODO
        return null;
    }

    public Optional<Article> findOneArticle(String article_id) {
        // TODO
        return Optional.empty();
    }

    public void saveArticles(List<Article> articles) {
        // TODO
    }
}
