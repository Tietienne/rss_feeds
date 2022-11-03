package bdd.bigdata.rss_feeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CassandraController {
    @Autowired
    ArticleByIdRepository articleByIdRepository;

    @GetMapping("/articles/{article_id}")
    public ResponseEntity<Article_by_id> findOneArticle(@PathVariable String article_id) {
        Optional<Article_by_id> article = articleByIdRepository.findById(article_id);
        if (article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Article_by_id("1","1","1","1","1"), HttpStatus.OK);
        }
    }
}
