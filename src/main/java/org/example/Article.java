package org.example;

import java.util.Objects;

public class Article {
    private final String id;
    private final String title;
    private final String pubDate;
    private final String description;
    private final String link;

    public Article(String id, String title, String pubDate, String description, String link) {
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
