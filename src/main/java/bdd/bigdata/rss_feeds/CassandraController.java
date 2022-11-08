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

@RestController
public class CassandraController {
    @Autowired
    ArticleByIdRepository articleByIdRepository;

    @Autowired
    ArticleByUserIdRepository articleByUserIdRepository;

    @Autowired
    UserIdByLinkRepository useridByLinkRepository;

    private KafkaSenderService kafkaSenderService;

    public CassandraController(KafkaSenderService kafkaSenderService) {
        this.kafkaSenderService = kafkaSenderService;
    }

    /**
     * Find an article depending on the article_id given
     * @param article_id The id of the article that you are looking for
     * @return The article if it exists or Not Found message
     */
    @GetMapping("/articles/{article_id}")
    public ResponseEntity<String> findOneArticle(@PathVariable UUID article_id) {
        Optional<Article_by_id> article = articleByIdRepository.findById(article_id);
        return article.isPresent() ? new ResponseEntity<>(article.get().toString(), HttpStatus.OK) : new ResponseEntity<>("Not Found", HttpStatus.OK);
    }

    /**
     * Find last 10 articles depending on the userId given
     * @param userId The userId for the articles that you are looking for
     * @return The articles summaries if they exist or an empty list
     */
    @GetMapping("/articles")
    public ResponseEntity<String> findLast10ArticlesSummaries(@RequestParam String userId) {
        var allSummariesUser = articleByUserIdRepository.findAllByUserId(userId);
        List<ArticleSummary> articleSummary = new ArrayList<>();
        for (var i = 0; i < Integer.min(10, allSummariesUser.size()); i++) {
            articleSummary.add(allSummariesUser.get(i).createArticleSummary());
        }
        return new ResponseEntity<>(articleSummary.toString(), HttpStatus.OK);
    }

    /**
     * Save articles in Article_by_id and in Article_by_userId tables
     * @param articles The list of articles to save
     * @return The articles ids inserted in database
     */
    @PostMapping("/articles")
    public ResponseEntity<String> saveArticles(@RequestBody List<ArticleFromScraper> articles) {
        ArrayList<UUID> uuids = new ArrayList<>();
        for (var article : articles) {
            var uuid = UUID.randomUUID();
            uuids.add(uuid);
            var article_by_id = article.toArticle_by_id(uuid);
            articleByIdRepository.insert(article_by_id);
            kafkaSenderService.sendArticle_by_id(article_by_id);
            var users = useridByLinkRepository.findAllByLink(article.getRssLink());
            for (var user : users) {
                var article_by_userId = article.toArticle_by_userId(uuid, user.getUserId());
                articleByUserIdRepository.insert(article_by_userId);
                kafkaSenderService.sendArticle_by_userId(article_by_userId);
            }
        }
        return new ResponseEntity<>("Article(s) id(s) inserted : " + uuids, HttpStatus.OK);
    }

    /**
     * Subscribe a user to a rss link
     * @param userId The userId to be subscribed
     * @param rss_link The rss link to subscribe to
     * @return Message "Done"
     */
    @GetMapping("/subscribe/{userId}")
    public ResponseEntity<String> subscribeUserToLink(@PathVariable String userId, @RequestParam String rss_link) {
        useridByLinkRepository.insert(new UserId_by_link(rss_link, userId));
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
