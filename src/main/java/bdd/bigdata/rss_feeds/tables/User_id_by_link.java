package bdd.bigdata.rss_feeds.tables;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table
public class User_id_by_link {
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private final String link;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private final String user_id;

    public String getUserId() {
        return user_id;
    }

    public User_id_by_link(String link, String user_id) {
        Objects.requireNonNull(link);
        Objects.requireNonNull(user_id);
        this.link = link;
        this.user_id = user_id;
    }
}
