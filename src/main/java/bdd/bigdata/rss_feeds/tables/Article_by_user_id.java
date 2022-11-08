package bdd.bigdata.rss_feeds.tables;

import bdd.bigdata.rss_feeds.articles.ArticleSummary;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Table
public class Article_by_user_id {
    @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private final UUID id;
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private final String user_id;
    @Column
    private final String title;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private final Date pubDate;

    public Article_by_user_id(UUID id, String user_id, String title, Date pubDate) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(user_id);
        Objects.requireNonNull(title);
        Objects.requireNonNull(pubDate);
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.pubDate = pubDate;

    }

    public ArticleSummary createArticleSummary(){
        return new ArticleSummary(id, title, pubDate);
    }
}
