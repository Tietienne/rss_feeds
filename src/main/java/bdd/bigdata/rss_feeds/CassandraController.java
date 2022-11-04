package bdd.bigdata.rss_feeds;

import bdd.bigdata.rss_feeds.articles.ArticleSummary;
import bdd.bigdata.rss_feeds.tables.ArticleByIdRepository;
import bdd.bigdata.rss_feeds.tables.ArticleByUserIdRepository;
import bdd.bigdata.rss_feeds.tables.Article_by_id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CassandraController {
    @Autowired
    ArticleByIdRepository articleByIdRepository;

    @Autowired
    ArticleByUserIdRepository articleByUserIdRepository;

    /**
     * Find an article depending on the article_id given
     * @param article_id The id of the article that you are looking for
     * @return The article if it exists or HttpStatus not found
     */
    @GetMapping("/articles/{article_id}")
    public ResponseEntity<Article_by_id> findOneArticle(@PathVariable String article_id) {
        Optional<Article_by_id> article = articleByIdRepository.findById(article_id);
        if (article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleSummary>> findLast10ArticlesSummaries(@RequestParam String userId) {
        // TODO
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

    }

    @PostMapping("/articles")
    public ResponseEntity<String> saveArticles(@RequestBody List<Article_by_id> articles) {
        //TODO
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
