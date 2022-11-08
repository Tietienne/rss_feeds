package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseridByLinkRepository extends CassandraRepository<Userid_by_link, String> {
    List<Userid_by_link> findAllByLink(String link);
}
