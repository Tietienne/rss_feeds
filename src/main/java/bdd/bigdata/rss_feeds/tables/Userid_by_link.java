package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table
public class Userid_by_link {
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private final String link;
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private final String userId;

    public Userid_by_link(String link, String userId) {
        Objects.requireNonNull(link);
        Objects.requireNonNull(userId);
        this.link = link;
        this.userId = userId;
    }
}
