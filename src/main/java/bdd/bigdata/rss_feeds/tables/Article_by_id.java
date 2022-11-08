package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Table
public class Article_by_id {
    @PrimaryKey
    private final UUID id;
    @Column
    private final String title;
    @Column
    private final Date pubDate;
    @Column
    private final String description;
    @Column
    private final String link; //flux rss

    public Article_by_id(UUID id, String title, Date pubDate, String description, String link) {
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

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pubDate=" + pubDate +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
