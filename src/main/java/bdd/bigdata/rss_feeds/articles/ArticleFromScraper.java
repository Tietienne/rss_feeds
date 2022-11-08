package bdd.bigdata.rss_feeds.articles;

import bdd.bigdata.rss_feeds.tables.Article_by_id;
import bdd.bigdata.rss_feeds.tables.Article_by_userId;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ArticleFromScraper {
    private final String id;
    private final String title;
    private final Date pubDate;
    private final String description;
    private final String link;

    public String getRssLink() {
        var splitLink = link.split("/");
        return splitLink[0] + "//" + splitLink[2];
    }

    public ArticleFromScraper(String id, String title, Date pubDate, String description, String link) {
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

    public Article_by_id toArticle_by_id(UUID uuid) {
        return new Article_by_id(uuid, title, pubDate, description, link);
    }

    public Article_by_userId toArticle_by_userId(UUID uuid, String userId) {
        return new Article_by_userId(uuid, userId, title, pubDate);
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
