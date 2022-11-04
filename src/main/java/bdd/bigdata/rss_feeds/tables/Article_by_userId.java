package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table
public class Article_by_userId {
    @Column
    private final String id;
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private final String userId;
    @Column
    private final String title;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private final String pubDate;

    public Article_by_userId(String id, String userId, String title, String pubDate) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(title);
        Objects.requireNonNull(pubDate);
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.pubDate = pubDate;
    }
}
