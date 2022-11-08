package bdd.bigdata.rss_feeds;

import bdd.bigdata.rss_feeds.articles.ArticleFromScraper;
import bdd.bigdata.rss_feeds.articles.ArticleSummary;
import bdd.bigdata.rss_feeds.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CassandraController {
    @Autowired
    ArticleByIdRepository articleByIdRepository;

    @Autowired
    ArticleByUserIdRepository articleByUserIdRepository;

    @Autowired
    UseridByLinkRepository useridByLinkRepository;

    /**
     * Find an article depending on the article_id given
     * @param article_id The id of the article that you are looking for
     * @return The article if it exists or HttpStatus not found
     */
    @GetMapping("/articles/{article_id}")
    public ResponseEntity<String> findOneArticle(@PathVariable UUID article_id) {
        Optional<Article_by_id> article = articleByIdRepository.findById(article_id);
        return article.isPresent() ? new ResponseEntity<>(article.get().toString(), HttpStatus.OK) : new ResponseEntity<>("Not Found", HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<String> findLast10ArticlesSummaries(@RequestParam String userId) {
        var allSummariesUser = articleByUserIdRepository.findAll();
        var indexMax = articleByUserIdRepository.findAll().size()-1;
        List<ArticleSummary> articleSummary = new ArrayList<>();
        if (indexMax>9) {
            allSummariesUser.subList(indexMax-10,indexMax).forEach(article -> articleSummary.add(article.createArticleSummary()));
        } else {
            allSummariesUser.forEach(article -> articleSummary.add(article.createArticleSummary()));
        }
        return new ResponseEntity<>(articleSummary.toString(), HttpStatus.OK);
    }

    @PostMapping("/articles")
    public ResponseEntity<String> saveArticles(@RequestBody List<ArticleFromScraper> articles) {
        ArrayList<UUID> uuids = new ArrayList<>();
        for (var article : articles) {
            var uuid = UUID.randomUUID();
            uuids.add(uuid);
            articleByIdRepository.insert(article.toArticle_by_id(uuid));
            var links = new ArrayList<String>();
            links.add(article.getLink());
            var users = useridByLinkRepository.findAllByLink(article.getLink());
            for (var user : users) {
                articleByUserIdRepository.insert(article.toArticle_by_userId(uuid, user.getUserId()));
            }
        }
        return new ResponseEntity<>("Article(s) id(s) inserted : " + uuids, HttpStatus.OK);
    }

    @GetMapping("/subscribe/{user_id}")
    public ResponseEntity<String> subscribeUserToLink(@PathVariable String user_id, @RequestParam String rss_link) {
        useridByLinkRepository.insert(new Userid_by_link(rss_link, user_id));
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
