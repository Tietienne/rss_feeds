package bdd.bigdata.rss_feeds;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ArticleByIdRepository extends CassandraRepository<Article_by_id, String> {
}
