package bdd.bigdata.rss_feeds.articles;

import java.util.Objects;

public class ArticleSummary {
    private final String id;
    private final String title;
    private final String pubDate;

    public ArticleSummary(String id, String title, String pubDate) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(title);
        Objects.requireNonNull(pubDate);
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
    }
}
