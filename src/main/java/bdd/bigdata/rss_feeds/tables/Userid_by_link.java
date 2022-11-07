package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.util.Objects;

public class Userid_by_link {
    @PrimaryKey
    private final String link;
    @Column
    private final String userId;

    public Userid_by_link(String link, String userId) {
        Objects.requireNonNull(link);
        Objects.requireNonNull(userId);
        this.link = link;
        this.userId = userId;
    }
}
