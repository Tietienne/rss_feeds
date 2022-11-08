package bdd.bigdata.rss_feeds;

import bdd.bigdata.rss_feeds.tables.Article_by_id;
import bdd.bigdata.rss_feeds.tables.Article_by_userId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yidoughi
 */

@Service
public class KafkaSenderService {
    private final String topic = "topic-articles";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendArticle_by_id(Article_by_id article) {
        kafkaTemplate.send(topic, article.toString());
    }

    public void sendArticle_by_userId(Article_by_userId article) {
        kafkaTemplate.send(topic, article.toString());
    }
}
