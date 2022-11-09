package bdd.bigdata.rss_feeds.Scrapper;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class RssHandler extends DefaultHandler {
    private static final String ARTICLES = "articles";
    private static final String ARTICLE = "article";
    private static final String CHANNEL = "channel";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    static final String DESCRIPTION = "description";
    static final String LINK = "link";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";

    private Feed feed;
    private StringBuilder elementValue;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        feed = new Feed();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case ARTICLES:
                feed.setArticleList(new ArrayList<>());
                break;
            case CHANNEL:
                feed.setArticleList(new ArrayList<>());
            case ARTICLE:
                feed.getArticleList().add(new Article());
                break;
            case ITEM:
                feed.getArticleList().add(new Article());
                break;
            case TITLE:

            case CONTENT:
            case DESCRIPTION:
            case LINK:
            case PUB_DATE:
                elementValue = new StringBuilder();
                break;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case TITLE:
                latestArticle().setTitle(elementValue.toString());
                break;
            case DESCRIPTION:
                latestArticle().setDescription(elementValue.toString());
                break;
            case LINK:
                latestArticle().setLink(elementValue.toString());
                break;
            case PUB_DATE:
                latestArticle().setPubDate(elementValue.toString());
                break;
        }
    }

    private Article latestArticle() {
        List<Article> articleList = feed.getArticleList();
        int latestArticleIndex = articleList.size() - 1;
        return articleList.get(latestArticleIndex);
    }

    public Feed getFeed() {
        return feed;
    }
}
