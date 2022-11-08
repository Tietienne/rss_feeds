package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIdByLinkRepository extends CassandraRepository<User_id_by_link, String> {
    List<User_id_by_link> findAllByLink(String link);
}
