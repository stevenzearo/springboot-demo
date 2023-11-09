package steve.springboot.nosql.elasticsearch.prototype;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steve.springboot.nosql.elasticsearch.domain.Person;

import java.io.IOException;
import java.util.UUID;

/**
 * @author steve
 */
public class RestHighLevelClientTest {
    private static final Logger logger = LoggerFactory.getLogger(RestHighLevelClientTest.class);
    private static ElasticsearchClient client = null;

    @BeforeEach
    public void before() {
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        client = new ElasticsearchClient(transport);
    }

    @Test
    public void create() throws IOException {
        Person person = new Person(UUID.randomUUID().toString(), "steve", "123@qq.com", 12);

        IndexResponse response = client.index(i -> i
                .index("persons")
                .id(person.id)
                .document(person));
        long version = response.version();
        logger.warn("version:" + version);
    }

    @AfterEach
    public void after() throws IOException {
        client.shutdown();
    }
}
