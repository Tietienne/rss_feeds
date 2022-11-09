package bdd.bigdata.rss_feeds.Scrapper;

public class Article {
    private String title;
    //private String content;
    private String description;
    private String link;
    //private String author;
    private String pubDate;
    //private String guid;

    public void setDescription(String description) {
        this.description = description;
    }


    public void setLink(String link) {
        this.link = link;
    }


    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }


    public String toString(){
        return "Article [title = "+title
                +",\n description="+description
                +",\n link="+link
                +",\n pubDate="+pubDate
                +"]\n";
    }
}
