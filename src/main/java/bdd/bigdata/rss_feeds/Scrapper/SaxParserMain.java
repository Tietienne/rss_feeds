package bdd.bigdata.rss_feeds.Scrapper;

import bdd.bigdata.rss_feeds.articles.ArticleFromScraper;
import com.typesafe.config.ConfigException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

public class SaxParserMain {

    public static String reverseString(String originalStr) {
        Objects.requireNonNull(originalStr);
        String reversed = "";
        char ch;
        for (int i = 0; i < originalStr.length(); i++) {
            ch = originalStr.charAt(i);
            reversed = ch + reversed;
        }
        return reversed;
    }

    public static String getFileNameFromUrl(String url) {
        int topLevel = url.length() - reverseString(url).indexOf('.') - 1;
        int wwwIndex = url.indexOf(".") + 1;

        return url.substring(wwwIndex, topLevel);
    }




    public static String getRSSFile(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String filename = "src/main/resources/" + getFileNameFromUrl(url) + ".xml";

        Elements links = doc.select("link[type=application/rss+xml]");
        String rss_url = "";
        if (links.size() > 0) { //s ill y a bel et bien des liens a regarder
            rss_url = links.get(0).attr("abs:href").toString();
            System.out.println(rss_url);
            Path p = Path.of(rss_url);
            File targetFile = new File(filename);
            targetFile.delete();
            try (BufferedInputStream in = new BufferedInputStream(new URL(rss_url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.out.println("IOException during feed parsing of "+filename);
            }
        } else {
            System.out.println("RSS url not found");
        }
        return rss_url;
    }


    public static List<ArticleFromScraper> getRSSFeedsFromLinksList(List<String> websiteLinks) throws ParserConfigurationException, SAXException, IOException, ParseException {
        ArrayList<ArticleFromScraper> fromScraperList = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        Random rand = new Random();
        for (String url : websiteLinks) {

            String filename = "src/main/resources/" + getFileNameFromUrl(url) + ".xml";

            RssHandler RssHandler = new RssHandler(); //will contain feeds

            saxParser.parse(filename, RssHandler);//Parses the xml rss file
            System.out.println("PARSING PASSED");

            SimpleDateFormat RFC822Format = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss Z", java.util.Locale.US);
            System.out.println("FORMATTED PASSED");

            for (Article a : RssHandler.getFeed().getArticleList()) {
                Date articleDate = new Date();
                if(a.getPubDate() != null){
                    articleDate = RFC822Format.parse(a.getPubDate());
                }
                fromScraperList.add(new ArticleFromScraper(rand.nextInt() + "",
                        a.getTitle(),
                        articleDate,
                        a.getDescription(),
                        a.getLink()));
            }
            System.out.println("ARTICLE LISTED");
        }
        return fromScraperList;
    }


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        ArrayList<String> websiteLinks = new ArrayList<>();

        websiteLinks.add("https://www.linux-magazine.com");
        websiteLinks.add("https://www.vogella.com");
        websiteLinks.add("https://www.clever-cloud.com/fr/blog/");
        websiteLinks.add("https://www.lemonde.fr");

        ArrayList<ArticleFromScraper> fromscraper = null;
        ArrayList<Article> artlist = new ArrayList<>();
        try{
            List<ArticleFromScraper> aFromScraper= getRSSFeedsFromLinksList(websiteLinks);
            fromscraper= new ArrayList<>(aFromScraper);
        }
        catch(ParserConfigurationException e){
            System.out.println("ParserConfiguration went wrong");
        }
        catch(ParseException pe){
            System.out.println("ParseExceptione");
        }




        for(String url : websiteLinks) {

            String filename = "src/main/resources/" + getFileNameFromUrl(url) + ".xml";

            RssHandler RssHandler = new RssHandler();

            saxParser.parse(filename, RssHandler);

            for(Article a : RssHandler.getFeed().getArticleList()){
                artlist.add(a);
            }

        }

        if(fromscraper != null){
            System.out.println(fromscraper);
        }
        else{

            System.out.println("went wrong during araylistifllement " + (fromscraper == null));
        }
    }

}
