package bdd.bigdata.rss_feeds.articles;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ArticleSummary {
    private final UUID id;
    private final String title;
    private final Date pubDate;

    public ArticleSummary(UUID id, String title, Date pubDate) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(title);
        Objects.requireNonNull(pubDate);
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
    }
}
