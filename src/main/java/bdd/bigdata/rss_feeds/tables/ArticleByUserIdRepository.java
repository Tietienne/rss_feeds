package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ArticleByUserIdRepository extends CassandraRepository<Article_by_userId, String> {
}
