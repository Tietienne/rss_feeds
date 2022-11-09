package bdd.bigdata.rss_feeds.Scrapper;

import java.util.List;

public class Feed {
    private List<Article> articleList;

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }


    public List<Article> getArticleList() {
        return this.articleList;
    }
}
