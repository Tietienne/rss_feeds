package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ArticleByIdRepository extends CassandraRepository<Article_by_id, UUID> {
}
