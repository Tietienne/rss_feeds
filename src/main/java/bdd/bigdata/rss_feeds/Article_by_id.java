package bdd.bigdata.rss_feeds;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table
public class Article_by_id {
    @PrimaryKey
    private final String id;
    @Column
    private final String title;
    @Column
    private final String pubDate;
    @Column
    private final String description;
    @Column
    private final String link;

    public Article_by_id(String id, String title, String pubDate, String description, String link) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(title);
        Objects.requireNonNull(pubDate);
        Objects.requireNonNull(description);
        Objects.requireNonNull(link);
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.link = link;
    }
}
