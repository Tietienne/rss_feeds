package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleByUserIdRepository extends CassandraRepository<Article_by_userId, String> {
    List<Article_by_userId> findAllByUserId(String userId);
}
