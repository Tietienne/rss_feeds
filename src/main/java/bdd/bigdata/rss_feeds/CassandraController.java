package bdd.bigdata.rss_feeds;

import bdd.bigdata.rss_feeds.articles.ArticleSummary;
import bdd.bigdata.rss_feeds.tables.ArticleByIdRepository;
import bdd.bigdata.rss_feeds.tables.ArticleByUserIdRepository;
import bdd.bigdata.rss_feeds.tables.Article_by_id;
import bdd.bigdata.rss_feeds.tables.UseridByLinkRepository;
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
        if (article.isPresent()) {
            return new ResponseEntity<>(article.get().toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<String> saveArticles(@RequestBody List<Article_by_id> articles) {
        //TODO
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
