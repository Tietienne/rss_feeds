package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleByUserIdRepository extends CassandraRepository<Article_by_user_id, String> {
    List<Article_by_user_id> findAllByUserId(String user_id);
}
