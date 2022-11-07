package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UseridByLinkRepository extends CassandraRepository<Userid_by_link, String> {
}
